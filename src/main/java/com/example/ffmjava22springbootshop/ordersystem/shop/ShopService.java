package com.example.ffmjava22springbootshop.ordersystem.shop;
import com.example.ffmjava22springbootshop.ordersystem.shop.order.Order;
import com.example.ffmjava22springbootshop.ordersystem.shop.order.OrderRepo;
import com.example.ffmjava22springbootshop.ordersystem.shop.order.OrderStatus;
import com.example.ffmjava22springbootshop.ordersystem.shop.product.Product;
import com.example.ffmjava22springbootshop.ordersystem.shop.product.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class ShopService {
    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;
    private final ServiceUtils serviceUtils;

    public ShopService(
            ProductRepo productRepo,
            OrderRepo orderRepo,
            ServiceUtils serviceUtils) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
        this.serviceUtils = serviceUtils;
    }

    public Product getProduct(String id) {
        return productRepo.getProduct(id);
    }

    public List<Product> listProducts() {
        return productRepo.listProducts();
    }

    public Order addOrder(String orderId, List<String> productIds) {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Product product = productRepo.getProduct(productId);
            products.add(product);
        }

        Order order = new Order(orderId, products, OrderStatus.RECEIVED);
        orderRepo.addOrder(order);
        return order;
    }

    public Order getOrder(String orderId) {
        return orderRepo.getOrder(orderId);
    }

    public List<Order> listOrders() {
        return orderRepo.listOrders();
    }

    public void removeOrder(String id) {
        orderRepo.removeOrder(id);
    }

    public Product addProduct(Product product) {
        String id = serviceUtils.generateUUID();
        product = new Product(id, product.name());
        productRepo.addProduct(id, product);
        return product;
    }

    public List<Order> getOrdersByStatus(OrderStatus orderStatus){
        return orderRepo.getOrdersByStatus(orderStatus);
    }

    public Order changeOrderStatus(Order order){
        return order;
    }
}
