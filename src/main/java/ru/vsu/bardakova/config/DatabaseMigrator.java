package ru.vsu.bardakova.config;

import org.flywaydb.core.Flyway;
import ru.vsu.bardakova.ex.AppException;

public class DatabaseMigrator {
    public static void migrate() {
        try {
            Flyway flyway = Flyway.configure().dataSource(DataSourceProvider.getDataSource()).load();
            flyway.migrate();
            System.out.println("БД успешно мигрирована.");
        } catch (Exception e) {
            throw new AppException("Ошибка при миграции БД: " + e.getMessage());
        }
    }
}
