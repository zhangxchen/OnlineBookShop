package com.atguigu.test;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

public class OrderServiceTest {
    private OrderService orderService = new OrderServiceImpl();
    @Test
    public void createOrder() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"java从入门到精通",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"java从入门到精通",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"数据结构与算法",1,new BigDecimal(100),new BigDecimal(100)));

        System.out.println(orderService.createOrder(cart, 1));
    }

    @Test
    public void showMyOrders() {
        for (Order order : orderService.showMyOrders(1)) {
            System.out.println(order);
        }
    }

    @Test
    public void showOrderDetails() {
        for ( OrderItem orderItem : orderService.showOrderDetails("1234567890")) {
            System.out.println(orderItem);
        }
    }

    @Test
    public void showOrderPriceByOrderId() {
        System.out.println(orderService.showOrderPriceByOrderId("1641459787857"));
    }

    @Test
    public void sendOrder() {
        orderService.sendOrder("1641540747122");
    }

    @Test
    public void receiverOrder() {
        orderService.receiverOrder("1641540747122");
    }

    @Test
    public void showUndeliveredOrdersByUserId() {
        for (final Order order : orderService.showUndeliveredOrdersByUserId(1)) {
            System.out.println(order);
        }
    }
}