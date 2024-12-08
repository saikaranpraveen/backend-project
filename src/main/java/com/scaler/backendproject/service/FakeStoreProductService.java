package com.scaler.backendproject.service;

import com.scaler.backendproject.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {

    @Override
    public Product getSingleProduct(Long id) {
        System.out.println("inside getSingleProduct");
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }
}
