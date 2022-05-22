<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>尚硅谷会员注册页面</title>
	<!--静态包含base标签、css样式、jQuery文件-->
	<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
        // 页面加载完成之后
        $(function () {

            // 给注册绑定单击事件
            $("#sub_btn").click(function () {
                // 验证用户名：必须由字母，数字下划线组成，并且长度为5到12位
                //1 获取用户名输入框里的内容
                var usernameText = $("#username").val();
                //2 创建正则表达式对象
                var usernamePatt = /^\w{5,12}$/;
                //3 使用test方法验证
                if (!usernamePatt.test(usernameText)) {
                    //4 提示用户结果
                    $("span.errorMsg").text("用户名不合法！");

                    return false;
                }

                // 验证密码：必须由字母，数字下划线组成，并且长度为5到12位
                //1 获取用户名输入框里的内容
                var passwordText = $("#password").val();
                //2 创建正则表达式对象
                var passwordPatt = /^\w{5,12}$/;
                //3 使用test方法验证
                if (!passwordPatt.test(passwordText)) {
                    //4 提示用户结果
                    $("span.errorMsg").text("密码不合法！");

                    return false;
                }

                // 验证确认密码：和密码相同
                //1 获取确认密码内容
                var repwdText = $("#repwd").val();
                //2 和密码相比较
                if (repwdText != passwordText) {
                    //3 提示用户
                    $("span.errorMsg").text("确认密码和密码不一致！");

                    return false;
                }

                // 邮箱验证：xxxxx@xxx.com
                //1 获取邮箱里的内容
                var emailText = $("#email").val();
                //2 创建正则表达式对象
                var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
                //3 使用test方法验证是否合法
                if (!emailPatt.test(emailText)) {
                    //4 提示用户
                    $("span.errorMsg").text("邮箱格式不合法！");

                    return false;
                }

                // 验证码：现在只需要验证用户已输入。因为还没讲到服务器。验证码生成。
                var codeText = $("#code").val();

                //去掉验证码前后空格
                // alert("去空格前：["+codeText+"]")
                codeText = $.trim(codeText);
                // alert("去空格后：["+codeText+"]")

                if (codeText == null || codeText == "") {
                    //4 提示用户
                    $("span.errorMsg").text("验证码不能为空！");

                    return false;
                }

                // 去掉错误信息
                $("span.errorMsg").text("");

            });

			//给验证码绑定单击事件
			$("#code_img").click(function (){
				//在事件响应的function函数中有一个this对象 这个this对象是当前正在响应事件的dom对象
				//此处的dom对象是验证码的 img标签
				//src属性表示验证码img标签的图片路径 可读可写

				//问题★
				//浏览器为了让请求速度更快 就会把每次请求的内容缓存到浏览器（硬盘/内存）
				//kaptcha.jpg=返回的图片内容
				//再发一样地址和参数和请求时，直接从缓存中找（直接从浏览器的缓存中获取原来的图片并返回）
				//缓存名称：http://localhost:8080/book/kaptcha.jpg(最后的资源名+参数)

				//解决方法（如何跳过浏览器的缓存 发起请求给服务器获取新的验证码）
				//修改浏览器的缓存名（后面+一个每次都会变的东西）
				this.src = "${bathPath}kaptcha.jpg?d=" + new Date();
			});

        });

	</script>
	<style type="text/css">
		.login_form{
			height:420px;
			margin-top: 25px;
		}

	</style>
</head>
<body>
<div id="login_header">
	<img class="logo_img" alt="" src="static/img/logo.gif" >
</div>

<div class="login_banner">

	<div id="l_content">
		<span class="login_word">欢迎注册</span>
	</div>

	<div id="content">
		<div class="login_form">
			<div class="login_box">
				<div class="tit">
					<h1>注册尚硅谷管理员</h1>
					<span class="errorMsg">
						${requestScope.msg}
					</span>
				</div>
				<div class="form">
					<form action="userServlet" method="post">
						<input type="hidden" name="action" value="regist">
						<label>用户名称：</label>
						<input class="itxt" type="text" placeholder="请输入用户名"
							   value="${requestScope.username}"
							   autocomplete="off" tabindex="1" name="username" id="username" />
						<br />
						<br />
						<label>用户密码：</label>
						<input class="itxt" type="password" placeholder="请输入密码"
							   autocomplete="off" tabindex="1" name="password" id="password"/>
						<br />
						<br />
						<label>确认密码：</label>
						<input class="itxt" type="password" placeholder="确认密码"
							   autocomplete="off" tabindex="1" name="repwd" id="repwd" />
						<br />
						<br />
						<label>电子邮件：</label>
						<input class="itxt" type="text" placeholder="请输入邮箱地址"
							   value="${requestScope.email}"
							   autocomplete="off" tabindex="1" name="email" id="email" />
						<br />
						<br />
						<label>邀请码：</label>&nbsp;&nbsp;&nbsp;&nbsp;
						<input class="itxt" type="text" name="code" style="width: 221px;"
							   id="code" value="请输入邀请码"/>
						<br />
						<br />
						<input type="submit" value="注册" id="sub_btn" />
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