<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2022/1/7
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>500错误页面</title>
    <%@include file="/pages/common/head.jsp"%>
    <script type="text/javascript">

        $(function (){
            $("#error500").click(function (){
                location.href = "http://localhost:8080/book/pages/error/error404.jsp";
            });


            window.setInterval("location='http://localhost:8080/book/index.jsp'",3500);

        });

        <%long time = 3000;//设定的倒计时时间ms%>
        var second = <%= time / 1000%>; // 剩余秒数

        window.setInterval(function(){//定时器
            document.getElementById("showTimes").innerHTML = second;//写入到div中
            second --;
        }, 1000);

    </script>
</head>
<body>



<center>
    <table>
        <tr></tr>
        <tr></tr>
        <tr></tr>
        <tr></tr>
        <tr></tr>
        <tr></tr>
        <tr></tr>
        <tr></tr>
        <tr></tr>
        <tr></tr>
        <tr></tr>
        <tr>
            <td><div
                    align="center"><img src="static/img/error_404.jpg" width="80px"
                                        height="80px"/></div></td>
            <td><h2>很抱歉，您访问的页面找不到了！</h2></td>
        </tr>
    </table>
    <span>404 error  <span id="showTimes"></span>秒后自动跳转</span><br><br>
    <span><input id="error404" type="submit" value="重新加载" style="background: lightskyblue; color:
    #666666;
    width: 100px; height: 30px; font-size:15px"></span>
    <br/>
</center>
</body>
</html>
