package com.binotify.services.utils;

import java.sql.*;
import io.github.cdimascio.dotenv.Dotenv;

public class DBHandler {
    private static Connection connection = null;
    private static DBHandler instance;
    private static String DB_URL = "jdbc:mysql://mysql/binotifysoap";
    private static String DB_Username = "root";
    private static String DB_Password = "binotifyspotipai";
    private Statement statement;

    private DBHandler() throws SQLException {
        try {
            DBHandler.DB_URL = System.getenv("DB_URL");
            DBHandler.DB_Username = System.getenv("DB_Username");
            DBHandler.DB_Password = System.getenv("DB_Password");
            if (DB_URL == null) {
                DB_URL = "jdbc:mysql://localhost:3306/binotifysoap";
            }
            if (DB_Username == null) {
                DB_Username = "root";
            }
            if (DB_Password == null) {
                DB_Password = "aditya962";
            }
            System.out.println(DBHandler.DB_URL);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(DBHandler.DB_URL);
        } finally {
            DBHandler.connection = DriverManager.getConnection(DB_URL, DBHandler.DB_Username, DBHandler.DB_Password);
            System.out.println("Connecting to MYSQL DB");
            System.out.println(DBHandler.DB_URL);
            System.out.println("Database connected!");
        }
    }

    public static Connection getConnection() throws SQLException {
        if (instance == null) {
            DBHandler.instance = new DBHandler();
        }
        return connection;
    }

    public boolean executeStatement(String sql) throws SQLException {
        this.statement = connection.createStatement();
        return this.statement.execute(sql);
    }

    public int executeUpdateStatement(String sql) throws SQLException {
        this.statement = connection.createStatement();
        return this.statement.executeUpdate(sql);
    }

    public ResultSet executeQueryStatement(String sql) throws SQLException {
        this.statement = connection.createStatement();
        return this.statement.executeQuery(sql);
    }

    public Statement getStatement() {
        return this.statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }
}
