package com.atguigu.test;

import com.atguigu.dao.OrderItemDAO;
import com.atguigu.dao.impl.OrderItemDAOImpl;
import com.atguigu.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

public class OrderItemDAOTest {
    private OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
    @Test
    public void saveOrderItem() {
        orderItemDAO.saveOrderItem(new OrderItem(null,"开心的事情要记在小本本上",1,new BigDecimal(100),
                new BigDecimal(100),"1234567890"));
        orderItemDAO.saveOrderItem(new OrderItem(null,"java从入门到精通",1,new BigDecimal(100),
                new BigDecimal(100),"1234567890"));
        orderItemDAO.saveOrderItem(new OrderItem(null,"javaScript从入门到精通",1,new BigDecimal(100),
                new BigDecimal(100),"1234567890"));
    }

    @Test
    public void queryOrderItemsByOrderId() {
        for ( OrderItem orderItem : orderItemDAO.queryOrderItemsByOrderId("1234567890")) {
            System.out.println(orderItem);
        }
    }
}