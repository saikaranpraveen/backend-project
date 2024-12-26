package com.scaler.backendproject.service;

import com.scaler.backendproject.exceptions.ProductNotFoundException;
import com.scaler.backendproject.models.Category;
import com.scaler.backendproject.models.Product;
import com.scaler.backendproject.repository.CategoryRepository;
import com.scaler.backendproject.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product createProduct(Long id, String title, String description, Double price, String categoryTitle, String image) {
        Product product = new Product();

        Category existingCat = categoryRepository.findByTitle(categoryTitle);
        if (existingCat !=null) {
            product.setCategory(existingCat);
        }else{
            Category newCat = new Category();
            newCat.setTitle(categoryTitle);
            Category newCategory = categoryRepository.save(newCat);
            product.setCategory(newCategory);
        }

        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(image);

        return productRepository.save(product);
    }

    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundException {
        System.out.println("ID: " + id);
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        }
        throw new ProductNotFoundException("Product not found");
    }

    @Override
    public Product[] getAllProducts() {
        List<Product> products = productRepository.findAll();
        if (!products.isEmpty()) {
            return products.toArray(new Product[0]);
        }
        return new Product[0];
    }

    @Override
    public Product updateProduct(Long id, String title, String description, Double price, String categoryTitle, String image) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            product.get().setTitle(title);
            product.get().setDescription(description);
            product.get().setPrice(price);
            product.get().setImageUrl(image);

            Category existingCat = categoryRepository.findByTitle(categoryTitle);
            if (existingCat != null) {
                product.get().setCategory(existingCat);
            } else {
                Category newCat = new Category();
                newCat.setTitle(categoryTitle);
                Category newCategory = categoryRepository.save(newCat);
                product.get().setCategory(newCategory);
            }

            Product response = productRepository.save(product.get());
            return response;
        }else {
            throw new ProductNotFoundException("Product not found");
        }
    }

    @Override
    public void deleteProduct(Long id) throws ProductNotFoundException{
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.delete(product.get());
        }else{
            throw new ProductNotFoundException("Product not found");
        }
    }
}
