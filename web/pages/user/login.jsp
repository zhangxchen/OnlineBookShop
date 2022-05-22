<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>尚硅谷会员登录页面</title>
	<!--静态包含base标签、css样式、jQuery文件-->
	<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		//页面加载完成之后
		// $(function (){
		// 	//给身份选择绑定失去焦点事件
		// 	$("#loginIdentity").blur(function (){
		// 		var text = $("#loginIdentity option:selected").text();
		// 		alert(text);
		// 		$.getJSON("http://localhost:8080/book/chooseLoginIdentityServlet","action=loginIdentity&text=" + text,function (data){
		//
		// 		});
		// 	});
		// });
	</script>
</head>
<body>
<div id="login_header">
	<img class="logo_img" alt="" src="static/img/logo.gif" >
</div>

<div class="login_banner">

	<div id="l_content">
		<span class="login_word">欢迎登录</span>
	</div>

	<div id="content">
		<div class="login_form"  style="height: 360px">
			<div class="login_box">
				<span class="tit">
					<a href="pages/user/regist.jsp">立即注册</a>
				</span>
				<br>
				<br>
				<div class="msg_cont">
					<b></b>
					<span class="errorMsg">
						${empty requestScope.msg?"请输入用户名和密码":requestScope.msg}
					</span>
				</div>

					<div class="form">
						<form method="post" action="userServlet">
							<input type="hidden" name="action" value="login">
							<label>登录身份：</label>
							<select name="loginIdentity" style="width: 232px; height: 40px;
							font-size: 13px">
								<option>--请选择登录身份--</option>
								<option>普通用户</option>
								<option>管理员</option>
							</select>
							<br>
							<br>
							<label>用户名称：</label>
							<input class="itxt" type="text" placeholder="请输入用户名"
								   autocomplete="off" tabindex="1" name="username"
								   value="${requestScope.username}"/>
							<br />
							<br />
							<label>用户密码：</label>
							<input class="itxt" type="password" placeholder="请输入密码"
								   autocomplete="off" tabindex="1" name="password" />
							<br />
							<br />
							<input type="submit" value="登录" id="sub_btn" />
						</form>
					</div>


			</div>
		</div>
	</div>
</div>
<!--静态包含页脚内容-->
<%@include file="/pages/common/foot.jsp"%>
</body>
</html>