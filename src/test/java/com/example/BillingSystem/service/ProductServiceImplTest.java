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

import static org.junit.jupiter.api.Assertions.*;

//@DataJpaTest
//@Component
@SpringBootTest
public class ProductServiceImplTest {
    @Autowired
    ProductService productService;
    ProductRepository productRepository;

    @Test
    public void saveProductTest() {

        Product pro = new Product("product to test save method",2,65.55);
        productService.saveProduct(pro);

        assertNotNull(pro);
//        Assertions.assertTrue(productService.isProductExists(pro.getId()));
    }


//    @Test
//    public void calculateTotalPriceTest(){
//        Product pro1 = new Product("book",2,12.49);
//        Double expectedTotalPrice = 24.98;
//        Double actualTotalPrice = productService.calculateTotalPrice();
//        assertEquals(expectedTotalPrice,actualTotalPrice);
//    }

    @Test
//    @Rollback(value = false)
    public void deleteProductByIdTest(){
        Product pro = productRepository.getByProductType("new product for test");
        productRepository.deleteById(pro.getId());
        Product deleted = null;
        Optional<Product> optionalProduct = Optional.ofNullable(productRepository.getByProductType("music CD"));
        if(optionalProduct.isPresent()){
            deleted = optionalProduct.get();
        }
        assertNull(deleted);
//        Assertions.assertThat(deleted).isNull();

//        Product deletedProduct = productRepository.getByProductType("music CD");
//        assertNull(deletedProduct);
    }


}
