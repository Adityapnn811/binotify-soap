package com.binotify.services.impl;

import javax.jws.WebService;
import com.binotify.services.utils.DBHandler;
import com.binotify.services.utils.APIKey;
import com.binotify.services.utils.Logger;

import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import com.sun.net.httpserver.HttpExchange;
import com.sun.xml.ws.developer.JAXWSProperties;

import javax.annotation.Resource;
import javax.jws.WebParam;
import java.sql.*;

@WebService(endpointInterface = "com.binotify.services.impl.APIKeyServiceImpl")
public class APIKeyServiceImpl implements APIKeyService {
    @Resource
    WebServiceContext wsContext;
    private static Logger logger = new Logger();

    @Override
    public String generateKey(@WebParam(name = "serviceName") String serviceName) {
        try {
            logger.createLog("Mencoba membuat API Key untuk service " + serviceName, this.getReqIP(),
                    this.getReqEndpoint());
        } catch (Exception e) {
            e.printStackTrace();
            return "Something went wrong";
        }
        try {
            String key = "";
            Connection conn = DBHandler.getConnection();
            Statement statement = conn.createStatement();
            for (int i = 0; i < 5; i++) {
                key = APIKey.generate(256);
            }
            String rawQuery = "INSERT INTO ApiKeys (service_name, api_key) VALUES ('%s', '%s');";
            String sql = String.format(rawQuery, serviceName, key);
            statement.executeUpdate(sql);
            return key;
        } catch (Exception e) {
            e.printStackTrace();
            return "Something went wrong";
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
