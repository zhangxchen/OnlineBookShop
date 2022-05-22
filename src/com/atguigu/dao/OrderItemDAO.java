package com.atguigu.dao;

import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;

import java.util.List;

public interface OrderItemDAO {
    /**
     * 保存订单项
     * @param orderItem
     * @return
     */
    public int saveOrderItem(OrderItem orderItem);

    /**
     * 根据订单号查询订单明细
     * @param orderId
     * @return
     */
    public List<OrderItem> queryOrderItemsByOrderId(String orderId);
}
