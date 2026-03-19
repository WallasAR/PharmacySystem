package com.db.repository;

import com.db.bank.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistroRepository {

    public void insert(String usuario, String medicamento, int quantidade, float valor, String data) throws SQLException {
        String sql = "INSERT INTO registros (usuario, medicamento, quantidade, valor, data) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.open();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, usuario);
            statement.setString(2, medicamento);
            statement.setInt(3, quantidade);
            statement.setFloat(4, valor);
            statement.setString(5, data);
            statement.executeUpdate();
        }
    }
}
