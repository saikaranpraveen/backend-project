package com.scaler.backendproject.controller;

import com.scaler.backendproject.models.Category;
import com.scaler.backendproject.models.Product;
import com.scaler.backendproject.service.CategoryService;
import com.scaler.backendproject.service.SelfCategoryService;
import com.scaler.backendproject.service.SelfProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class CategoryController {

    private final CategoryService categoryService;
    private final SelfProductService selfProductService;
    private SelfCategoryService selfCategoryService;
    CategoryController(SelfCategoryService selfCategoryService, CategoryService categoryService, SelfProductService selfProductService) {
        this.selfCategoryService = selfCategoryService;
        this.categoryService = categoryService;
        this.selfProductService = selfProductService;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> findAll() {
        List<Category> response = selfCategoryService.getAllCategories();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        Category category = selfCategoryService.getCategoryById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping("/categories")
    public ResponseEntity<Category> save(@RequestBody Category category) {
        Category response = selfCategoryService.saveCategory(category.getTitle());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}/products")
    public ResponseEntity<List<Product>> findByCategory(@PathVariable Long categoryId) {
        Category currCategory = selfCategoryService.getCategoryById(categoryId);
        Long id = currCategory.getId();
        List<Product> products = selfCategoryService.getProductsByCategoryId(id);
        if (products == null) {
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.OK);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
