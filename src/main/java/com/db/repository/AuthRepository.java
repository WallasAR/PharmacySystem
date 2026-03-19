package com.db.repository;

import com.db.bank.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuthRepository {

    public void insertAdmin(String user, String pass) throws SQLException {
        String sql = "INSERT INTO autenticar (usuario, password) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.open();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user);
            statement.setString(2, pass);
            statement.executeUpdate();
        }
    }
}
