package com.example.BillingSystem.service.impl;

import com.example.BillingSystem.entity.ProductList;
import com.example.BillingSystem.repository.ProductRepository;
import com.example.BillingSystem.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        super();
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductList> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public ProductList getProductById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public ProductList saveProduct(ProductList productList) {
         return productRepository.save(productList);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

}
