package ru.vsu.bardakova.config;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceProvider {

    private static DataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            PGSimpleDataSource ds = new PGSimpleDataSource();
            ds.setUrl(DatabaseConfig.getUrl());
            ds.setUser(DatabaseConfig.getUser());
            ds.setPassword(DatabaseConfig.getPassword());
            dataSource = ds;
        }
        return dataSource;
    }

    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }
}
