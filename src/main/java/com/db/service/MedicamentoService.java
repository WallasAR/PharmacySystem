package com.db.service;

import com.db.repository.MedicamentoRepository;

import java.sql.SQLException;

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
}
