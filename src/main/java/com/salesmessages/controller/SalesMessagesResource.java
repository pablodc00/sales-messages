package com.salesmessages.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.salesmessages.model.MessageTypeOne;
import com.salesmessages.model.MessageTypeThree;
import com.salesmessages.model.MessageTypeTwo;
import com.salesmessages.model.Operation;
import com.salesmessages.service.MessageProcessingService;

@Path("/salesmessage")
public class SalesMessagesResource {

    private static final Logger LOGGER = Logger.getLogger( SalesMessagesResource.class.getName() );
    
    private MessageProcessingService service = MessageProcessingService.getInstance();
    
    @PUT
    @Path("/messageTypeOne/{product}/{price}")
    public Response messageTypeOne(@PathParam("product") String product, @PathParam("price") Double price) {

        LOGGER.log(Level.INFO, "messageTypeOne - Product: "+ product + ", price: " + price);
        
        MessageTypeOne message = new MessageTypeOne(product, price);        
        if (service.messageTypeOne(message)) {
            return Response.ok().build();
        }
        
        LOGGER.log(Level.WARNING, Status.NOT_ACCEPTABLE.getReasonPhrase());
        return Response.status(Status.NOT_ACCEPTABLE).build();
    }
    
    @PUT
    @Path("/messageTypeTwo/{product}/{price}/{ocurrences}")
    public Response messageTypeTwo(@PathParam("product") String product, @PathParam("price") Double price, 
            @PathParam("ocurrences") int ocurrences) {

        
        LOGGER.log(Level.INFO, "messageTypeTwo - Product: "+ product + ", price: " + price + ", ocurrences: " + ocurrences);
        MessageTypeTwo message = new MessageTypeTwo(product, price, ocurrences);
        
        if (service.messageTypeTwo(message)) {
            return Response.ok().build();
        }
        
        LOGGER.log(Level.WARNING, Status.NOT_ACCEPTABLE.getReasonPhrase());
        return Response.status(Status.NOT_ACCEPTABLE).build();
    }
    
    @PUT
    @Path("/messageTypeThree/{product}/{price}/{operation}")
    public Response messageTypeThree(@PathParam("product") String product, @PathParam("price") Double price, 
            @PathParam("ocurrences") int ocurrences, @PathParam("operation") Operation operation) {
        
        LOGGER.log(Level.INFO, "messageTypeThree - Product: "+ product + ", price: " + price + ", ocurrences: " 
                + ocurrences + ", operation: " + operation);
        MessageTypeThree message = new MessageTypeThree(product, price, operation);
        
        if (service.messageTypeThree(message)) {
            return Response.ok().build();
        }
        
        LOGGER.log(Level.WARNING, Status.NOT_ACCEPTABLE.getReasonPhrase());
        return Response.status(Status.NOT_ACCEPTABLE).build();
    }
}
