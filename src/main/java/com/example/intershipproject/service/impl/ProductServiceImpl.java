package com.example.intershipproject.service.impl;

import com.example.intershipproject.dao.ProductsRepository;
import com.example.intershipproject.entites.Product;
import com.example.intershipproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductsRepository productsRepository;

    @Override
    public List<Product> getAllProducts() {
        return productsRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(int id) {
        return productsRepository.findById(id);
    }

    @Override
    public void removeById(int id) {
        productsRepository.deleteById(id);
    }

    @Override
    public void removeAllProducts() {
        productsRepository.deleteAll();
    }

    @Override
    public boolean saveProduct(Product product) {
        productsRepository.save(product);
        return true;
    }

    @Override
    public List<Object[]> allOrderedProducts() {
        return productsRepository.findAllOrderedProducts();
    }
}
