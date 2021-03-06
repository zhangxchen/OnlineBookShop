<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<!--静态包含base标签、css样式、jQuery文件-->
	<%@include file="/pages/common/head.jsp"%>
</head>
<body>
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
		<!--静态包含登录成功之后的菜单-->
		<%@ include file="/pages/common/login_success_menu.jsp"%>
		<script type="text/javascript">
			$(function (){

				//确认删除
				$("a.deleteItem").click(function (){
					return confirm("你确定要删除【"+ $(this).parent().parent().find("td:first").text()
							+"】嘛？");
				});

				//确认清空
				$("#clearCart").click(function (){
					return confirm("你确定要清空购物车嘛？");
				});

				//给输入框绑定内容发生改变事件
				$(".updateCount").change(function (){
					//获取商品名称
					var name = $(this).parent().parent().find("td:first").text();
					var id = $(this).attr('bookId');
					//获取商品数量
					var count = this.value;

					if(confirm("你确定要将【"+ name + "】商品修改数量为：" + count + "嘛？")){
						//发起请求给服务器保存修改
						location.href =
								"http://localhost:8080/book/cartServlet?action=updateCount&count=" + count +"&id=" + id;
					}else{
						//defaultValue是表单项dom对象的属性 表示默认的值
						this.value = this.defaultValue;
					}
				});

				//订单事务
				// $("#checkOut").click(function (){
				// 	var ifError = $("#checkOut").attr("ifErrorId");
				// 	if(ifError == "error"){
				// 		//数据库数据回滚 订单提交失败
				// 		alert("订单提交失败");
				// 	}
				// });
			});
		</script>
	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<c:if test="${empty sessionScope.cart.items}">
				<tr>
					<td colspan="5"><a href="index.jsp">购物车是空的呦~ 快去挑选自己喜欢的图书吧~</a></td>
				</tr>
			</c:if>
			<c:if test="${not empty sessionScope.cart.items}">
				<c:forEach items="${sessionScope.cart.items}" var="entry">
					<tr>
						<td>${entry.value.name}</td>
						<td>
							<input class="updateCount" bookId="${entry.value.id}" type="text"
								   value="${entry.value.count}"
								   style="width: 80px">
						</td>
						<td>${entry.value.price}</td>
						<td>${entry.value.totalPrice}</td>
						<td><a class="deleteItem" href="cartServlet?action=deleteItem&id=${entry.value.id}">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<!--购物车非空才输出-->
		<c:if test="${not empty sessionScope.cart.items}">
			<div class="cart_info">
			<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品
			</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元
			</span>
				<span class="cart_span"><a id="clearCart" href="cartServlet?action=clear">清空购物车
				</a></span>
				<span class="cart_span"><a id="checkOut" href="orderServlet?action=createOrder">去结账
				</a></span>
<%--				<c:if test="${sessionScope.msg} == 1">--%>
<%--					<span>订单提交失败！</span>--%>
<%--				</c:if>--%>
			</div>
		</c:if>
	</div>

	<!--静态包含页脚内容-->
	<%@include file="/pages/common/foot.jsp"%>
</body>
</html>