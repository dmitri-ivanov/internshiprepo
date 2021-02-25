package com.example.intershipproject.service.impl;

import com.example.intershipproject.dao.OrderRepository;
import com.example.intershipproject.entites.Order;
import com.example.intershipproject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Object[]> getEntireInfoAboutEveryOrder() {
        return orderRepository.findAllOrders();
    }

    @Override
    public Optional<Order> getOrderById(int id) {
        return orderRepository.findById(id);
    }

    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void createOrder() {

    }
}
