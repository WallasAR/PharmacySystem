package com.db.bank;

public final class DatabaseConfig {

    private DatabaseConfig() {
    }

    public static String host() {
        return read("DB_HOST", "localhost");
    }

    public static String port() {
        return read("DB_PORT", "3306");
    }

    public static String name() {
        return read("DB_NAME", "farmacia");
    }

    public static String user() {
        return read("DB_USER", "adm");
    }

    public static String password() {
        return read("DB_PASSWORD", "1234");
    }

    public static String jdbcUrl() {
        return "jdbc:mysql://" + host() + ":" + port() + "/" + name()
                + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=America/Sao_Paulo";
    }

    private static String read(String key, String defaultValue) {
        String value = System.getenv(key);
        if (value == null || value.isBlank()) {
            return defaultValue;
        }
        return value;
    }
}
