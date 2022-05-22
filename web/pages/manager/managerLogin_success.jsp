<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2022/1/10
  Time: 11:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员登录页面成功</title>
    <!--静态包含base标签、css样式、jQuery文件-->
    <%@include file="/pages/common/head.jsp"%>
    <script type="text/javascript">
        $(function (){
            $("#sendOrder").click(function (){
                var orderId = $(this).attr("orderId");
                location.href =
                    "http://localhost:8080/book/orderServlet?action=sendOrder&orderId="+orderId;
                return confirm("你确定要发货吗？");
            });
        });
    </script>
</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif" >
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <img src="static/img/go_to_work.png" width="550px" height="90px">
    <div>
        <form action="orderServlet" method="post">
            <input type="hidden" name="action" value="showUndeliveredOrdersByUserId">
            用户编号：<input type="text" name="userId" value="${sessionScope.userId}">
            &nbsp;<input type="submit" value="查找" id="searchSub">
        </form>
    </div>
</div>

<div id="main">
        <table>
            <tr>
                <td>待发货订单编号</td>
                <td>下单时间</td>
                <td>订单详情</td>
                <td>去发货</td>
            </tr>
            <c:if test="${sessionScope.userId != 0}">
                <c:if test="${empty sessionScope.orders}">
                    <tr>
                        <td colspan="5">该用户无未发货订单信息</td>
                    </tr>
                </c:if>
                <c:if test="${not empty sessionScope.orders}">
                    <c:forEach items="${sessionScope.orders}" var="i">
                        <tr>
                            <td>${i.orderId}</td>
                            <td>${i.createTime}</td>
                            <td><a
                                    href="orderServlet?action=managerShowOrderDetail&orderId=${i.orderId}"
                                    style="font-size: 18px">查看详情</a>
                            </td>
                            <td>
                                <button id="sendOrder" style="width: 60px; height: 30px" orderId="${i.orderId}">发货
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
            </c:if>
            <c:if test="${sessionScope.userId == 0}">
                <tr>
                    <td colspan="4">请输入用户编号</td>
                </tr>
            </c:if>
        </table>
</div>

<!--静态包含页脚内容-->
<%@include file="/pages/common/foot.jsp"%>
</body>
</html>
