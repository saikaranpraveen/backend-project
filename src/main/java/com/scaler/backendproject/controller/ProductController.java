package com.scaler.backendproject.controller;

import com.scaler.backendproject.models.Product;
import com.scaler.backendproject.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.List;

@RestController
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //    @RequestMapping(value="/product", method = RequestMethod.POST)
    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
        System.out.println("Create product");
        Product productToReturn = productService.createProduct(product.getId(),
                product.getTitle(), product.getDescription(),
                product.getPrice(),product.getCategory().getTitle(),
                product.getImageUrl());
        productToReturn.toString();
        return productToReturn;
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        System.out.println("Get product");
        Product product = productService.getSingleProduct(id);
        return product;
    }

    @GetMapping("/products")
    public Product[] getAllProducts(){
        Product[] products = productService.getAllProducts();
        return products;
    }

    @PutMapping("products/{id}")
    public String updateProduct(@PathVariable Long id, @RequestBody Product product) {
        productService.updateProduct(id,
                product.getTitle(), product.getDescription(),
                product.getPrice(),product.getCategory().getTitle(),
                product.getImageUrl());
        return "Product updated";
    }

    @DeleteMapping("products/{id}")
    public String deleteProduct(@PathVariable Long id) {
        String response = productService.deleteProduct(id);
        return response;
    }
}
