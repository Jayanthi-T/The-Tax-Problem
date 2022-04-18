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
//        Assertions.assertTrue(productService.isProductExists(pro.getId()));
    }


    @Test
    public void calculateTotalPriceTest(){
        Product pro1 = new Product("book",2,12.49);
        Double expectedTotalPrice = 24.98;
        Double actualTotalPrice = productService.calculateTotalPrice();
        assertEquals(expectedTotalPrice,actualTotalPrice);
    }

    @Test
    public void getProductDetailsTest(){
        String newProType = "music CD";
        Product pro = productService.getProductDetails(newProType);
        assertThat(pro.getProductType()).isEqualTo(newProType);
    }



    @Test
//    @Rollback(value = false)
    public void deleteProductByIdTest(){
        String proType = "new product";
        Product pro = productService.getProductDetails(proType);
        System.out.println(pro);
        productService.deleteProductById(pro.getId());
//        assertNull(pro);
//        Product deleted = null;
//        Optional<Product> optionalProduct = Optional.ofNullable(productRepository.getByProductType("music CD"));
//        if(optionalProduct.isPresent()){
//            deleted = optionalProduct.get();
//        }
        assertThat(pro.getId()).isNull();
//        Product deletedProduct = productRepository.getByProductType("music CD");
//        assertNull(deletedProduct);
    }


}
