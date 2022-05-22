<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2021/12/31
  Time: 9:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--动态访问地址-->
<%
    String bathPath = request.getScheme()
            +"://"
            +request.getServerName()
            +":"
            +request.getServerPort()
            +request.getContextPath()
            +"/";
    pageContext.setAttribute("bathPath",bathPath);
%>
<!--写base标签，永远固定相对路径跳转的结果-->
<base href="<%=bathPath%>">
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
