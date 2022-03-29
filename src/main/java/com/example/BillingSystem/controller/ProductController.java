package com.example.BillingSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.BillingSystem.entity.ProductList;
import com.example.BillingSystem.service.ProductService;

@Controller
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        super();
        this.productService = productService;
    }

    // handler method to handle list products and return mode and view
    @GetMapping("/products")
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @GetMapping("/products/new")
    public String createProductList(Model model) {
        ProductList productList = new ProductList();
        model.addAttribute("product",productList);
        return "create_product";
    }

//    @RequestMapping("/Productcontroller")
    @PostMapping("/products")
    public String saveProduct(@ModelAttribute("product") ProductList productList) {
        productService.saveProduct(productList);
        return "redirect:/products";
    }

    @GetMapping("/TaxAndTotal")
    public String CalcTaxandTotal(@ModelAttribute("product") ProductList productList, BindingResult TaxAndTotal, Model model)
    {
        model.addAttribute("ProductList",productList);
        return "TaxAndTotal";
    }

    // handler method to handle delete product request
    @GetMapping("/products/{id}")
    public String deleteProduct(@PathVariable Long id) {
        this.productService.deleteProductById(id);
        return "redirect:/products";
    }


}
