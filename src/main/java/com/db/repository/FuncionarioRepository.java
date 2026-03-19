package com.db.repository;

import com.db.bank.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FuncionarioRepository {

    public void insert(String nome, String sobrenome, String usuario, String cargo, String cpf, float salario, String senha) throws SQLException {
        String sql = "INSERT INTO funcionarios (nome, sobrenome, usuario, cargo, cpf, salario, senha) VALUES (?,?,?,?,?,?,?)";
        try (Connection connection = DatabaseConnection.open();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nome);
            statement.setString(2, sobrenome);
            statement.setString(3, usuario);
            statement.setString(4, cargo);
            statement.setString(5, cpf);
            statement.setFloat(6, salario);
            statement.setString(7, senha);
            statement.executeUpdate();
        }
    }

    public void update(String nome, String sobrenome, String usuario, String cargo, String cpf, float salario, String senha, int id) throws SQLException {
        String sql = "UPDATE funcionarios SET nome = ?, sobrenome = ?, usuario = ?, cargo = ?, cpf = ?, salario = ?, senha = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.open();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nome);
            statement.setString(2, sobrenome);
            statement.setString(3, usuario);
            statement.setString(4, cargo);
            statement.setString(5, cpf);
            statement.setFloat(6, salario);
            statement.setString(7, senha);
            statement.setInt(8, id);
            statement.executeUpdate();
        }
    }

    public void deleteById(int id) throws SQLException {
        String sql = "DELETE FROM funcionarios WHERE id = ?";
        try (Connection connection = DatabaseConnection.open();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public int countAll() throws SQLException {
        String sql = "SELECT COUNT(id) AS soma_total FROM funcionarios";
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
