<%--
  Created by IntelliJ IDEA.
  User: forever
  Date: 2022/6/22
  Time: 15:05
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

    <title>管理员对用户数据的增删改</title>
    <link rel="stylesheet" href="./css/manager.css">

</head>
<body>
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

<div class="cotainer">

    <div class="box1">

        <div class="head1"><br><br><br></div>

        <div class="head2">
            <h1>用户信息管理</h1>
        </div>

        <div class="Sidebar">
            <a href="admin.jsp"><img src="images/Home.png"></a>
        </div>

        <div class="form">
            <form action="" class="distance" name="form1">

                客户编号：<br>
                <input type="text" class="tom" name="id"><br><br>
                客户名称：<br>
                <input type="text" class="tom" name="name" ><br><br>
                客户邮箱：<br>
                <input type="text" class="tom" name="mail" ><br><br>
                客户身份证：<br>
                <input type="text" class="tom" name="card" ><br><br>
                客户手机号：<br>
                <input type="text" class="tom" name="phone" ><br><br>
                <input type="submit" class="tom" name="search" value="搜索" onclick="form1.action='clientsearch';form1.submit();"><br>
            <input type="submit" class="tom" name="delete" value="删除" onclick="form1.action='clientdelete';form1.submit();">
            <input type="submit" class="tom" name="add" value="增加" onclick="form1.action='clientupdate';form1.submit();">
            <input type="submit" class="tom" name="update" value="更新" onclick="form1.action='clientupdate';form1.submit();">
            </form>

        </div>
    </div>
</div>








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
  <%--  <div class="right">
    <form action="" class="right">
        <h2>删除功能区</h2><br>
        在对应框里面输入要删除的内容：<br>
        客户编号：
        <input type="text" class="tom" name="delete-id"><br>
        客户名称：
        <input type="text" class="tom" name="delete-name" ><br>
        客户邮箱：
        <input type="text" class="tom" name="delete-mail" ><br>
        客户身份证：
        <input type="text" class="tom" name="delete-id-card" ><br>
        客户手机号：
        <input type="text" class="tom" name="delete-phone" ><br>
        客户状态：
        <input type="text" class="tom" name="delete-status "><br>
        <input type="submit" class="tom" name="delete" value="删除">
    </form>
    </div>
    <div class="distance">
    <form action="" class="distance">
        <h2>增加功能区</h2><br>
        在对应框里面输入要增加的内容：<br>
        客户编号：
        <input type="text" class="tom" name="add-id"><br>
        客户名称：
        <input type="text" class="tom" name="add-name" ><br>
        客户邮箱：
        <input type="text" class="tom" name="add-mail" ><br>
        客户身份证：
        <input type="text" class="tom" name="add-id-card" ><br>
        客户手机号：
        <input type="text" class="tom" name="add-phone" ><br>
        客户状态：
        <input type="text" class="tom" name="add-status "><br>
        <input type="submit" class="tom" name="add" value="增加">
    </form>
    </div>
    <div class="right">
    <form action="" class="right">
        <h2>更新功能区</h2><br>
        在对应框里面输入要更新的内容：<br>
        客户编号：
        <input type="text" class="tom" name="update-id"><br>
        客户名称：
        <input type="text" class="tom" name="update-name" ><br>
        客户邮箱：
        <input type="text" class="tom" name="update-mail" ><br>
        客户身份证：
        <input type="text" class="tom" name="update-id-card" ><br>
        客户手机号：
        <input type="text" class="tom" name="update-phone" ><br>
        客户状态：
        <input type="text" class="tom" name="update-status "><br>
        <input type="submit" class="tom" name="update" value="更新">
    </form>
    </div>--%>



</body>
</html>
