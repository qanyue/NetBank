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
        .distance{
            font-family: 宋体;font-size:18px;color: black;
            border :3px red;
            text-align: center;
        }

        h1{
            text-align: center;

            padding-top: 20px;
            padding-bottom: 20px;

        }

    </style>
</head>
<body background="picture/background.jpeg">
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
<div class="distance">
    <form action="" class="distance" >
        在对应框填入对应内容<br>
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
        <input type="submit" class="tom" name="search" value="搜索">
        <input type="submit" class="tom" name="delete" value="删除">
        <input type="submit" class="tom" name="add" value="增加">
        <input type="submit" class="tom" name="update" value="更新">
    </form>
</div>
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
<%
    String id =request.getParameter("id");
    String name =request.getParameter("name");
    String mail =request.getParameter("mail");
    String card =request.getParameter("card");
    String phone =request.getParameter("phone");
    String search =request.getParameter("search");
    String delete =request.getParameter("delete");
    String add =request.getParameter("add");
    String update =request.getParameter("update");
    String SQL =null;
    String search1 ="SELECT * FROM finance.client WHERE ";
    if(search!=null){
        int tag = 1;//为1时不加AND，遇见一个不空就变为0，加AND。
        if(id!=null){
            SQL =search1+" c_id ="+id;
            tag = 0;
        }
        else {
            SQL =search1;
        }
        if(name!=null){
            if(tag==1){
                SQL = SQL+"c_name ="+name;
                tag = 0;
            }
            else {
                SQL =SQL +"AND c_name ="+ name;
            }
        }
        if(mail!=null){
            if(tag==1){
                SQL = SQL+"c_mail ="+mail;
                tag = 0;
            }
            else {
                SQL =SQL +"AND c_mail ="+ mail;
            }
        }
        if(card!=null){
            if(tag==1){
                SQL = SQL+"c_id_card ="+card;
                tag = 0;
            }
            else {
                SQL =SQL +"AND c_id_card ="+ card;
            }
        }
        if(phone !=null){
            if(tag ==1){
                SQL = SQL+"c_phone ="+phone+";";
            }
            else {
                SQL =SQL +"AND c_phone ="+ phone+";";
            }
        }
        else {
            SQL = SQL + ";";
        }
        rs =stmt.executeQuery(SQL);


    }
    else if (delete!=null){

    }
    else if (add!=null){

    }
    else if (update!=null){


    }








%>


</body>
</html>
