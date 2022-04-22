package com.example.BillingSystem.util;

import com.example.BillingSystem.entity.Product;

public class productMessage {
     Long id;
     String reqType;
     Product payload = new Product();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReqType() {
        return reqType;
    }
    public void setReqType(String reqType) {
        this.reqType = reqType;
    }

    public Product getPayload() {
        return payload;
    }
    public void setPayload(Product payload) {
        this.payload = payload;
    }
}
