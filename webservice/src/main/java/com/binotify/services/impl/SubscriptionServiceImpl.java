package com.binotify.services.impl;

import javax.jws.WebService;

import com.binotify.services.utils.DBHandler;
import com.binotify.services.utils.Logger;

import java.sql.*;

@WebService(endpointInterface = "com.binotify.services.impl.SubscriptionService")
public class SubscriptionServiceImpl implements SubscriptionService {
    // Endpoint buat get All subscription Request (PENDING)
    @Override
    public String getSubscriptionReq() {
        // Nanti alurnya get API Key dan validasi
        // Get IP address dll buat log dulu
        // Baru eksekusi statement
        // Terus lakuin callback
        return "stub";
    }

    // Endpoint buat create new subs req
    @Override
    public String createSubscriptionReq(int creatorId, int subscriberId, String apiKey) {
        // Nanti alurnya get API Key dan validasi
        // Get IP address dll buat log dulu
        // Baru eksekusi statement
        try {
            Connection conn = DBHandler.getConnection();
            Statement statement = conn.createStatement();
            String rawQuery = "INSERT INTO Subscription(creator_id, subscriber_id) VALUES (%d, %d)";
            String sql = String.format(rawQuery, creatorId, subscriberId);
            int res = statement.executeUpdate(sql);
            return "Insert berhasil dengan row affected: " + res;
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to insert";
        }
        // Terus lakuin callback
    }

    // Endpoint buat Approve Subs Req
    @Override
    public String approveSubscriptionReq(int creatorId, int subscriberId, String apiKey) {
        return "stub";
    }

    // Endpoint buat Reject Subs Req
    @Override
    public String rejectSubscriptionReq(int creatorId, int subscriberId, String apiKey) {
        return "stub";
    }

    // Endpoint buat Check Subs Req
    @Override
    public String checkEndpointRequest(int creatorId, int subscriberId, String apiKey) {
        return "stub";
    }

}
