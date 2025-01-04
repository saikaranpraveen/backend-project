package com.scaler.backendproject.service;

import com.scaler.backendproject.models.Category;
import com.scaler.backendproject.models.Product;

import java.util.List;

public interface CategoryService {
    public List<Category> getAllCategories();
    public Category getCategoryById(Long id);
    public Category saveCategory(String categoryName);
    public List<Product> getProductsByCategoryId(Long id);
}
