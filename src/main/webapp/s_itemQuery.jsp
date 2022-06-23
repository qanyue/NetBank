<%--
  Created by IntelliJ IDEA.
  User: qanyu
  Date: 2022/6/23
  Time: 9:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<% if(request.getSession().getAttribute("s_id")==null) response.sendRedirect("Login.jsp"); %>--%>
<html>
<head>
    <title>产品信息查询</title>
</head>
<body>
<label><br>
请选择产品需要查询的项目:</label><br>
<form action="ItemQueryServlet" method="post">

<label>
    <input type="radio" name="asset" value="products">理财产品
</label>
<label>
    <input type="radio" name="asset" value="insurance">保险
</label>
<label>
    <input type="radio" name="asset" value="fund">基金
</label>
    <br>
<label>
    请输入该产品编号:
    <input type="text" name="id" >
</label>
    <br>
    <label>
        请输入产品名称:
        <input type="text" name="name">
    </label>
    <br>
    <label>
        请输入状态:
        <input type="text" name="status">
    </label>
    <br>
    <label>
        请输入管理员密码:
        <input type="password" name="password">
    </label><br>
<input type="submit" value="查询">
    <input type="submit" value=新建>
</form>
</body>
</html>
