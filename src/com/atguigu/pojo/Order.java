package com.atguigu.pojo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class Order {
//    private Map<Integer,OrderItem> orders = new LinkedHashMap<>();

    private BigDecimal price;
    /**
     * 0表示未发货
     * 1表示已发货
     * 2表示已签收
     */
    private Integer status = 0;
    private String orderId;
    private Date createTime;
    private Integer userId;

    public Order() {
    }

    public Order(final BigDecimal price, final Integer status, final String orderId,
            final Date createTime,
            final Integer userId) {
        this.price = price;
        this.status = status;
        this.orderId = orderId;
        this.createTime = createTime;
        this.userId = userId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(final Integer status) {
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(final String orderId) {
        this.orderId = orderId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(final Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "price=" + price +
                ", status=" + status +
                ", orderId='" + orderId + '\'' +
                ", createTime=" + createTime +
                ", userId=" + userId +
                '}';
    }
}
