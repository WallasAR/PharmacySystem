package com.db.service;

import com.db.repository.ClienteRepository;

import java.sql.SQLException;

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
}
