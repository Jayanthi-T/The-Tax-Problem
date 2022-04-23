package com.example.BillingSystem.listener;

import com.example.BillingSystem.entity.Product;
import com.example.BillingSystem.service.ProductService;
import com.example.BillingSystem.util.productMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

@Component
public class ProductListener {

    @Autowired
    ProductService productService;

    @JmsListener(destination = "product.queue")
    public void productListener(String product) {

        try {

            ObjectMapper mapper = new ObjectMapper();
            productMessage map = (productMessage) mapper.readValue(product, productMessage.class);

            if(map.getReqType().equalsIgnoreCase("ADD")) {
                System.out.println(map.getPayload().getProductType()+" added successfully.");
                productService.saveProduct(map.getPayload());
            }
            else if(map.getReqType().equalsIgnoreCase("UPDATE")){
                System.out.println(map.getPayload().getProductType()+" updated successfully.");
                productService.updateProduct(map.getPayload(),map.getId());
            }
            else{
                System.out.println(map.getPayload().getProductType()+" deleted successfully.");
                productService.deleteProductById(map.getId());
            }

        }
        catch (IOException err) {
            System.out.println("Exception : " + err.toString());
        }
    }

}
