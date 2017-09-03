package com.salesmessages.service;

import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.salesmessages.controller.Operation;
import com.salesmessages.model.MessageTypeOne;
import com.salesmessages.model.MessageTypeThree;
import com.salesmessages.model.MessageTypeTwo;

public class MessageProcessingService {
    
    private AtomicInteger count = new AtomicInteger(0);    
    private ConcurrentMap<String, Double> accumulatedMap = new ConcurrentHashMap<>();
    Queue<String> historicQueue = new ConcurrentLinkedQueue<String>();


    public boolean messageTypeOne(MessageTypeOne message) {
        if (!checkFiftyMessages()) {
            return false;
        }

        accumulatedMap.compute(message.getProduct(), (k, v) -> v == null ? message.getPrice() : v +  message.getPrice());
        historicQueue.add(message.getProduct() + " at " + message.getPrice() + "p");
        count.incrementAndGet();
        
        return true;
    }
    
    
    public boolean messageTypeTwo(MessageTypeTwo message) {
        if (!checkFiftyMessages()) {
            return false;
        }
        
        Double total = message.getPrice() * message.getOcurrences();
        accumulatedMap.compute(message.getProduct(), (k, v) -> v == null ?  total : v + total);
        historicQueue.add(message.getOcurrences() + " sales of " + message.getProduct() + " at " + message.getPrice() + "p each");
        count.incrementAndGet();
        
        return true;
    }
    
    
    public boolean messageTypeThree(MessageTypeThree message) {
        if (!checkFiftyMessages()) {
            return false;
        }

        
        
        return true;
    }
    
    private Double getOperationResult(Double currentValue, MessageTypeThree message) {
        Double result = 0D;
        if (Operation.ADD.equals(message.getOperation())) {
            result = currentValue + message.getPrice(); 
        } else if (Operation.SUBTRACT.equals(message.getOperation())) {
            result = currentValue - message.getPrice();
        } else if (Operation.MULTIPLY.equals(message.getOperation())) {
            result = currentValue * message.getPrice();
        }
        
        return result;
    }
    
    
    private void checkTenMessages() {
        if ((count.get() % 10) == 0) {
            System.out.println(
                    "number of sales of each product and their total value");
            this.accumulatedMap
                .entrySet()
                .forEach(entry -> 
                    System.out.println("Product: " + entry.getKey() + " Total value: " + entry.getValue())
            );
        }
    }
    
    private boolean checkFiftyMessages() {
        if (count.get() == 50) {
            System.out.println(
                    "Adjustments that have been made to each sale type");
            
            this.historicQueue
                .forEach(e -> System.out.println(e));
            return false;
        }
        return true;
    }
        
}
