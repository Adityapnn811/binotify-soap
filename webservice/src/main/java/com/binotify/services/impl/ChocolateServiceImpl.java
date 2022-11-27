package com.binotify.services.impl;

import javax.jws.WebService;

import com.binotify.services.utils.DBHandler;

import java.sql.*;

@WebService(endpointInterface = "com.binotify.services.impl.ChocolateService")
public class ChocolateServiceImpl implements ChocolateService {
    @Override
    public String createChocolateDatabase() {
        try {
            Connection conn = DBHandler.getConnection();
            Statement statement = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS chocolate (id int not null auto_increment primary key, name varchar(255) not null, price int not null)";
            int res = statement.executeUpdate(sql);
            return "Create table succes with return value : " + res;
        } catch (Exception e) {
            e.printStackTrace();
            return "Something went wrong";
        }
    }

    @Override
    public String addChocolateDatabase(String name, int price) {
        try {
            Connection conn = DBHandler.getConnection();
            Statement statement = conn.createStatement();
            String sql = "INSERT INTO chocolate(name, price) VALUES('%s', %d);";
            String bind = String.format(sql, name, price);
            int res = statement.executeUpdate(bind);
            return "Added new chocolate success with return value : " + res;
        } catch (Exception e) {
            e.printStackTrace();
            return "Something went wrong";
        }
    }
}
