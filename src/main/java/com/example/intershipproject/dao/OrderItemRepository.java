package com.example.intershipproject.dao;

import com.example.intershipproject.entites.OrderItems;
import org.hibernate.boot.model.source.spi.Orderable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItems, OrderItems.OrderItemsId> {


}
