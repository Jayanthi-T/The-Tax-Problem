package com.example.BillingSystem.entity;

import java.util.ArrayList;
import java.util.List;

public class ProductSummary {
    private Double TotalTax;
    private Double TotalPrice;
    private Double GrossPrice;

    List<Product> productsSummary;

    public List<Product> getProductsSummary(){
        return productsSummary;
    }
    public void setProductsSummary(List<Product> productsSummary){
        this.productsSummary = productsSummary;
    }

    public Double getTotalTax() {
        return TotalTax;
    }
    public void setTotalTax(Double TotalTax) {
        this.TotalTax = TotalTax;
    }

    public Double getTotalPrice() {
        return TotalPrice;
    }
    public void setTotalPrice(Double TotalPrice) {
        this.TotalPrice = TotalPrice;
    }

    public Double getGrossPrice() {
        return GrossPrice;
    }
    public void setGrossPrice(Double GrossPrice) {
        this.GrossPrice = GrossPrice;
    }

}
