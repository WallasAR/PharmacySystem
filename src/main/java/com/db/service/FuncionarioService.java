package com.db.service;

import com.db.repository.FuncionarioRepository;

import java.sql.SQLException;

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
}
