package com.example.intershipproject.service.impl;

import com.example.intershipproject.dao.OrderItemRepository;
import com.example.intershipproject.entites.OrderItems;
import com.example.intershipproject.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemsServiceImpl implements OrderItemService {
    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    public List<OrderItems> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    @Override
    public void saveOrderItem(OrderItems orderItems) {
        orderItemRepository.save(orderItems);
    }
}
