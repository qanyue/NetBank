<%--
  Created by IntelliJ IDEA.
  User: qanyu
  Date: 2022/5/31
  Time: 21:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>存款</title>
    <link rel="stylesheet" href="css/deposit.css">
</head>
<body>
<div class="cotainer">

    <div class="box1">

        <div class="head1"></div>

        <div class="head2">
            <p>欢迎您存款</p>
        </div>

        <div class="Sidebar">
            <a href="withdraw.jsp"><button>取款</button></a>
        </div>

        <div class="form">
            <form  action="Deposit" method="post">

                <label>银行卡号:</br><input type="number" name="card_num" minlength="7"  size="50"></label><br><br>
                <label>存款金额:</br><input type="number" name="depositNum" size="50"></label><br><br>
                <label>银行卡密码:</br><input type="password" name="password"></label><br><br>

                <input type="submit" value="存款" >

            </form>

        </div>
    </div>
</div>
</body>
</html>
