package com.example.intershipproject.service;

import com.example.intershipproject.entites.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    List<Product> getAllProducts();

    Optional<Product> getProductById(int id);

    void removeById(int id);

    void removeAllProducts();

    boolean saveProduct(Product product);

    List<Object[]> allOrderedProducts();
}
