package com.example.ffmjava22springbootshop.ordersystem.shop.order;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class OrderRepo {

    private final Map<String, Order> orders = new HashMap<>();

    public void addOrder(Order order) {
        orders.put(order.id(), order);
    }

    public Order getOrder(String orderId) {
        return orders.get(orderId);
    }

    public List<Order> getOrdersByStatus(OrderStatus orderStatus) {
              return orders.values().stream()
                       .filter(order -> orderStatus.equals(orderStatus))
                       .collect(Collectors.toList());

    }
    public List<Order> listOrders() {
        return new ArrayList<>(orders.values());
    }

    public void removeOrder(String id) {
        orders.remove(id);
    }

}
