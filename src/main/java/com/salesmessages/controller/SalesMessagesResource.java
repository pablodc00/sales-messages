package com.salesmessages.controller;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.salesmessages.model.MessageTypeOne;
import com.salesmessages.model.MessageTypeThree;
import com.salesmessages.model.MessageTypeTwo;
import com.salesmessages.service.MessageProcessingService;

@Path("/salesmessage")
public class SalesMessagesResource {

    private MessageProcessingService service = MessageProcessingService.getInstance();    
    
    @PUT
    @Path("/messageTypeOne/{product}/{price}")
    public Response messageTypeOne(@PathParam("product") String product, @PathParam("price") Double price) {

        MessageTypeOne message = new MessageTypeOne();
        message.setProduct(product);
        message.setPrice(price);

        if (service.messageTypeOne(message)) {
            return Response.ok().build();
        }
        
        return Response.status(Status.NOT_ACCEPTABLE).build();
    }
    
    @PUT
    @Path("/messageTypeTwo/{product}/{price}/{ocurrences}")
    public Response messageTypeTwo(@PathParam("product") String product, @PathParam("price") Double price, @PathParam("ocurrences") int ocurrences) {

        MessageTypeTwo message = new MessageTypeTwo();
        message.setProduct(product);
        message.setPrice(price);
        message.setOcurrences(ocurrences);
        
        if (service.messageTypeTwo(message)) {
            return Response.ok().build();
        }
        return Response.status(Status.NOT_ACCEPTABLE).build();
    }
    
    @PUT
    @Path("/messageTypeThree/{product}/{price}/{operation}")
    public Response messageTypeThree(@PathParam("product") String product, @PathParam("price") Double price, 
            @PathParam("ocurrences") int ocurrences, @PathParam("operation") Operation operation) {
        
        MessageTypeThree message = new MessageTypeThree();
        message.setProduct(product);
        message.setPrice(price);
        message.setOperation(operation);
        
        if (service.messageTypeThree(message)) {
            return Response.ok().build();
        }
        return Response.status(Status.NOT_ACCEPTABLE).build();
    }
}
