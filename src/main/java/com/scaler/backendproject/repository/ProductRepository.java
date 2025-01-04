package com.scaler.backendproject.repository;

import com.scaler.backendproject.models.Category;
import com.scaler.backendproject.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // 1. Get By Id
    Optional<Product> findById(Long id);
    // 2. Get All Products
    List<Product> findAll();
    // 3. Create Product
    Product save(Product product);
    // 4. Update Product is implemented by save but providing ID

    // 5. Delete Product
    void deleteById(Long id);

    Product findByTitle(String title);

    @Query(value = "select * from product as p where p.category_id = :categoryId",nativeQuery = true)
    List<Product> getProductsByCategoryId(@Param("categoryId") Long categoryId);
}
