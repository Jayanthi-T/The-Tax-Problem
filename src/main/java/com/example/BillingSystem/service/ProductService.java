package com.example.BillingSystem.service;

import com.example.BillingSystem.entity.Product;

import java.util.List;

public interface ProductService {
    Product insert(Product productList);

    List<Product> getAllProducts();

    boolean isProductExists(Long id);

    Product saveProduct(Product product);

    Double calculateTotalPrice();

    Double calculateTotalTax();
    
    Product getProductById(Long id);

    void deleteProductById(Long id);
}
