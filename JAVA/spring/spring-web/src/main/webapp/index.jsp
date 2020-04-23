<%--
  Created by IntelliJ IDEA.
  User: YongLiu
  Date: 2020/4/24
  Time: 0:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setCharacterEncoding("UTF-8");
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getRequestURI() + request.getServletPath();
%>
<html>
<head>
    <meta charset="UTF-8">
    <base href="<%=basePath%>">
    <title>Title</title>
</head>
<body>
<p>提交参数</p>
<form action="regservlet" method="post">

    姓名：<input type="text" name="name"> <br>
    年龄：<input type="text" name="age"> <br>
    <input type="submit" value="注册">
</form>
</body>
</html>
