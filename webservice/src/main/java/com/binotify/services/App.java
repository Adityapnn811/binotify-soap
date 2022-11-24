package com.binotify.services;

import javax.xml.ws.Endpoint;

import com.binotify.services.*;
import java.lang.reflect.*;

public class App {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:4848/webservice/chocolate", new ChocolateServiceImpl());

    }
}
