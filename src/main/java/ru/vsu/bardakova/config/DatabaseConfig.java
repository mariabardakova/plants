package ru.vsu.bardakova.config;

public class DatabaseConfig {

    private static final String DEFAULT_URL = "jdbc:postgresql://localhost:5432/plants_db";
    private static final String DEFAULT_USER = "plants_user";
    private static final String DEFAULT_PASSWORD = "plants_password";

    public static String getUrl() {
        return getEnv("DB_URL", DEFAULT_URL);
    }

    public static String getUser() {
        return getEnv("DB_USER", DEFAULT_USER);
    }

    public static String getPassword() {
        return getEnv("DB_PASSWORD", DEFAULT_PASSWORD);
    }

    private static String getEnv(String key, String defaultValue) {
        String value = System.getenv(key);
        return (value != null && !value.isBlank() ? value : defaultValue);
    }
}
