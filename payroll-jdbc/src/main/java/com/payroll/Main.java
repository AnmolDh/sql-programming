package com.payroll;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static final String dbUrl = System.getenv("DATABASE_URL");
    public static final String dbUser = System.getenv("DATABASE_USER");
    public static final String dbPassword = System.getenv("DATABASE_PASSWORD");

    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

            boolean isReachable = conn.isValid(1);

            System.out.println("Connected to database: " + isReachable);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}