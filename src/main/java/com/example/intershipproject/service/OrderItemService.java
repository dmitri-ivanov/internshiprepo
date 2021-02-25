package com.example.intershipproject.service;

import com.example.intershipproject.entites.OrderItems;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderItemService {
    List<OrderItems> getAllOrderItems();

    void saveOrderItem(OrderItems orderItems);
}
