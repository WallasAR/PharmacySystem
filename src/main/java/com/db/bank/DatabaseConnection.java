package com.db.bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseConnection {

    private DatabaseConnection() {
    }

    public static Connection open() throws SQLException {
        return DriverManager.getConnection(
                DatabaseConfig.jdbcUrl(),
                DatabaseConfig.user(),
                DatabaseConfig.password()
        );
    }
}
