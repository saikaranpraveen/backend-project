package com.scaler.backendproject.controller;

import com.scaler.backendproject.dto.ErrorDto;
import com.scaler.backendproject.exceptions.ProductNotFoundException;
import com.scaler.backendproject.models.Product;
import com.scaler.backendproject.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        System.out.println("Get product");
        Product product = productService.getSingleProduct(id);
        ResponseEntity<Product> response = new ResponseEntity<>(product, HttpStatus.OK);
        return response;
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

    @ExceptionHandler({ProductNotFoundException.class})
    public ResponseEntity<ErrorDto> handleProductNotFoundException(Exception exception) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(exception.getMessage());

        ResponseEntity<ErrorDto> response = new ResponseEntity<>(
                errorDto,
                HttpStatus.NOT_FOUND);
        return response;
    }
}
