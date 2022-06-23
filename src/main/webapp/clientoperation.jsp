<%--
  Created by IntelliJ IDEA.
  User: forever
  Date: 2022/6/22
  Time: 15:05
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
    <title>管理员对用户数据的增删改</title>
    <link rel="stylesheet" href="./css/admin.css">
    <style>

        h1{
            text-align: center;
            padding-top: 20px;
            padding-bottom: 20px;
        }
        h2{
            padding-bottom: 20px;

        }

    </style>
</head>
<body background="picture/yellow.jpeg">
<h1>用户信息增删改查界面</h1>
<%
    Statement stmt = null;
    ResultSet rs = null;
    try (Connection conn = GaussDBQuery.getConnetion()) {
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        rs = stmt.executeQuery("select * from finance.client;");

    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
    }
%>

<%/*
        out.print("<table border = 1>");
        out.print("<tr>");
        out.print("<td>客户编号</td>");
        out.print("<td>客户名称</td>");
        out.print("<td>客户邮箱</td>");
        out.print("<td>客户身份证</td>");
        out.print("<td>客户手机号</td>");
        out.print("<td>客户状态</td>");
        out.print("</tr>");
        while(rs.next()){
            out.print("<tr>");
            out.print("<td>"+rs.getString(1)+"</td>");
            out.print("<td>"+rs.getString(2)+"</td>");
            out.print("<td>"+rs.getString(3)+"</td>");
            out.print("<td>"+rs.getString(4)+"</td>");
            out.print("<td>"+rs.getString(5)+"</td>");
            out.print("<td>"+rs.getString(6)+"</td>");
            out.print("</tr>");
        }
        out.print("</table>");*/
%>
<div class="adminbox" >
        <form action="" class="distance">
           <h2> 在对应框填入对应内容</h2><br>
            客户编号：
            <input type="text" class="tom" name="id"><br>
            客户名称：
            <input type="text" class="tom" name="name" ><br>
            客户邮箱：
            <input type="text" class="tom" name="mail" ><br>
            客户身份证：
            <input type="text" class="tom" name="card" ><br>
            客户手机号：
            <input type="text" class="tom" name="phone" ><br>
            <input type="submit" class="tom" name="search" value="查询">
            <input type="submit" class="tom" name="delete" value="删除">
            <input type="submit" class="tom" name="add" value="增加">
            <input type="submit" class="tom" name="update" value="更新">
        </form>
</div>
</body>
</html>
