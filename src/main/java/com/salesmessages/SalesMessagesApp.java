package com.salesmessages;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.salesmessages.controller.SalesMessagesResource;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.jersey.api.core.DefaultResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.net.httpserver.HttpServer;

public class SalesMessagesApp {

    private static final URI BASE_URI = URI.create("http://localhost:8080/");
    
    public static void main(String[] args) {
        
        final ResourceConfig resourceConfig = new DefaultResourceConfig(SalesMessagesResource.class);
        ExecutorService service = Executors.newFixedThreadPool(20);
        HttpServer server = null;
        try {
            server = HttpServerFactory.create(BASE_URI, resourceConfig);
            server.setExecutor(service);
            server.start();
            System.out.println("----------- Web Server Started -----------");
        } catch (IllegalArgumentException | IOException e) {
            e.printStackTrace();
        }
    }

}
