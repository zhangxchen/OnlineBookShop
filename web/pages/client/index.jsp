<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
	<!--静态包含base标签、css样式、jQuery文件-->
	<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function (){
			$("button.addToCart").click(function (){
				let userId = $(this).attr("userId");
				if(userId != ""){
					//已经登录
					var bookId = $(this).attr("bookId");

					// location.href = "http://localhost:8080/book/cartServlet?action=addItem&id=" +
					// 		bookId;
					// let stockId = $(this).attr("stockId");
					// if(stockId == 0){
					// 	alert("库存不足，请重新选购");
					// }else{
					// 	alert("在购物车等您呦~");
					// }

					//发AJAX请求 添加图书到购物车
					$.getJSON("http://localhost:8080/book/cartServlet","action=ajaxAddItem&id=" + bookId
							,function (data){
						console.log(data);
						if(data.isSoldOut == true){
							//卖光了
							alert("《" + data.bookName + "》" + "已经卖光啦~ 再看看别的吧~");
						}else{
							//更新页面数据
							$("#cartTotalCount").text("您的购物车中有" + data.totalCount + "件商品");
							if(data.lastName != ""){
								$("#cartLastName").text("您刚刚将《" + data.lastName + "》加入了购物车中");
							}else{
								$("#cartLastName").text("购物车里什么都没有呦~");
							}
						}
					});

				} else{
					//未登录
					alert("请先登录");
					location.href = "http://localhost:8080/book/userServlet?action=login";
				}
			});
		});
	</script>
</head>

<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">网上书城</span>
			<div>
				<c:if test="${empty sessionScope.user.username}">
					<a href="pages/user/login.jsp">登录</a> |
					<a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
				</c:if>

				<c:if test="${not empty sessionScope.user.username}">
					<span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
					<a href="orderServlet?action=showMyOrders">我的订单</a>
					<a href="userServlet?action=logOut">注销</a>&nbsp;&nbsp;
					<a href="pages/cart/cart.jsp">购物车</a>
				</c:if>

<%--				<a href="pages/manager/manager.jsp">后台管理</a>--%>
			</div>
	</div>
	<div id="main">
		<div id="book">
			<div class="book_cond">
				<form action="client/bookServlet" method="get">
					<input type="hidden" name="action" value="pageByPrice">
					价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
						<input id="max" type="text" name="max" value="${param.max}"> 元
						<input type="submit" value="查询" />
				</form>
			</div>
			<div style="text-align: center">
				<c:if test="${empty sessionScope.cart.items}">
					<%--购物车空的输出--%>
					<span style="color: red" id="cartTotalCount"> </span>
					<div>
						<span style="color: red" id="cartLastName">购物车里什么都没有呦~</span>
					</div>
				</c:if>
				<c:if test="${not empty sessionScope.cart.items}">
					<%--购物车非空的输出--%>
					<span style="color: red" id="cartTotalCount"></span>
					<div>
						<span style="color: red" id="cartLastName"></span>
					</div>
				</c:if>
			</div>

			<c:forEach items="${requestScope.page.items}" var="book">
				<div class="b_list">
					<div class="img_div">
						<img class="book_img" alt="" src="${book.imgPath}" />
					</div>
					<div class="book_info">
						<div class="book_name">
							<span class="sp1">书名:</span>
							<span class="sp2">${book.name}</span>
						</div>
						<div class="book_author">
							<span class="sp1">作者:</span>
							<span class="sp2">${book.author}</span>
						</div>
						<div class="book_price">
							<span class="sp1">价格:</span>
							<span class="sp2">￥${book.price}</span>
						</div>
						<div class="book_sales">
							<span class="sp1">销量:</span>
							<span class="sp2">${book.sales}</span>
						</div>
						<div class="book_amount">
							<span class="sp1">库存:</span>
							<span class="sp2">${book.stock}</span>
						</div>
						<div class="book_add">
							<button stockId="${book.stock}" bookId="${book.id}"
									userId="${sessionScope.user.username}"
									class="addToCart">加入购物车</button>
						</div>
					</div>
				</div>
			</c:forEach>


		</div>

		<!--静态包含分页条-->
		<%@include file="/pages/common/page_nav.jsp"%>
	
	</div>
	<!--静态包含页脚内容-->
	<%@include file="/pages/common/foot.jsp"%>
</body>
</html>