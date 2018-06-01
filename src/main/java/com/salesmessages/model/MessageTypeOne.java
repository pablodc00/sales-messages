package com.salesmessages.model;

public class MessageTypeOne {
    
    private String product;
    private Double price;

    
    public MessageTypeOne(String product, Double price) {
        this.product = product;
        this.price = price;
    }

    public String getProduct() {
        return product;
    }
    public void setProduct(String product) {
        this.product = product;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    
    

}
