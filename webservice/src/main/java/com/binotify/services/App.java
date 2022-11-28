package com.binotify.services;

import javax.xml.ws.Endpoint;

import com.binotify.services.impl.APIKeyServiceImpl;
import com.binotify.services.impl.ChocolateServiceImpl;
import com.binotify.services.impl.SubscriptionServiceImpl;

import io.github.cdimascio.dotenv.Dotenv;

public class App {
    private static Dotenv dotenv = Dotenv.load();
    private static String host = dotenv.get("HOST");
    private static String port = dotenv.get("PORT");

    public static void main(String[] args) {
        Endpoint.publish(String.format("http://%s:%s/webservice/chocolate", host, port), new ChocolateServiceImpl());
        Endpoint.publish(String.format("http://%s:%s/webservice/subscription", host, port),
                new SubscriptionServiceImpl());
        Endpoint.publish(String.format("http://%s:%s/webservice/generatekey", host, port), new APIKeyServiceImpl());

    }
}
