package com.binotify.services.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Logger {
    private static Connection db;

    public Logger() {
        try {
            this.db = DBHandler.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Couldn't connect to db");
        }
    }

    public static int createLog(String description, String IP, String endpoint) throws SQLException {
        String query = "INSERT INTO Logging(description, IP, endpoint) VALUES ('%s', '%s', '%s')";
        String sql = String.format(query, description, IP, endpoint);
        Statement statement = db.createStatement();
        return statement.executeUpdate(sql);
    }
}
