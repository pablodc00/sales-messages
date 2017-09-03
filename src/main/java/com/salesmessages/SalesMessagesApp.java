package com.salesmessages;

import java.io.IOException;
import java.net.URI;

import com.salesmessages.controller.SalesMessagesResource;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.jersey.api.core.DefaultResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.net.httpserver.HttpServer;

public class SalesMessagesApp {

    private static final URI BASE_URI = URI.create("http://localhost:8080/");
    
    public static void main(String[] args) {
        
        final ResourceConfig resourceConfig = new DefaultResourceConfig(SalesMessagesResource.class);
        HttpServer server = null;
        try {
            server = HttpServerFactory.create(BASE_URI, resourceConfig);
            server.start();
            Thread.currentThread().join();
        } catch (InterruptedException | IllegalArgumentException | IOException e) {
            e.printStackTrace();
        }
    }

}
