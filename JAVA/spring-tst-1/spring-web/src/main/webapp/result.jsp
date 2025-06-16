<%--
  Created by IntelliJ IDEA.
  User: YongLiu
  Date: 2020/4/24
  Time: 0:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getRequestURI() + request.getServletPath();
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="<%=basePath%>">
    <title>Result</title>
</head>
<body>
    result.jsp 注册成功！
</body>
</html>
