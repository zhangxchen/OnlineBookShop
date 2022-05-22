package com.atguigu.dao.impl;

import com.atguigu.dao.OrderItemDAO;
import com.atguigu.dao.impl.BaseDao;
import com.atguigu.pojo.OrderItem;

import java.util.List;

public class OrderItemDAOImpl extends BaseDao implements OrderItemDAO {
    @Override
    public int saveOrderItem(final OrderItem orderItem) {

        System.out.println("OrderItemDAOImpl程序在【" + Thread.currentThread().getName() + "】线程中");

        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) " +
                "values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),
                orderItem.getTotalPrice(), orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> queryOrderItemsByOrderId(final String orderId) {
        String sql = "select `id`,`name`,`count`,`price`,`total_price` totalPrice,`order_id` " +
                "orderId from t_order_item where `order_id` = ?";
        return queryForList(OrderItem.class,sql,orderId);
    }
}
