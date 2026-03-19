package com.db.service;

import com.db.bank.DatabaseConnection;
import com.table.view.CarrinhoTable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PurchaseService {

    public static final class MedicationAvailability {
        private final String medicationName;
        private final String medicationType;
        private final int stockQuantity;
        private final String clientPhone;

        public MedicationAvailability(String medicationName, String medicationType, int stockQuantity, String clientPhone) {
            this.medicationName = medicationName;
            this.medicationType = medicationType;
            this.stockQuantity = stockQuantity;
            this.clientPhone = clientPhone;
        }

        public String medicationName() {
            return medicationName;
        }

        public String medicationType() {
            return medicationType;
        }

        public int stockQuantity() {
            return stockQuantity;
        }

        public String clientPhone() {
            return clientPhone;
        }
    }

    public MedicationAvailability getMedicationAvailability(String user, String medicationName) throws SQLException {
        try (Connection db = DatabaseConnection.open()) {
            String type = null;
            int quantity = 0;

            try (PreparedStatement medicamentoStmt = db.prepareStatement(
                    "SELECT nome, quantidade, tipo FROM medicamentos WHERE nome = ?")) {
                medicamentoStmt.setString(1, medicationName);
                try (ResultSet result = medicamentoStmt.executeQuery()) {
                    if (result.next()) {
                        type = result.getString("tipo");
                        quantity = result.getInt("quantidade");
                    } else {
                        return null;
                    }
                }
            }

            String phone = null;
            try (PreparedStatement phoneStmt = db.prepareStatement(
                    "SELECT telefone FROM cliente WHERE usuario = ?")) {
                phoneStmt.setString(1, user);
                try (ResultSet result = phoneStmt.executeQuery()) {
                    if (result.next()) {
                        phone = result.getString("telefone");
                    }
                }
            }

            return new MedicationAvailability(medicationName, type, quantity, phone);
        }
    }

    public void addToCartAndDecreaseStock(String user, String medicationName, int quantity, float totalPrice) throws SQLException {
        try (Connection db = DatabaseConnection.open()) {
            db.setAutoCommit(false);
            try {
                int stockQuantity = 0;
                try (PreparedStatement stockStmt = db.prepareStatement(
                        "SELECT quantidade FROM medicamentos WHERE nome = ?")) {
                    stockStmt.setString(1, medicationName);
                    try (ResultSet result = stockStmt.executeQuery()) {
                        if (result.next()) {
                            stockQuantity = result.getInt("quantidade");
                        } else {
                            db.rollback();
                            return;
                        }
                    }
                }

                if (quantity > stockQuantity) {
                    db.rollback();
                    return;
                }

                try (PreparedStatement insertCart = db.prepareStatement(
                        "INSERT INTO carrinho (usuario, medicamento, quantidade, valor) VALUES (?, ?, ?, ?)");
                     PreparedStatement updateStock = db.prepareStatement(
                             "UPDATE medicamentos SET quantidade = ? WHERE nome = ?")) {
                    insertCart.setString(1, user);
                    insertCart.setString(2, medicationName);
                    insertCart.setInt(3, quantity);
                    insertCart.setFloat(4, totalPrice);
                    insertCart.executeUpdate();

                    updateStock.setInt(1, stockQuantity - quantity);
                    updateStock.setString(2, medicationName);
                    updateStock.executeUpdate();
                }

                db.commit();
            } catch (SQLException e) {
                db.rollback();
                throw e;
            } finally {
                db.setAutoCommit(true);
            }
        }
    }

    public void createOrder(String user, String medicationName, int quantity, float totalPrice, String date, String phone, String status)
            throws SQLException {
        try (Connection db = DatabaseConnection.open();
             PreparedStatement statement = db.prepareStatement(
                     "INSERT INTO encomendas (usuario, medicamento, quantidade, valor, telefone, data, status) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            statement.setString(1, user);
            statement.setString(2, medicationName);
            statement.setInt(3, quantity);
            statement.setFloat(4, totalPrice);
            statement.setString(5, phone);
            statement.setString(6, date);
            statement.setString(7, status);
            statement.executeUpdate();
        }
    }

    public List<CarrinhoTable> listCartByUser(String user) throws SQLException {
        List<CarrinhoTable> cart = new ArrayList<>();
        try (Connection db = DatabaseConnection.open();
             PreparedStatement statement = db.prepareStatement("SELECT * FROM carrinho WHERE usuario = ?")) {
            statement.setString(1, user);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    cart.add(new CarrinhoTable(
                            result.getInt("id"),
                            result.getString("usuario"),
                            result.getString("medicamento"),
                            result.getInt("quantidade"),
                            result.getFloat("valor")
                    ));
                }
            }
        }
        return cart;
    }

    public float sumCartTotal(String user) throws SQLException {
        try (Connection db = DatabaseConnection.open();
             PreparedStatement statement = db.prepareStatement("SELECT SUM(valor) AS soma_total FROM carrinho WHERE usuario = ?")) {
            statement.setString(1, user);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return result.getFloat("soma_total");
                }
            }
        }
        return 0F;
    }

    public void confirmPurchase(String user, String medicationName, int quantity, float totalPrice, String date) throws SQLException {
        try (Connection db = DatabaseConnection.open()) {
            db.setAutoCommit(false);
            try (PreparedStatement insertRegistro = db.prepareStatement(
                    "INSERT INTO registros (usuario, medicamento, quantidade, valor, data) VALUES (?, ?, ?, ?, ?)");
                 PreparedStatement deleteCarrinho = db.prepareStatement("DELETE FROM carrinho WHERE usuario = ?")) {
                insertRegistro.setString(1, user);
                insertRegistro.setString(2, medicationName);
                insertRegistro.setInt(3, quantity);
                insertRegistro.setFloat(4, totalPrice);
                insertRegistro.setString(5, date);
                insertRegistro.executeUpdate();

                deleteCarrinho.setString(1, user);
                deleteCarrinho.executeUpdate();
                db.commit();
            } catch (SQLException e) {
                db.rollback();
                throw e;
            } finally {
                db.setAutoCommit(true);
            }
        }
    }

    public void removeCartItemAndRestoreStock(int cartId) throws SQLException {
        try (Connection db = DatabaseConnection.open()) {
            db.setAutoCommit(false);
            try {
                String medication = null;
                int cartQuantity = 0;

                try (PreparedStatement cartStmt = db.prepareStatement(
                        "SELECT medicamento, quantidade FROM carrinho WHERE id = ?")) {
                    cartStmt.setInt(1, cartId);
                    try (ResultSet result = cartStmt.executeQuery()) {
                        if (result.next()) {
                            medication = result.getString("medicamento");
                            cartQuantity = result.getInt("quantidade");
                        } else {
                            db.rollback();
                            return;
                        }
                    }
                }

                int currentStock = 0;
                try (PreparedStatement stockStmt = db.prepareStatement(
                        "SELECT quantidade FROM medicamentos WHERE nome = ?")) {
                    stockStmt.setString(1, medication);
                    try (ResultSet result = stockStmt.executeQuery()) {
                        if (result.next()) {
                            currentStock = result.getInt("quantidade");
                        }
                    }
                }

                try (PreparedStatement updateStock = db.prepareStatement(
                        "UPDATE medicamentos SET quantidade = ? WHERE nome = ?");
                     PreparedStatement deleteCart = db.prepareStatement(
                             "DELETE FROM carrinho WHERE id = ?")) {
                    updateStock.setInt(1, currentStock + cartQuantity);
                    updateStock.setString(2, medication);
                    updateStock.executeUpdate();

                    deleteCart.setInt(1, cartId);
                    deleteCart.executeUpdate();
                }

                db.commit();
            } catch (SQLException e) {
                db.rollback();
                throw e;
            } finally {
                db.setAutoCommit(true);
            }
        }
    }
}
