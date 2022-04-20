package com.example.BillingSystem.controller;

import com.example.BillingSystem.entity.ProductSummary;
import com.example.BillingSystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.BillingSystem.entity.Product;
import com.example.BillingSystem.service.ProductService;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;
    private ProductRepository productRepository;

    public ProductController(ProductService productService) {
        super();
        this.productService = productService;
    }

//    public ProductController(ProductRepository productRepository) {
//        super();
//        this.productRepository = productRepository;
//    }

    // handler method to handle list products and return mode and view
    @GetMapping("/products")
    public Iterable<Product> listProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/products/new")
    public Product createProductList(@RequestBody Product product) {
//        productService.insert(product);
        return productService.saveProduct(product);
//        return productRepository.save(productList);
    }

    @PostMapping("/products")
    public Product saveProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
//        return productRepository.save(productList);
    }

    @GetMapping("/getTotalPrice")
    public Double CalcTotalPrice(){
        return productService.calculateTotalPrice();
//        return productRepository.getTotalPriceReceipt();
    }

    @GetMapping("/getTotalTax")
    public Double CalcTotalTax(){
        return productService.calculateTotalTax();
//        return productRepository.getTotalTaxReceipt();
    }

    @GetMapping("/getBillSummary")
    public ProductSummary getSummary(){
        return productService.getBillSummary();
    }

    // handler method to handle delete product request
    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable Long id) {
        if(productService.isProductExists(id)) {
            productService.deleteProductById(id);
            return "Deleted the Product successfully!";
        }
        else {
            return "No such Product to delete from your bill!";
        }
    }


}
