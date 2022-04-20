package com.example.BillingSystem.service;

import com.example.BillingSystem.entity.Product;
import com.example.BillingSystem.repository.ProductRepository;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


//@Component
@SpringBootTest
public class ProductServiceImplTest {
    @Autowired
    ProductService productService;
    ProductRepository productRepository;

    @Test
    public void saveProductTest() {

        Product pro = new Product("new product",2,65.55);
        Product actual = productService.saveProduct(pro);

        assertNotNull(actual);
    }

    @Test
    public void getProductDetailsTest(){
        String newProType = "music CD";
        Product pro = productService.getProductDetails(newProType);
        assertThat(pro.getProductType()).isEqualTo(newProType);
    }

    @Test
    public void getAllProductsTest(){
        assertEquals(6,productService.getAllProducts().size());
    }

    @Test
//    @Rollback(value = false)
    public void deleteProductByIdTest(){
        String proType = "new product";
        Product pro = productService.getProductDetails(proType);
        productService.deleteProductById(pro.getId());
        assertEquals(6,productService.getAllProducts().size());
    }

    @Test
    public void getTotalPriceTest(){
        assertEquals(193.302,productService.calculateTotalPrice());
    }

    @Test
    public void getTotalTaxTest(){
        assertEquals(39.482,productService.calculateTotalTax());
    }

    @Test
    public void getGrossPriceTest(){
        assertEquals(153.82,productService.calculateGrossPrice());
    }

}
