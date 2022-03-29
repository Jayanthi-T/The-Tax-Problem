package com.example.BillingSystem.service;

import com.example.BillingSystem.entity.ProductList;

import java.util.List;

public interface ProductService {
    List<ProductList> getAllProducts();

    ProductList saveProduct(ProductList productList);

    ProductList getProductById(Long id);

    void deleteProductById(Long id);
}
