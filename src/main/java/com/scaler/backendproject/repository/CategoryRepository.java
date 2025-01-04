package com.scaler.backendproject.repository;

import com.scaler.backendproject.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category save(Category category);
    Category findByTitle(String title);
    Optional<Category> findById(Long id);
    List<Category> findAll();
}
