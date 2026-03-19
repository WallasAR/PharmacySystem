package com.db.repository;

import com.db.bank.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicamentoRepository {

    public void insert(String nome, int quantidade, String tipo, float valor) throws SQLException {
        String sql = "INSERT INTO medicamentos (nome, quantidade, tipo, valor) VALUES (?,?,?,?)";
        try (Connection connection = DatabaseConnection.open();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nome);
            statement.setInt(2, quantidade);
            statement.setString(3, tipo);
            statement.setFloat(4, valor);
            statement.executeUpdate();
        }
    }

    public void update(String nome, int quantidade, String tipo, float valor, int id) throws SQLException {
        String sql = "UPDATE medicamentos SET nome = ?, quantidade = ?, tipo = ?, valor = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.open();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nome);
            statement.setInt(2, quantidade);
            statement.setString(3, tipo);
            statement.setFloat(4, valor);
            statement.setInt(5, id);
            statement.executeUpdate();
        }
    }

    public void deleteById(int id) throws SQLException {
        String sql = "DELETE FROM medicamentos WHERE id = ?";
        try (Connection connection = DatabaseConnection.open();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public int countAll() throws SQLException {
        String sql = "SELECT COUNT(id) AS soma_total FROM medicamentos";
        try (Connection connection = DatabaseConnection.open();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {
            if (result.next()) {
                return result.getInt("soma_total");
            }
            return 0;
        }
    }
}
