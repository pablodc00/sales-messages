package com.salesmessages.model;

public class MessageTypeThree extends MessageTypeOne {

    private Operation operation;

    public MessageTypeThree(String product, Double price, Operation operation) {
        super(product, price);
        this.operation = operation;
    }
    
    
    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

}
