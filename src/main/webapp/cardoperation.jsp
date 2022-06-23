<%--
  Created by IntelliJ IDEA.
  User: forever
  Date: 2022/6/23
  Time: 9:01
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
    <title>银行卡数据库操作界面</title>
    <link rel="stylesheet" href="./css/admin.css">
</head>
<body background="picture/yellow.jpeg">
<h1>欢迎进入银行卡信息操作界面</h1><br>
<div class="adminbox">
    <form action="" class="distance" >
        <h2>在对应框填入对应内容</h2><br>
        银行卡号：
        <input type="text" class="tom" name="cardid"><br>
        客户编号：
        <input type="text" class="tom" name="clientid" ><br>
        客户密码：
        <input type="text" class="tom" name="password" ><br>
        卡的类型：
        <input type="text" class="tom" name="type" ><br>
        卡的余额：
        <input type="text" class="tom" name="deposit" ><br>
        卡的状态：
        <input type="text" class="tom" name="status" ><br>
        <input type="submit" class="tom" name="search" value="查询">
        <input type="submit" class="tom" name="delete" value="删除">
        <input type="submit" class="tom" name="add" value="增加">
        <input type="submit" class="tom" name="update" value="更新">
    </form>
</div>



</body>
</html>
