package com.scaler.backendproject.service;

import com.scaler.backendproject.exceptions.ProductNotFoundException;
import com.scaler.backendproject.models.Category;
import com.scaler.backendproject.models.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Long id, String title, String description, Double price, String category, String image );
    Product getSingleProduct(Long id) throws ProductNotFoundException;
    Product[] getAllProducts();
    void updateProduct(Long id, String title, String description, Double price, String category, String image );
    String deleteProduct(Long id);
}
