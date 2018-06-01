package com.salesmessages.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.salesmessages.model.MessageTypeOne;
import com.salesmessages.model.MessageTypeThree;
import com.salesmessages.model.MessageTypeTwo;
import com.salesmessages.model.Operation;

public class MessageProcessingServiceTest {
    
    private MessageProcessingService service;
    private final String APPLE = "apple";

    @Before
    public void setUp() {
        service = MessageProcessingService.getInstance();
    }    
    
    @Test
    public void messageTypeOneTest() {
        MessageTypeOne messageTypeOne = new MessageTypeOne(APPLE, 10D);
        boolean flag = service.messageTypeOne(messageTypeOne);
        assertTrue(flag);
    }
    
    @Test
    public void messageTypeTwoTest() {
        MessageTypeTwo messageTypeTwo = new MessageTypeTwo(APPLE, 10.5D, 5);
        boolean flag = service.messageTypeTwo(messageTypeTwo);
        assertTrue(flag);
    }
    
    @Test
    public void messageTypeThreeTest() {
        MessageTypeThree messageTypeThree = new MessageTypeThree(APPLE, 0.5D, Operation.ADD);
        boolean flag = service.messageTypeThree(messageTypeThree);
        assertTrue(flag);
    }    
    
    @Test
    public void messagesReach50() {
        MessageTypeOne messageTypeOne = new MessageTypeOne(APPLE, 14.5D);
        for (int i = 0; i < 47; i++) {
            service.messageTypeOne(messageTypeOne);
        }
        
        boolean flag = service.messageTypeOne(messageTypeOne);
        assertFalse(flag);
    }
    
}
