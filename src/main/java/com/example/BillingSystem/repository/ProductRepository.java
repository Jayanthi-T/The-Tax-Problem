package com.example.BillingSystem.repository;

import com.example.BillingSystem.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    public Product getByProductType(String productType);
//    public boolean isProductTypeExists(String productType);

}
