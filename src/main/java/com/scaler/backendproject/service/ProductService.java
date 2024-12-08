package com.scaler.backendproject.service;

import com.scaler.backendproject.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long id);
    List<Product> getAllProducts();
    Product createProduct(Product product);
}
