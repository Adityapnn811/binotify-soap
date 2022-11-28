package com.binotify.services.impl;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import com.sun.net.httpserver.HttpExchange;

import com.sun.xml.ws.developer.JAXWSProperties;
import com.binotify.services.utils.APIKey;
import com.binotify.services.utils.DBHandler;
import com.binotify.services.utils.Logger;

import java.sql.*;

@WebService(endpointInterface = "com.binotify.services.impl.SubscriptionService")
public class SubscriptionServiceImpl implements SubscriptionService {
    @Resource
    WebServiceContext wsContext;
    private static Logger logger = new Logger();

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
