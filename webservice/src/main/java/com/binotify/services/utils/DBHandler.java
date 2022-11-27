package com.binotify.services.utils;

import java.sql.*;
import io.github.cdimascio.dotenv.Dotenv;

public class DBHandler {
    private static Connection connection = null;
    private static DBHandler instance;
    private static Dotenv dotenv = Dotenv.load();
    private static String DB_URL = dotenv.get("DB_URL");
    private static String DB_Username = dotenv.get("DB_Username");
    private static String DB_Password = dotenv.get("DB_Password");
    private Statement statement;

    private DBHandler() throws SQLException {
        System.out.println("Connecting to MYSQL DB");
        DBHandler.connection = DriverManager.getConnection(DB_URL, DB_Username, DB_Password);
        System.out.println("Database connected!");
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
