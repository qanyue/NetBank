<%--
  Created by IntelliJ IDEA.
  User: forever
  Date: 2022/6/23
  Time: 9:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.demo1.GaussDBQuery" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="./css/admin.css">
</head>
<body background="picture/yellow.jpeg">
<h1>
    欢迎进入理财产品管理界面！
</h1><br>
<%
    Statement stmt = null;
    ResultSet rs = null;
    try (Connection conn = GaussDBQuery.getConnetion()) {
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        rs = stmt.executeQuery("select * from finance.insurance;");

    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
    }
%>

<div class="adminbox">
    <form action="" class="distance" >
        <h2>在对应框填入对应内容</h2><br>
        理财产品编号：
        <input type="text" class="tom" name="i_id"><br>
        理财产品名称：
        <input type="text" class="tom" name="i_name" ><br>
        理财产品状态：
        <input type="text" class="tom" name="i_project" ><br>
        <input type="submit" class="tom" name="search" value="查询">
        <input type="submit" class="tom" name="delete" value="删除">
        <input type="submit" class="tom" name="add" value="增加">
        <input type="submit" class="tom" name="update" value="更新">
    </form>
</div>
</body>
</html>
