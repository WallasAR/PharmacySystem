package com.db.service;

import com.db.bank.DatabaseConnection;
import com.db.repository.ClienteRepository;
import com.table.view.ClienteTable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClienteService {
    private final ClienteRepository clienteRepository = new ClienteRepository();

    public boolean existsByUsuario(String user) throws SQLException {
        return clienteRepository.existsByUsuario(user);
    }

    public void insert(String nome, String sobrenome, String user, String fone) throws SQLException {
        clienteRepository.insert(nome, sobrenome, user, fone);
    }

    public void update(String nome, String sobrenome, String usuario, String fone, String id) throws SQLException {
        clienteRepository.update(nome, sobrenome, usuario, fone, id);
    }

    public void deleteById(String id) throws SQLException {
        clienteRepository.deleteById(id);
    }

    public int countAll() throws SQLException {
        return clienteRepository.countAll();
    }

    public List<ClienteTable> listAll() throws SQLException {
        List<ClienteTable> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try (Connection connection = DatabaseConnection.open();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {
            while (result.next()) {
                clientes.add(new ClienteTable(
                        result.getInt("id"),
                        result.getString("nome"),
                        result.getString("sobrenome"),
                        result.getString("usuario"),
                        result.getString("telefone")
                ));
            }
        }
        return clientes;
    }

    public List<String> listUsernames() throws SQLException {
        List<String> users = new ArrayList<>();
        String sql = "SELECT usuario FROM cliente";
        try (Connection connection = DatabaseConnection.open();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {
            while (result.next()) {
                users.add(result.getString("usuario"));
            }
        }
        return users;
    }
}
