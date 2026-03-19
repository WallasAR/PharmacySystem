package com.db.service;

import com.db.bank.DatabaseConnection;
import com.db.repository.RegistroRepository;
import com.table.view.RecordTable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RegistroService {
    private final RegistroRepository registroRepository = new RegistroRepository();

    public void insert(String user, String medicamento, int quantidade, float valor, String date) throws SQLException {
        registroRepository.insert(user, medicamento, quantidade, valor, date);
    }

    public List<RecordTable> listAll() throws SQLException {
        List<RecordTable> registros = new ArrayList<>();
        String sql = "SELECT * FROM registros";
        try (Connection connection = DatabaseConnection.open();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {
            while (result.next()) {
                registros.add(new RecordTable(
                        result.getInt("id"),
                        result.getString("usuario"),
                        result.getString("medicamento"),
                        result.getInt("quantidade"),
                        result.getFloat("valor"),
                        result.getString("data")
                ));
            }
        }
        return registros;
    }
}
