package com.scaler.backendproject.dto;

import com.scaler.backendproject.models.Category;
import com.scaler.backendproject.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private Double price;
    private String description;
    private String category;
    private String image;

    Product getProduct() {
        Product product = new Product();
        product.setId(id);
        product.setTitle(title);
        product.setPrice(price);
        product.setDescription(description);
        product.setImageUrl(image);

        Category category = new Category();
        category.setTitle(title);
        product.setCategory(category);

        return product;
    }
}
