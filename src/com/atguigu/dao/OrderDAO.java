package com.atguigu.dao;

import com.atguigu.pojo.Order;

import java.math.BigDecimal;
import java.util.List;

public interface OrderDAO {
    /**
     * 保存订单
     * @param order
     * @return
     */
    public int saveOrder(Order order);

    /**
     * 根据用户编号查询订单信息
     * @param userId
     * @return
     */
    public List<Order> queryOrdersByUserId(Integer userId);

    /**
     * 根据订单编号查询订单金额
     * @param orderId
     * @return
     */
    public BigDecimal showOrderPriceByOrderId(String orderId);

    /**
     * 修改订单状态
     * @param orderId
     * @param status
     */
    public int changeOrderStatus(String orderId,Integer status);

    /**
     * 根据用户编号查询未发货订单信息
     * @param userId
     * @return
     */
    public List<Order> queryUndeliveredOrdersByUserId(Integer userId);
}
