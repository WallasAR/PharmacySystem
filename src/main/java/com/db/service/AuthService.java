package com.db.service;

import com.db.repository.AuthRepository;

import java.sql.SQLException;

public class AuthService {
    private final AuthRepository authRepository = new AuthRepository();

    public void insertAdmin(String user, String pass) throws SQLException {
        authRepository.insertAdmin(user, pass);
    }
}
