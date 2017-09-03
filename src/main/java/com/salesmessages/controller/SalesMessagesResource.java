package com.salesmessages.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.salesmessages.model.MessageTypeOne;
import com.salesmessages.model.MessageTypeThree;
import com.salesmessages.model.MessageTypeTwo;
import com.salesmessages.service.MessageProcessingService;

@Path("/salesmessage")
@Consumes(MediaType.APPLICATION_JSON)
public class SalesMessagesResource {

    private MessageProcessingService service = new MessageProcessingService();    
    
    @POST
    @Path("/messageTypeOne")
    public Response messageTypeOne(MessageTypeOne message) {
        if (service.messageTypeOne(message)) {
            return Response.ok().build();
        }
        return Response.status(Status.NOT_ACCEPTABLE).build();
    }
    
    @POST
    @Path("/messageTypeTwo")
    public Response messageTypeTwo(MessageTypeTwo message) {
        return null;
    }
    
    @POST
    @Path("/messageTypeThree")    
    public Response messageTypeThree(MessageTypeThree message) {
        return null;
    }
}
