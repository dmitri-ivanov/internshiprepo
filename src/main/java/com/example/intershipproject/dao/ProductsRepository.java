package com.example.intershipproject.dao;

import com.example.intershipproject.entites.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Integer> {

    @Query(value = "select p.name, SUM(oi.quantity) from products as p inner join order_items as oi where p.id=oi.product_id and oi.quantity > 0 group by p.name order by oi.quantity desc", nativeQuery=true )
    List<Object[]> findAllOrderedProducts();
}
