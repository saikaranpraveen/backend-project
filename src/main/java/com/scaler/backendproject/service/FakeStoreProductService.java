package com.scaler.backendproject.service;

import com.scaler.backendproject.dto.FakeStoreProductDto;
import com.scaler.backendproject.models.Category;
import com.scaler.backendproject.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {

    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(Long id) {
        System.out.println("inside getSingleProduct");
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id,
                FakeStoreProductDto.class);
        return fakeStoreProductDto.getProduct();
    }

    @Override
    public Product[] getAllProducts() {
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity("https://fakestoreapi.com/products",
                FakeStoreProductDto[].class);
        FakeStoreProductDto[] products = response.getBody();
        System.out.println(response.getStatusCode());
        Product[] array = new Product[products.length];
        for (int i = 0; i < products.length; i++) {
            array[i] = products[i].getProduct();
        }
        return array;
    }

    @Override
    public Product createProduct(Long id, String title, String description, Double price, String category, String url) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(id);
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setCategory(category);
        fakeStoreProductDto.setImage(url);

        FakeStoreProductDto response = restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                fakeStoreProductDto,
                FakeStoreProductDto.class);

        return response.getProduct();
    }

    public void updateProduct(Long id, String title, String description, Double price, String category, String url) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(id);
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setCategory(category);
        fakeStoreProductDto.setImage(url);
        restTemplate.put("https://fakestoreapi.com/products/"+id,
                fakeStoreProductDto);
    }

    public String deleteProduct(Long id) {
        restTemplate.delete("https://fakestoreapi.com/products/"+ id);
        return "Product deleted successfully";
    }
}
