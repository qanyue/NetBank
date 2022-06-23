<%--
  Created by IntelliJ IDEA.
  User: forever
  Date: 2022/6/23
  Time: 9:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.servlet.GaussDBQuery" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<html>
<head>
    <title>保险操作</title>
    <link rel="stylesheet" href="./css/admin.css">
</head>
<body>
<%
    session = request.getSession();
    session.getAttribute("s_id");
    Statement stmt = null;
    ResultSet rs = null;
    try (Connection conn = GaussDBQuery.getConnetion()) {
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        rs = stmt.executeQuery("select * from finance.insurance;");

    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
    }
%>

<div class="distance">
    <form action="" class="distance" >
        保险编号：
        <input type="text" class="tom" name="i_id"><br>
        保险名称：
        <input type="text" class="tom" name="i_name" ><br>
        保险项目：
        <input type="text" class="tom" name="i_project" ><br>
        发行时间：
        <input type="text" class="tom" name="i_start" ><br>
        截止时间：
        <input type="text" class="tom" name="i_end" ><br>
        适用人群：
        <input type="text" class="tom" name="i_people" ><br>
        保险状态：
        <input type="text" class="tom" name="i_status" ><br>
        请选择对应操作：
        <label>
            <input type="radio" name="act" value="search" checked="true">
            查询
        </label>
        <label>
            <input type="radio" name="act" value="delete">
            删除
        </label>
        <label>
            <input type="radio" name="act" value="add">
            添加
        </label>
        <label>
            <input type="radio" name="act" value="update">
            更新
        </label>
        <input type="submit" value="提交">
    </form>
</div>
</body>
</html>
