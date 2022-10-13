package com.example.ffmjava22springbootshop.ordersystem.shop;

import com.example.ffmjava22springbootshop.ordersystem.shop.order.Order;
import com.example.ffmjava22springbootshop.ordersystem.shop.order.OrderRepo;
import com.example.ffmjava22springbootshop.ordersystem.shop.order.OrderStatus;
import com.example.ffmjava22springbootshop.ordersystem.shop.product.Product;
import com.example.ffmjava22springbootshop.ordersystem.shop.product.ProductRepo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class ShopServiceTest {
    private OrderRepo orderRepo = mock(OrderRepo.class);
    private ProductRepo productRepo = mock(ProductRepo.class);
    private ServiceUtils serviceUtils = mock(ServiceUtils.class);
    private ShopService shopService = new ShopService(productRepo, orderRepo, serviceUtils);

    @Test
    void getProduct2Test(){
        //GIVEN
        String id = "2";
        Product expected = new Product("2", "xxxx");
        when(productRepo.getProduct(id)).thenReturn(expected);
        //WHEN
        Product actual = shopService.getProduct(id);
        //THEN
        assertEquals(expected,actual);
    }

    @Test
    void addProductTest(){
        //GIVEN
        when(serviceUtils.generateUUID()).thenReturn("1234");
        // doNothing muss vor den Test, da addProduct void ist und nichts zurück gibt
        doNothing().when(productRepo).addProduct("1234",new Product("1234","Apfel"));
        //WHEN
        Product actual = shopService.addProduct(new Product("1234","Apfel"));
        //THEN
        Product expected = new Product("1234","Apfel");
        // verify: um die Repoaufrufe zu testen
        verify(productRepo).addProduct("1234",new Product("1234","Apfel"));
        verify(serviceUtils).generateUUID();
        assertEquals(expected,actual);
    }


    @Test
    void addOrderTest() {
        //GIVEN
        String id = "100";
        List<String> productList = new ArrayList<>(List.of(
            "1",
            "2"
        ));
        Order testOrder = new Order("100", new ArrayList<>(List.of(
                new Product("1","Apfel"),
                new Product("2","Banane")
        )), OrderStatus.RECEIVED);
        doNothing().when(orderRepo).addOrder(testOrder);
        when(productRepo.getProduct("1")).thenReturn(new Product("1","Apfel"));
        when(productRepo.getProduct("2")).thenReturn(new Product("2","Banane"));
        //WHEN
        Order actual = shopService.addOrder(id, productList);
        //THEN
        Order expected = testOrder;
        assertEquals(expected,actual);
    }
}