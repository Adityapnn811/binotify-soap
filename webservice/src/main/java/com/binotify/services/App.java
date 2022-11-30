package com.binotify.services;

import javax.xml.ws.Endpoint;

import com.binotify.services.impl.APIKeyServiceImpl;
import com.binotify.services.impl.SubscriptionServiceImpl;

import io.github.cdimascio.dotenv.Dotenv;

public class App {
    private static String host = "localhost";
    private static String port = "4040";

    public static void main(String[] args) {
        try {
            App.host = System.getenv("HOST");
            App.port = System.getenv("PORT");
            if (App.host == null) {
                App.host = "localhost";
            }
            if (App.port == null) {
                App.port = "4040";
            }
        } catch (Exception e) {
            System.out.println("No .env file found, using default values");
        } finally {

            Endpoint.publish(String.format("http://%s:%s/webservice/subscription", host, port),
                    new SubscriptionServiceImpl());
            Endpoint.publish(String.format("http://%s:%s/webservice/generatekey", host, port),
                    new APIKeyServiceImpl());
        }

    }
}
