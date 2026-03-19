package com.db.service;

import com.db.bank.DatabaseConnection;
import com.db.repository.MedicamentoRepository;
import com.table.view.MedicamentoTable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoService {
    private final MedicamentoRepository medicamentoRepository = new MedicamentoRepository();

    public void insert(String nome, int quantidade, String tipo, float valor) throws SQLException {
        medicamentoRepository.insert(nome, quantidade, tipo, valor);
    }

    public void update(String nome, int quantidade, String tipo, float valor, int id) throws SQLException {
        medicamentoRepository.update(nome, quantidade, tipo, valor, id);
    }

    public void deleteById(int id) throws SQLException {
        medicamentoRepository.deleteById(id);
    }

    public int countAll() throws SQLException {
        return medicamentoRepository.countAll();
    }

    public List<MedicamentoTable> listAll() throws SQLException {
        List<MedicamentoTable> medicamentos = new ArrayList<>();
        String sql = "SELECT * FROM medicamentos";
        try (Connection connection = DatabaseConnection.open();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {
            while (result.next()) {
                medicamentos.add(new MedicamentoTable(
                        result.getInt("id"),
                        result.getString("nome"),
                        result.getInt("quantidade"),
                        result.getString("tipo"),
                        result.getFloat("valor")
                ));
            }
        }
        return medicamentos;
    }
}
