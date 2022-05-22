<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2021/12/31
  Time: 9:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    <c:if test="${empty sessionScope.user.username}">
        <span>欢迎光临尚硅谷书城 请先登录</span>
    </c:if>

    <c:if test="${not empty sessionScope.user.username}">
        <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
    </c:if>

    <a href="orderServlet?action=showMyOrders">我的订单</a>
    <a href="userServlet?action=logOut">注销</a>
    <a href="index.jsp">返回</a>
</div>
