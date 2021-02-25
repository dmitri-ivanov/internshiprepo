package com.example.intershipproject.service.impl;

import com.example.intershipproject.dao.ProductsRepository;
import com.example.intershipproject.entites.Product;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductServiceImplTest {
    @Autowired
    ProductServiceImpl productService;

    @MockBean
    ProductsRepository productsRepository;

    @Test
    void getAllProducts() {
        when(productsRepository.findAll()).thenReturn(Arrays.asList(new Product(), new Product()));
        assertEquals(2, productService.getAllProducts().size());
    }

    @Test
    void saveProduct() {
        Product product = new Product();
        product.setName("prod");
        product.setPrice(100);
        product.setCreatedAt(LocalDateTime.now());
        System.out.println("Int the test");
        boolean b = productService.saveProduct(product);
        assertTrue(b);
    }
}