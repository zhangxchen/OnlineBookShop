<%@ page import="java.util.List" %>
<%@ page import="com.atguigu.pojo.OrderItem" %>
<%@ page import="java.math.BigDecimal" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的订单详情</title>
    <link type="text/css" rel="stylesheet" href="../../static/css/style.css" >
    <!--静态包含base标签、css样式、jQuery文件-->
    <%@include file="/pages/common/head.jsp"%>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>
</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="../../static/img/logo.gif" >
    <span class="wel_word">我的订单</span>
    <!--静态包含登录成功之后的菜单-->
    <%@ include file="/pages/common/login_success_menu.jsp"%>
</div>

<div id="main">

    <table>
        <tr>
            <td>商品名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>总价</td>
        </tr>

        <c:forEach items="${sessionScope.orderItems}" var="orderItem">
            <tr>
                <td>${orderItem.name}</td>
                <td>${orderItem.count}</td>
                <td>${orderItem.price}</td>
                <td>${orderItem.totalPrice}</td>
            </tr>
        </c:forEach>


        <tr>
            <td colspan="4">商品总额：${sessionScope.orderPrice}￥</td>
        </tr>
    </table>

</div>

<!--静态包含页脚内容-->
<%@include file="/pages/common/foot.jsp"%>
</body>
</html>