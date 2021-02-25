package com.example.intershipproject.entites;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order_items")
public class OrderItems {
    @EmbeddedId
    private OrderItemsId orderItemsId = new OrderItemsId();

    @ManyToOne
    @MapsId("orderId")
    private Order order;

    @ManyToOne
    @MapsId("itemId")
    private Product product;

    private int quantity;

    public OrderItems() {
    }

    public OrderItemsId getOrderItemsId() {
        return orderItemsId;
    }

    public void setOrderItemsId(OrderItemsId orderItemsId) {
        this.orderItemsId = orderItemsId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Embeddable
    public static class OrderItemsId implements Serializable {

        private int orderId;
        private int itemId;

        public OrderItemsId() {
        }

        public OrderItemsId(int orderId, int itemId) {
            super();
            this.orderId = orderId;
            this.itemId = itemId;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public int getItemId() {
            return itemId;
        }

        public void setItemId(int itemId) {
            this.itemId = itemId;
        }
    }


}
