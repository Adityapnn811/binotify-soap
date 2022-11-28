package com.binotify.services.utils;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

public class APIKey {
    public static String generate(final int keyLen) throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(keyLen);
        SecretKey secretKey = keyGen.generateKey();
        byte[] encoded = secretKey.getEncoded();
        return DatatypeConverter.printHexBinary(encoded).toLowerCase();
    }

    public static Boolean checkKey(String apiKey) throws SQLException {
        Connection conn = DBHandler.getConnection();
        Statement statement = conn.createStatement();
        String rawQuery = "SELECT api_key FROM ApiKeys WHERE api_key = '%s';";
        String sql = String.format(rawQuery, apiKey);
        ResultSet res = statement.executeQuery(sql);
        return res.next();
    }
}
