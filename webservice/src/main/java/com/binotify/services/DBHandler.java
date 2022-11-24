package com.binotify.services;

import java.sql.*;

public class DBHandler {
    private Connection connection;
    private static String DB_URL = "jdbc:mysql://localhost:3306/binotifysoap";
    private static String DB_Username = "root";
    private static String DB_Password = "aditya962";

    public DBHandler() {
        try {
            System.out.println("Connecting to MYSQL DB");
            this.connection = DriverManager.getConnection(DB_URL, DB_Username, DB_Password);
            System.out.println("Database connected!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Can't connect to database");
        }
    }

    public Connection getConnection() {
        return this.connection;
    }
}
