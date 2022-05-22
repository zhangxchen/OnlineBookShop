<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
<link type="text/css" rel="stylesheet" href="../../static/css/style.css" ><!--静态包含base标签、css样式、jQuery文件-->
	<%@include file="/pages/common/head.jsp"%>
	<style type="text/css">
		h1 {
			text-align: center;
			margin-top: 200px;
		}
	</style>
	<script type="text/javascript">
		$(function (){
			//给确认收货绑定单击事件
			$("button.confirmReceived").click(function (){
				var id = $(this).attr("id");
				if(confirm("你确定要收货嘛？")){
					//发起请求给服务器保存修改
					location.href =
							"http://localhost:8080/book/orderServlet?action=receiveOrder&orderId="+id;
				}
			});
		});
	</script>
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
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>详情</td>
				<td>确认收货</td>
			</tr>

			<c:forEach items="${sessionScope.orders}" var="order">
				<tr>
					<td>${order.createTime}</td>
					<td>${order.price}</td>
					<td>
						<c:choose>
							<c:when test="${order.status == 0}">未发货</c:when>
							<c:when test="${order.status == 1}">已发货</c:when>
							<c:when test="${order.status == 2}">已签收</c:when>
						</c:choose>
					</td>
					<td><a href="orderServlet?action=showOrderDetail&orderId=${order.orderId}"
						   style="font-size: 16px">
						查看详情</a></td>
					<c:if test="${order.status == 2}">
						<td>已签收</td>
					</c:if>
					<c:if test="${order.status != 2}">
						<td><button class="confirmReceived" id="${order.orderId}">确认收货</button></td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
		
	
	</div>

	<!--静态包含页脚内容-->
	<%@include file="/pages/common/foot.jsp"%>
</body>
</html>