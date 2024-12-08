package com.scaler.backendproject.controller;

import com.scaler.backendproject.models.Product;
import com.scaler.backendproject.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //    @RequestMapping(value="/product", method = RequestMethod.POST)
    @PostMapping("/product")
    public void createProduct(Product product) {

    }

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        productService.getSingleProduct(id);
        return null;
    }

    public void updateProduct(Product product) {

    }

    public void deleteProduct(Product product) {

    }
}
