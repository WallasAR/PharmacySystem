package com.db.service;

import com.db.bank.DatabaseConnection;
import com.db.repository.EncomendaRepository;
import com.table.view.EncomendasTable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EncomendaService {
    private final EncomendaRepository encomendaRepository = new EncomendaRepository();

    public void insert(String user, String medicamento, int quantidade, float valor, String data, String fone, String status)
            throws SQLException {
        encomendaRepository.insert(user, medicamento, quantidade, valor, data, fone, status);
    }

    public List<EncomendasTable> listAll() throws SQLException {
        List<EncomendasTable> encomendas = new ArrayList<>();
        String sql = "SELECT * FROM encomendas";
        try (Connection connection = DatabaseConnection.open();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {
            while (result.next()) {
                encomendas.add(new EncomendasTable(
                        result.getInt("id"),
                        result.getString("usuario"),
                        result.getString("medicamento"),
                        result.getInt("quantidade"),
                        result.getFloat("valor"),
                        result.getString("data"),
                        result.getString("telefone"),
                        result.getString("status")
                ));
            }
        }
        return encomendas;
    }

    public void updateStatus(int id, String status) throws SQLException {
        String sql = "UPDATE encomendas SET status = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.open();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, status);
            statement.setInt(2, id);
            statement.executeUpdate();
        }
    }
}
