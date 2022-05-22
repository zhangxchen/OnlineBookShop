package com.atguigu.service;

import com.atguigu.dao.BookDAO;
import com.atguigu.dao.impl.BookDAOImpl;
import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    /**
     * 生成订单
     * @param cart
     * @param userId
     * @return
     */
    public String createOrder(Cart cart,Integer userId);

    /**
     * 查看我的订单
     * @param userId
     * @return
     */
    public List<Order> showMyOrders(Integer userId);

    /**
     * 根据订单号查询订单明细
     * @param orderId
     * @return
     */
    public List<OrderItem> showOrderDetails(String orderId);

    /**
     * 根据用户编号查询该用户未发货的订单信息
     * @param userId
     * @return
     */
    public List<Order> showUndeliveredOrdersByUserId(Integer userId);

    /**
     * 根据订单号查询订单总金额
     * @param orderId
     * @return
     */
    public BigDecimal showOrderPriceByOrderId(String orderId);

    /**
     * 发货
     * @param orderId
     */
    public void sendOrder(String orderId);

    /**
     * 收货
     * @param orderId
     */
    public void receiverOrder(String orderId);
}
