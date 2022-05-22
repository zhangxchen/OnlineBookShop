package com.atguigu.test;

import com.atguigu.dao.OrderDAO;
import com.atguigu.dao.impl.OrderDAOImpl;
import com.atguigu.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

public class OrderDAOTest {
    private OrderDAO orderDAO = new OrderDAOImpl();
    @Test
    public void saveOrder() {
        orderDAO.saveOrder(new Order(new BigDecimal(100),0,"3472394728305",new Date(),1));
    }

    @Test
    public void queryOrdersByUserId() {
        for (Order order : orderDAO.queryOrdersByUserId(1)) {
            System.out.println(order);
        }
    }

    @Test
    public void showOrderPriceByOrderId() {
        System.out.println(orderDAO.showOrderPriceByOrderId("1641459787857"));
    }

    @Test
    public void changeOrderStatus() {
        System.out.println(orderDAO.changeOrderStatus("1641535013848", 1));
    }

    @Test
    public void queryUndeliveredOrdersByUserId() {
        for (Order order : orderDAO.queryUndeliveredOrdersByUserId(1)) {
            System.out.println(order);
        }
    }
}