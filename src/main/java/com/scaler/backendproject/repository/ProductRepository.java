package com.scaler.backendproject.repository;

import com.scaler.backendproject.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // 1. Get By Id
    Optional<Product> findById(Long id);
    // 2. Get All Products
    List<Product> findAll();
    // 3. Create Product
    Product save(Product product);
    // 4. Update Product

    // 5. Delete Product
    void deleteById(Long id);

    Product findByTitle(String title);

}
