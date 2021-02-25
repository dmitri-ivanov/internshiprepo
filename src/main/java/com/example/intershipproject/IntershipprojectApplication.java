package com.example.intershipproject;

import com.example.intershipproject.dao.OrderItemRepository;
import com.example.intershipproject.dao.OrderRepository;
import com.example.intershipproject.dao.ProductsRepository;
import com.example.intershipproject.service.UserInputHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IntershipprojectApplication implements CommandLineRunner {

    @Autowired
    ProductsRepository productsRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    UserInputHandler userInputHandler;

    public static void main(String[] args) {
        SpringApplication.run(IntershipprojectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hi there. You are going to use internship console application. There are some simple commands you are able to use:");
        userInputHandler.showHelp();
        userInputHandler.handler();

    }
}
