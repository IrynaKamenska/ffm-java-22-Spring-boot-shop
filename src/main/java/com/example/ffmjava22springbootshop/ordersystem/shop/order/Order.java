package com.example.ffmjava22springbootshop.ordersystem.shop.order;
import com.example.ffmjava22springbootshop.ordersystem.shop.product.Product;
import java.util.List;

public record Order(
        String id,
        List<Product> products,
        OrderStatus orderStatus
) {

}
