package com.atguigu.service.impl;

import com.atguigu.dao.BookDAO;
import com.atguigu.dao.OrderDAO;
import com.atguigu.dao.impl.OrderDAOImpl;
import com.atguigu.dao.OrderItemDAO;
import com.atguigu.dao.impl.OrderItemDAOImpl;
import com.atguigu.dao.impl.BookDAOImpl;
import com.atguigu.pojo.Book;
import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;
import com.atguigu.service.OrderService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private OrderDAO orderDAO = new OrderDAOImpl();
    private OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
    private BookDAO bookDAO = new BookDAOImpl();
    @Override
    public String createOrder(final Cart cart, final Integer userId) {

        System.out.println("OrderServiceImpl程序在【" + Thread.currentThread().getName() + "】线程中");

        //生成订单号(唯一性)
        String orderId = System.currentTimeMillis()+userId+"";
        //创建一个订单对象
        Order order = new Order(cart.getTotalPrice(),0,orderId,new Date(),userId);
        //保存订单
        orderDAO.saveOrder(order);

        //添加异常
        int i = 12 / 0;

        //遍历购物车中每一个商品项转化成为订单项保存到数据库
        for(Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()){
            //获取每一个购物车中的商品项
            CartItem cartItem = entry.getValue();
            //转换为每一个订单项
            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getCount(),
                    cartItem.getPrice(), cartItem.getTotalPrice(),orderId);
            //保存订单项到数据库
            orderItemDAO.saveOrderItem(orderItem);
            //查出商品编号
            Book book = bookDAO.queryBookById(cartItem.getId());
            //修改销量和库存
            book.setSales(book.getSales() + cartItem.getCount());
            book.setStock(book.getStock() - cartItem.getCount());
            bookDAO.updateBook(book);
        }
        //清空购物车
        cart.clear();
        return orderId;
    }

    @Override
    public List<Order> showMyOrders(final Integer userId) {
        return orderDAO.queryOrdersByUserId(userId);
    }

    @Override
    public List<OrderItem> showOrderDetails(final String orderId) {
        return orderItemDAO.queryOrderItemsByOrderId(orderId);
    }

    @Override
    public List<Order> showUndeliveredOrdersByUserId(final Integer userId) {
        return orderDAO.queryUndeliveredOrdersByUserId(userId);
    }

    @Override
    public BigDecimal showOrderPriceByOrderId(final String orderId) {
        return orderDAO.showOrderPriceByOrderId(orderId);
    }

    @Override
    public void sendOrder(final String orderId) {
        orderDAO.changeOrderStatus(orderId,1);
    }

    @Override
    public void receiverOrder(final String orderId) {
        orderDAO.changeOrderStatus(orderId,2);
    }
}
