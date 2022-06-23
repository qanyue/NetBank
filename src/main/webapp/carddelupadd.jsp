<%--
  Created by IntelliJ IDEA.
  User: 40843
  Date: 2022/6/23
  Time: 10:36
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
    <title>管理员对银行卡信息的增删改查</title>
    <link rel="stylesheet" href="./css/admin.css">
    <style>
        .distance{
            font-family: 宋体;font-size:18px;color: black;
            border :3px red;
            text-align: center;
        }
        .tom{
            font-family: 宋体;font-size:18px;color: black;
        }
        h1{
            text-align: center;

            padding-top: 20px;
            padding-bottom: 20px;

        }

    </style>
</head>
<body background="picture/background.jpeg">
<h1>银行卡信息增删改查界面</h1>

<div class="distance">
    <form action="" class="distance" name="form1">
        在对应框填入对应内容<br>
        客户编号：
        <input type="text" class="tom" name="id"><br>
        银行卡号：
        <input type="text" class="tom" name="caid" ><br>
        银行卡密码：
        <input type="text" class="tom" name="password" ><br>
        银行卡类型：
        <input type="text" class="tom" name="type" ><br>
        <input type="submit" class="tom" name="search" value="搜索" onclick="form1.action='cardsearch';form1.submit();"></form>
    <input type="submit" class="tom" name="delete" value="删除" onclick="form1.action='carddelete';form1.submit();"></form>
    <input type="submit" class="tom" name="add" value="增加/修改" onclick="form1.action='cardupdate';form1.submit();"></form>
    </form>
</div>



</body>
</html>
