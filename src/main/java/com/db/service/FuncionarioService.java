package com.db.service;

import com.db.bank.DatabaseConnection;
import com.db.repository.FuncionarioRepository;
import com.table.view.FuncionarioTable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository = new FuncionarioRepository();

    public void insert(String nome, String sobrenome, String user, String cargo, String cpf, float salario, String senha)
            throws SQLException {
        funcionarioRepository.insert(nome, sobrenome, user, cargo, cpf, salario, senha);
    }

    public void update(String nome, String sobrenome, String user, String cargo, String cpf, float salario, String senha, int id)
            throws SQLException {
        funcionarioRepository.update(nome, sobrenome, user, cargo, cpf, salario, senha, id);
    }

    public void deleteById(int id) throws SQLException {
        funcionarioRepository.deleteById(id);
    }

    public int countAll() throws SQLException {
        return funcionarioRepository.countAll();
    }

    public List<FuncionarioTable> listAll() throws SQLException {
        List<FuncionarioTable> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionarios";
        try (Connection connection = DatabaseConnection.open();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {
            while (result.next()) {
                funcionarios.add(new FuncionarioTable(
                        result.getInt("id"),
                        result.getString("nome"),
                        result.getString("sobrenome"),
                        result.getString("usuario"),
                        result.getString("cargo"),
                        result.getString("cpf"),
                        result.getFloat("salario"),
                        result.getString("senha")
                ));
            }
        }
        return funcionarios;
    }
}
