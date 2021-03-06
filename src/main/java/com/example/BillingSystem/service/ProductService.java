package com.example.BillingSystem.service;

import com.example.BillingSystem.entity.Product;
import com.example.BillingSystem.entity.ProductSummary;

import java.util.List;

public interface ProductService {
    Product insert(Product productList);

    List<Product> getAllProducts();

    boolean isProductExists(Long id);

    boolean isProductTypeExists(String productType);

    Product saveProduct(Product product);

    Double calculateTotalPrice();

    Double calculateTotalTax();

    Double[] getBill();
    
    Product getProductById(Long id);

    void deleteProductById(Long id);

//    ProductSummary getBillSummary();
}
