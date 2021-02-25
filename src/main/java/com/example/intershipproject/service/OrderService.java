package com.example.intershipproject.service;

import com.example.intershipproject.entites.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OrderService {
    public List<Order> getAllOrders();

    public List<Object[]> getEntireInfoAboutEveryOrder();

    public Optional<Order> getOrderById(int id);

    public void saveOrder(Order order);

    public void createOrder();
}
