package com.salesmessages.service;

import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.salesmessages.model.MessageTypeOne;
import com.salesmessages.model.MessageTypeThree;
import com.salesmessages.model.MessageTypeTwo;
import com.salesmessages.model.Operation;

public class MessageProcessingService {
    
    private static MessageProcessingService instance = null; 
    private MessageProcessingService() {}
    
    
    public static MessageProcessingService getInstance() {
        if(instance == null) {
           instance = new MessageProcessingService();
        }
        return instance;
     }
    
    private AtomicInteger count = new AtomicInteger(0);    
    private ConcurrentMap<String, Double> accumulatedMap = new ConcurrentHashMap<>();
    Queue<String> historicQueue = new ConcurrentLinkedQueue<String>();


    public boolean messageTypeOne(MessageTypeOne message) {
        if (!this.checkFiftyMessages()) {
            return false;
        }

        accumulatedMap.compute(message.getProduct(), (k, v) -> v == null ? message.getPrice() : v +  message.getPrice());
        historicQueue.add(message.getProduct() + " at " + message.getPrice() + "p");
        
        count.getAndIncrement();
        this.checkTenMessages();
        return true;
    }
    
    
    public boolean messageTypeTwo(MessageTypeTwo message) {
        if (!this.checkFiftyMessages()) {
            return false;
        }
        
        Double total = message.getPrice() * message.getOcurrences();
        accumulatedMap.compute(message.getProduct(), (k, v) -> v == null ?  total : v + total);
        historicQueue.add(message.getOcurrences() + " sales of " + message.getProduct() + " at " + message.getPrice() + "p each");
        
        count.getAndIncrement();
        this.checkTenMessages();
        return true;
    }
    
    
    public boolean messageTypeThree(MessageTypeThree message) {
        if (!checkFiftyMessages()) {
            return false;
        }
        accumulatedMap.computeIfPresent(message.getProduct(), 
                (num, val) -> this.getOperationResult(val, message));
        historicQueue.add(message.getOperation() + " " + message.getPrice() + "p to each sale of " + message.getProduct() + "s");
        
        count.getAndIncrement();
        this.checkTenMessages();        
        return true;
    }
    
    private Double getOperationResult(Double currentValue, MessageTypeThree message) {
        Double result = 0D;
        if (Operation.ADD.equals(message.getOperation())) {
            result = currentValue + message.getPrice(); 
        } else if (Operation.SUBTRACT.equals(message.getOperation())) {
            if (currentValue > message.getPrice()) {
                result = currentValue - message.getPrice();
            }
        } else if (Operation.MULTIPLY.equals(message.getOperation())) {
            result = currentValue * message.getPrice();
        }
        
        return result;
    }
    
    
    private void checkTenMessages() {
        if ((count.get() % 10) == 0) {
            System.out.println(
                    "Number of sales of each product and their total value:");
            this.accumulatedMap
                .entrySet()
                .forEach(entry -> 
                    System.out.println("Product: " + entry.getKey() + ", Total value: " + entry.getValue())
            );
        }
    }
    
    private boolean checkFiftyMessages() {
        if (count.get() == 50) {
            System.out.println("Application is paused. It does not accept new messages.");
            
            System.out.println(
                    "Adjustments that have been made to each sale type:");
            
            this.historicQueue
                .forEach(e -> System.out.println(e));
            return false;
        }
        return true;
    }
        
}
