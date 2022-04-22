package com.example.BillingSystem.service.impl;

import com.example.BillingSystem.entity.Product;
import com.example.BillingSystem.entity.ProductSummary;
import com.example.BillingSystem.repository.ProductRepository;
import com.example.BillingSystem.service.ProductService;
import com.fasterxml.jackson.databind.util.ArrayIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        super();
        this.productRepository = productRepository;
    }

    @Override
    public Product insert(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product can not be null or empty.");
        }
        else {
            return productRepository.save(product);
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        if(id==0){
            throw new IllegalArgumentException("ProductId can not be null or empty.");
        }
        else {
            return productRepository.findById(id).get();
        }
    }

    @Override
    public boolean isProductExists(Long id){
        if(id==0){
            throw new IllegalArgumentException("ProductId can not be null or empty.");
        }
        else {
            return productRepository.existsById(id);
        }
    }

    @Override
    public boolean isProductTypeExists(String productType){
        Product product = productRepository.getByProductType(productType);
        if(product!=null){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public Product getProductDetails(String productType){
        if(productType == null){
            throw new IllegalArgumentException("ProductType can not be null or empty.");
        }
        else {
            Product productDetails = new Product();
            productDetails = productRepository.getByProductType(productType);
            return productDetails;
        }
    }

    @Override
    public Product saveProduct(Product product) {

        String ProType = product.getProductType();
        Integer ProQuan = product.getQuantity();
        Product addedProduct = new Product();
        Product existingProduct = new Product();

            if (isProductTypeExists(ProType)) {

                existingProduct = getProductDetails(ProType);

                addedProduct.setId(existingProduct.getId());
                addedProduct.setProductType(product.getProductType());
                addedProduct.setQuantity(existingProduct.getQuantity() + product.getQuantity());
                addedProduct.setUnitPrice(product.getUnitPrice());

                addedProduct = productRepository.save(addedProduct);
            }

            else if (product == null) {
                throw new IllegalArgumentException("Product can not be null or empty.");
            }

            else {
                addedProduct = productRepository.save(product);
            }
        return addedProduct;

//        return productRepository.save(product);

    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProductById(Long id) {
        if(id==0){
            throw new IllegalArgumentException("ProductId can not be null or empty.");
        }
        else {
            productRepository.deleteById(id);
        }
    }

    @Override
    public Double calculateTotalPrice(){

        AtomicReference<Double> taxPerProduct = new AtomicReference<>(0.0);
        AtomicReference<Double> total_tax = new AtomicReference<>(0.0);
        AtomicReference<Double> total_price = new AtomicReference<>(0.0);
        AtomicReference<Double> unitPrice = new AtomicReference<>(0.0);


        List<Product> productList = getAllProducts();
        productList.forEach(product-> {

            if (product.getProductType().contains("imported")) {
                if (product.getProductType().contains("book") || product.getProductType().contains("food") || product.getProductType().contains("medicine")) {
                    taxPerProduct.set(product.getUnitPrice() * 0.05);
                    unitPrice.set((product.getUnitPrice() + taxPerProduct.get()) * product.getQuantity());
                    total_tax.updateAndGet(v -> v + (taxPerProduct.get() * product.getQuantity()));
                    total_price.updateAndGet(v -> v + product.getUnitPrice());

//                    total_price.updateAndGet(v -> v + (product.getUnitPrice() + total_tax.get()));
                }
                else {
                    taxPerProduct.set(product.getUnitPrice() * 0.15);
                    unitPrice.set((product.getUnitPrice() + taxPerProduct.get()) * product.getQuantity());
                    total_tax.updateAndGet(v -> v + (taxPerProduct.get() * product.getQuantity()));
                    total_price.updateAndGet(v -> v + product.getUnitPrice());
                }
            }
            else {
                if (product.getProductType().contains("book") || product.getProductType().contains("food") || product.getProductType().contains("medicine")) {
                    unitPrice.set((product.getUnitPrice() + taxPerProduct.get()) * product.getQuantity());
                    total_price.updateAndGet(v -> v + product.getUnitPrice());
                }
                else {
                    taxPerProduct.set(product.getUnitPrice() * 0.10);
                    unitPrice.set((product.getUnitPrice() + taxPerProduct.get()) * product.getQuantity());
                    total_tax.updateAndGet(v -> v + (taxPerProduct.get() * product.getQuantity()));
                    total_price.updateAndGet(v -> v + product.getUnitPrice());
                }
            }

        });

        return total_price.get() + total_tax.get();

    }
    @Override
    public Double calculateTotalTax(){

        AtomicReference<Double> total_tax = new AtomicReference<>(0.0);
        AtomicReference<Double> total_price = new AtomicReference<>(0.0);
        AtomicReference<Double> taxPerProduct = new AtomicReference<>(0.0);
        AtomicReference<Double> unitPrice = new AtomicReference<>(0.0);

        List<Product> productList = getAllProducts();
        productList.forEach(product-> {


            if (product.getProductType().contains("imported")) {
                if (product.getProductType().contains("book") || product.getProductType().contains("food") || product.getProductType().contains("medicine")) {
                    taxPerProduct.set(product.getUnitPrice() * 0.05);
                    unitPrice.set((product.getUnitPrice() + taxPerProduct.get()) * product.getQuantity());
                    total_tax.updateAndGet(v -> v + (taxPerProduct.get() * product.getQuantity()));
                    total_price.updateAndGet(v -> v + product.getUnitPrice());
                }
                else {
                    taxPerProduct.set(product.getUnitPrice() * 0.15);
                    unitPrice.set((product.getUnitPrice() + taxPerProduct.get()) * product.getQuantity());
                    total_tax.updateAndGet(v -> v + (taxPerProduct.get() * product.getQuantity()));
                    total_price.updateAndGet(v -> v + product.getUnitPrice());
                }
            }
            else {
                if (product.getProductType().contains("book") || product.getProductType().contains("food") || product.getProductType().contains("medicine")) {
                    unitPrice.set((product.getUnitPrice() + taxPerProduct.get()) * product.getQuantity());
                    total_price.updateAndGet(v -> v + product.getUnitPrice());
                }
                else {
                    taxPerProduct.set(product.getUnitPrice() * 0.10);
                    unitPrice.set((product.getUnitPrice() + taxPerProduct.get()) * product.getQuantity());
                    total_tax.updateAndGet(v -> v + (taxPerProduct.get() * product.getQuantity()));
                    total_price.updateAndGet(v -> v + product.getUnitPrice());
                }
            }

        });

        return total_tax.get();

    }
    @Override
    public Double calculateGrossPrice(){
        Double total_tax = calculateTotalTax();
        Double total_price = calculateTotalPrice();
        Double gross_price = total_price - total_tax;
        return gross_price;
    }

    @Override
    public ProductSummary getBillSummary(){
        ProductSummary productsSummary = new ProductSummary();

        List<Product> productsList = (List<Product>) productRepository.findAll();
        if(productsList == null){
            throw new IllegalArgumentException("ProductsList can not be null or empty.");
        }
        else {
            productsSummary.setProductsSummary(productsList);

            productsSummary.setTotalTax(calculateTotalTax());
            productsSummary.setTotalPrice(calculateTotalPrice());
            productsSummary.setGrossPrice(calculateGrossPrice());

            return productsSummary;
        }
    }

}
