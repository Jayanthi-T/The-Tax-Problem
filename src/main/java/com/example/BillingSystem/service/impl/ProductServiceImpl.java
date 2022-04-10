package com.example.BillingSystem.service.impl;

import com.example.BillingSystem.entity.Product;
import com.example.BillingSystem.repository.ProductRepository;
import com.example.BillingSystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicReference;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        super();
        this.productRepository = productRepository;
    }

    @Override
    public Product insert(Product product){
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public boolean isProductExists(Long id){
        return productRepository.existsById(id);
    }



    @Override
    public Product saveProduct(Product product) {
//        if(product.getProductType() != productRepository.)
         return productRepository.save(product);
    }


    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
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
    public Double[] getBill(){

        Double bill[];
        bill = new Double[3];

        Double total_tax = calculateTotalTax();
        Double total_price = calculateTotalPrice();
        Double gross_price = total_price - total_tax;

        bill[0] = total_tax;
        bill[1] = total_price;
        bill[2] = gross_price;

        return bill;
    }

}
