package com.example.ffmjava22springbootshop.ordersystem;

import com.example.ffmjava22springbootshop.ordersystem.shop.ShopService;
import com.example.ffmjava22springbootshop.ordersystem.shop.order.Order;
import com.example.ffmjava22springbootshop.ordersystem.shop.order.OrderStatus;
import com.example.ffmjava22springbootshop.ordersystem.shop.product.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api")
@RestController
public class ShopController {
    private ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    // GET Products
    @GetMapping("/products")
    public List<Product> getProducts(){
        return shopService.listProducts();
    }

    // GET Orders
    @GetMapping("/orders")
    //@GetMapping("test")
    public List<Order> getOrders(){
        return shopService.listOrders();
    }

    //GET Order
    @GetMapping(path="/orders/{id}")
    public Order getOrderByID(@PathVariable String id){
        return shopService.getOrder(id);
    }

    //GET Product
    @GetMapping(path="/products/{id}")
    public Product getProductByID(@PathVariable String id){
        return shopService.getProduct(id);
    }

    @GetMapping(path="/ordersWithStatus/{orderStatus}")
    public List<Order> getOrdersByStatus(@PathVariable OrderStatus orderStatus){
        return shopService.getOrdersByStatus(orderStatus);
    }

    // POST Orders
    // POST /api/orders/{id} (int-array als Body)
    // Im Postman POST - raw - JSON - [1,2,3,4]
    @PostMapping("/orders/{id}")
    public Order addOrder(@RequestBody List<String> list,
                          @PathVariable String id){
        return shopService.addOrder(id,list);
    }


    // DELETE Order
    // Löscht durch die Übergabe der ID den kompletten HashWert Eintrag
    @DeleteMapping("/orders/{id}")
    public void removeOrder(@PathVariable String id){
        shopService.removeOrder(id);
    }

    // POST Produkte
    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product){
        return shopService.addProduct(product);
    }





    /* POST
    @PostMapping
    public void addOrder(@RequestBody Order order){
        shopService.addOrder(order);
    }
   */
}
