package com.scaler.backendproject.service;

import com.scaler.backendproject.controller.CategoryController;
import com.scaler.backendproject.models.Category;
import com.scaler.backendproject.models.Product;
import com.scaler.backendproject.repository.CategoryRepository;
import com.scaler.backendproject.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SelfCategoryService implements CategoryService{

    private final ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    SelfCategoryService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if (!categories.isEmpty()) {
            return categories;
        }
        return new ArrayList<Category>();
    }

    public Category getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (!category.isPresent()) {
            return null;
        }
        return category.get();
    }

    public Category saveCategory(String categoryName) {
        Category category = new Category();
        category.setTitle(categoryName);
        return categoryRepository.save(category);
    }

    @Override
    public List<Product> getProductsByCategoryId(Long id) {
        List<Product> products = productRepository.getProductsByCategoryId(id);
        if (!products.isEmpty()) {
            return products;
        }else{
            return null;
        }
    }
}
