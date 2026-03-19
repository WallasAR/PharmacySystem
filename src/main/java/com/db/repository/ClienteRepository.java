package com.db.repository;

import com.db.bank.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteRepository {

    public boolean existsByUsuario(String usuario) throws SQLException {
        String sql = "SELECT 1 FROM cliente WHERE usuario = ? LIMIT 1";
        try (Connection connection = DatabaseConnection.open();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, usuario);
            try (ResultSet result = statement.executeQuery()) {
                return result.next();
            }
        }
    }

    public void insert(String nome, String sobrenome, String usuario, String telefone) throws SQLException {
        String sql = "INSERT INTO cliente (nome, sobrenome, usuario, telefone) VALUES (?,?,?,?)";
        try (Connection connection = DatabaseConnection.open();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nome);
            statement.setString(2, sobrenome);
            statement.setString(3, usuario);
            statement.setString(4, telefone);
            statement.executeUpdate();
        }
    }

    public void update(String nome, String sobrenome, String usuario, String telefone, String id) throws SQLException {
        String sql = "UPDATE cliente SET nome = ?, sobrenome = ?, usuario = ?, telefone = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.open();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nome);
            statement.setString(2, sobrenome);
            statement.setString(3, usuario);
            statement.setString(4, telefone);
            statement.setString(5, id);
            statement.executeUpdate();
        }
    }

    public void deleteById(String id) throws SQLException {
        String sql = "DELETE FROM cliente WHERE id = ?";
        try (Connection connection = DatabaseConnection.open();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            statement.executeUpdate();
        }
    }

    public int countAll() throws SQLException {
        String sql = "SELECT COUNT(id) AS soma_total FROM cliente";
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
