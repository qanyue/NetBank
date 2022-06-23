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
    <link rel="stylesheet" href="css/manager.css">


</head>
<body>

<div class="cotainer">

    <div class="box1">

        <div class="head1"><br><br><br></div>

        <div class="head2">
            <h1>银行卡信息管理</h1>
        </div>

        <div class="Sidebar">
            <%--            <a href="User.jsp"><img src="images/Home.png"></a>--%>
        </div>

        <div class="form">


                <form action="" class="distance" name="form1">
                    客户编号：<br>
                    <input type="text" class="tom" name="id"><br><br>
                    银行卡号：<br>
                    <input type="text" class="tom" name="caid" ><br><br>
                    银行卡密码：<br>
                    <input type="text" class="tom" name="password" ><br><br>
                    银行卡类型：<br>
                    <input type="text" class="tom" name="type" ><br><br>
                    <input type="submit" class="tom" name="search" value="搜索" onclick="form1.action='cardsearch';form1.submit();"><br>
                <input type="submit" class="tom" name="delete" value="删除" onclick="form1.action='carddelete';form1.submit();">
            <input type="submit" class="tom" name="add" value="增加/修改" onclick="form1.action='cardupdate';form1.submit();">
            </form>




        </div>
    </div>
</div>











</body>
</html>
