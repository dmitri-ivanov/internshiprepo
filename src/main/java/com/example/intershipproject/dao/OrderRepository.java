package com.example.intershipproject.dao;

import com.example.intershipproject.entites.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value= "select o.id as order_id, p.name, (oi.quantity * p.price) as total_price, oi.quantity as quantity_in_order, o.created_at\n" +
            " from orders as o inner join order_items as oi on oi.order_id = o.id inner join products as p on oi.product_id = p.id order by o.id", nativeQuery=true)
    List<Object[]> findAllOrders();
}
