package com.binotify.services.impl;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import com.sun.net.httpserver.HttpExchange;

import com.sun.xml.ws.developer.JAXWSProperties;
import com.binotify.services.models.SubscriptionModel;
import com.binotify.services.models.SubscriptionModelDao;
import com.binotify.services.utils.APIKey;
import com.binotify.services.utils.DBHandler;
import com.binotify.services.utils.Logger;

import java.sql.*;
import java.util.List;

@WebService(endpointInterface = "com.binotify.services.impl.SubscriptionService")
public class SubscriptionServiceImpl implements SubscriptionService {
    @Resource
    WebServiceContext wsContext;
    private static Logger logger = new Logger();

    // Endpoint buat get All subscription Request (PENDING)
    @Override
    public List<SubscriptionModel> getSubscriptionReq(String apiKey) {
        // Nanti alurnya get API Key dan validasi
        try {
            if (Boolean.TRUE.equals(APIKey.checkKey(apiKey))) {
                // System.out.println(res.getString("api_key"));
                // Get IP address dll buat log dulu
                logger.createLog("Mengambil data subscription yang masih pending", this.getReqIP(),
                        this.getReqEndpoint());
                // Baru eksekusi statement
                Connection conn = DBHandler.getConnection();
                Statement statement = conn.createStatement();
                String rawQuery = "SELECT * FROM Subscription WHERE status = 'PENDING';";
                String sql = String.format(rawQuery);
                // Terus lakuin callback
                return SubscriptionModelDao.getSubscriptionReq(statement.executeQuery(sql));
            } else {
                return SubscriptionModelDao.getSubscriptionReq(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return SubscriptionModelDao.getSubscriptionReq(null);
        }
    }

    // Endpoint buat create new subs req
    @Override
    public Boolean createSubscriptionReq(int creatorId, int subscriberId, String apiKey) {
        // Nanti alurnya get API Key dan validasi
        try {
            if (Boolean.TRUE.equals(APIKey.checkKey(apiKey))) {
                // System.out.println(res.getString("api_key"));
                // Get IP address dll buat log dulu
                logger.createLog("Mencoba membuat subscription request baru dengan creatorId " + creatorId
                        + " dan subscriberId " + subscriberId, this.getReqIP(), this.getReqEndpoint());
                // Baru eksekusi statement
                Connection conn = DBHandler.getConnection();
                Statement statement = conn.createStatement();
                String rawQuery = "INSERT INTO Subscription(creator_id, subscriber_id) VALUES (%d, %d)";
                String sql = String.format(rawQuery, creatorId, subscriberId);
                statement.executeUpdate(sql);
                // Terus lakuin callback
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Endpoint buat Approve Subs Req
    @Override
    public Boolean approveSubscriptionReq(int creatorId, int subscriberId, String apiKey) {
        try {
            if (Boolean.TRUE.equals(APIKey.checkKey(apiKey))) {
                // Get IP address dll buat log dulu
                logger.createLog("Menerima subscription request dengan creatorId " + creatorId
                        + " dan subscriberId " + subscriberId, this.getReqIP(), this.getReqEndpoint());
                // Baru eksekusi statement
                Connection conn = DBHandler.getConnection();
                Statement statement = conn.createStatement();
                String rawQuery = "UPDATE Subscription SET status = 'ACCEPTED' WHERE creator_id = %d AND subscriber_id = %d;";
                String sql = String.format(rawQuery, creatorId, subscriberId);
                statement.executeUpdate(sql);
                // Terus lakuin callback
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Endpoint buat Reject Subs Req
    @Override
    public Boolean rejectSubscriptionReq(int creatorId, int subscriberId, String apiKey) {
        try {
            if (Boolean.TRUE.equals(APIKey.checkKey(apiKey))) {
                // Get IP address dll buat log dulu
                logger.createLog("Menolak subscription request dengan creatorId " + creatorId
                        + " dan subscriberId " + subscriberId, this.getReqIP(), this.getReqEndpoint());
                // Baru eksekusi statement
                Connection conn = DBHandler.getConnection();
                Statement statement = conn.createStatement();
                String rawQuery = "UPDATE Subscription SET status = 'REJECTED' WHERE creator_id = %d AND subscriber_id = %d;";
                String sql = String.format(rawQuery, creatorId, subscriberId);
                statement.executeUpdate(sql);
                // Terus lakuin callback
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Endpoint buat Check Subs Req
    @Override
    public List<SubscriptionModel> checkEndpointRequest(int creatorId, int subscriberId, String apiKey) {
        try {
            if (Boolean.TRUE.equals(APIKey.checkKey(apiKey))) {
                // System.out.println(res.getString("api_key"));
                // Get IP address dll buat log dulu
                logger.createLog(
                        "Mengambil data subscription dengan creatorId " + creatorId + " dan subscriberId "
                                + subscriberId,
                        this.getReqIP(),
                        this.getReqEndpoint());
                // Baru eksekusi statement
                Connection conn = DBHandler.getConnection();
                Statement statement = conn.createStatement();
                String rawQuery = "SELECT * FROM Subscription WHERE creator_id = %d AND subscriber_id = %d;";
                String sql = String.format(rawQuery, creatorId, subscriberId);
                // Terus lakuin callback
                return SubscriptionModelDao.getSubscriptionReq(statement.executeQuery(sql));
            } else {
                return SubscriptionModelDao.getSubscriptionReq(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return SubscriptionModelDao.getSubscriptionReq(null);
        }
    }

    public String getReqIP() {
        MessageContext mc = wsContext.getMessageContext();
        HttpExchange req = (HttpExchange) mc.get(JAXWSProperties.HTTP_EXCHANGE);
        String ip = String.format("%s", req.getRemoteAddress());
        return ip;
    }

    public String getReqEndpoint() {
        MessageContext mc = wsContext.getMessageContext();
        HttpExchange req = (HttpExchange) mc.get(JAXWSProperties.HTTP_EXCHANGE);
        String endpoint = String.format("%s", req.getRequestURI());
        return endpoint;
    }
}
