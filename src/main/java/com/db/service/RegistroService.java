package com.db.service;

import com.db.repository.RegistroRepository;

import java.sql.SQLException;

public class RegistroService {
    private final RegistroRepository registroRepository = new RegistroRepository();

    public void insert(String user, String medicamento, int quantidade, float valor, String date) throws SQLException {
        registroRepository.insert(user, medicamento, quantidade, valor, date);
    }
}
