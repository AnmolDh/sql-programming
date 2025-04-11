package com.payroll.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbService {
    public static final String dbUrl = System.getenv("DATABASE_URL");
    public static final String dbUser = System.getenv("DATABASE_USER");
    public static final String dbPassword = System.getenv("DATABASE_PASSWORD");

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }
}