package com.atguigu.dao.impl;

import com.atguigu.dao.OrderDAO;
import com.atguigu.dao.impl.BaseDao;
import com.atguigu.pojo.Order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderDAOImpl extends BaseDao implements OrderDAO {
    @Override
    public int saveOrder(final Order order) {

        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`)" +
                "values(?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),
                order.getStatus(),order.getUserId());
    }

    @Override
    public List<Order> queryOrdersByUserId(final Integer userId) {
//`order_id` VARCHAR(50) PRIMARY KEY,
//	`create_time` DATETIME,
//	`price` DECIMAL(11,2),
//	`status` INT,
//	`user_id` INT,
        String sql = "select `order_id` orderId,`create_time` createTime,`price`,`status`," +
                "`user_id` userId from t_order where `user_id` = ?";
        return queryForList(Order.class,sql,userId);
    }

    @Override
    public BigDecimal showOrderPriceByOrderId(final String orderId) {
        String sql = "select `price` from t_order where `order_id` = ?";
        return (BigDecimal) queryForSingleValue(sql,orderId);
    }

    @Override
    public int changeOrderStatus(final String orderId, final Integer status) {
        String sql = "update t_order set `status` = ? where `order_id` = ?";
        return update(sql,status,orderId);
    }

    @Override
    public List<Order> queryUndeliveredOrdersByUserId(final Integer userId) {
        String sql = "select `order_id` orderId,`create_time` createTime,`price`,`user_id` userId" +
                " from t_order where `user_id` = ? and status = 0";
        return queryForList(Order.class,sql,userId);
    }
}
