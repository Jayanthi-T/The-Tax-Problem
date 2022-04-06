package com.example.BillingSystem.repository;

import com.example.BillingSystem.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

//    @Autowired
//    public ProductList productList = new ProductList();

//    Double getTotalPriceReceipt();
//    Double getTotalTaxReceipt();

}
