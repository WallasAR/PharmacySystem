package com.db.service;

import com.db.repository.EncomendaRepository;

import java.sql.SQLException;

public class EncomendaService {
    private final EncomendaRepository encomendaRepository = new EncomendaRepository();

    public void insert(String user, String medicamento, int quantidade, float valor, String data, String fone, String status)
            throws SQLException {
        encomendaRepository.insert(user, medicamento, quantidade, valor, data, fone, status);
    }
}
