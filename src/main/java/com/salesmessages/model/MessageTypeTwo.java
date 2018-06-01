package com.salesmessages.model;

public class MessageTypeTwo extends MessageTypeOne {

    private int ocurrences;

    public MessageTypeTwo(String product, Double price, int ocurrences) {
        super(product, price);
        this.ocurrences = ocurrences;
    }

    public int getOcurrences() {
        return ocurrences;
    }

    public void setOcurrences(int ocurrences) {
        this.ocurrences = ocurrences;
    }
    

}
