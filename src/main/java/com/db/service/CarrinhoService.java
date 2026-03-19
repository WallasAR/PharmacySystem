package com.db.service;

import com.db.repository.CarrinhoRepository;

import java.sql.SQLException;

public class CarrinhoService {
    private final CarrinhoRepository carrinhoRepository = new CarrinhoRepository();

    public void insert(String user, String medicamento, int quantidade, float valor) throws SQLException {
        carrinhoRepository.insert(user, medicamento, quantidade, valor);
    }
}
