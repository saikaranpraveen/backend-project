package com.scaler.backendproject.controller;

import com.scaler.backendproject.dto.ErrorDto;
import com.scaler.backendproject.exceptions.ProductNotFoundException;
import com.scaler.backendproject.models.Product;
import com.scaler.backendproject.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.List;

@RestController
public class ProductController {

    ProductService productService;

    public ProductController(@Qualifier("selfProductService") ProductService productService) {

        this.productService = productService;
    }

    //    @RequestMapping(value="/product", method = RequestMethod.POST)
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        System.out.println("Create product");
        Product productToReturn = productService.createProduct(product.getId(),
                product.getTitle(), product.getDescription(),
                product.getPrice(),product.getCategory().getTitle(),
                product.getImageUrl());
        ResponseEntity<Product> response = new ResponseEntity<>(productToReturn, HttpStatus.CREATED);
        return response;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        System.out.println("Get product");
        Product product = productService.getSingleProduct(id);
        ResponseEntity<Product> response = new ResponseEntity<>(product, HttpStatus.OK);
        return response;
    }

    @GetMapping("/products")
    public ResponseEntity<Product[]> getAllProducts(){
        Product[] products = productService.getAllProducts();
        ResponseEntity<Product[]> response = new ResponseEntity<>(products, HttpStatus.OK);
        return response;
    }

    @PutMapping("products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        try{
            Product updatedProduct = productService.updateProduct(id,
                    product.getTitle(), product.getDescription(),
                    product.getPrice(),product.getCategory().getTitle(),
                    product.getImageUrl());
            ResponseEntity<Product> response = new ResponseEntity<>(updatedProduct, HttpStatus.ACCEPTED);
            return response;
        }catch(ProductNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        try{
            productService.deleteProduct(id);
            ResponseEntity<String> responseToReturn = new ResponseEntity<>("Product deleted successfully", HttpStatus.ACCEPTED);
            return responseToReturn;
        }catch(ProductNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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
