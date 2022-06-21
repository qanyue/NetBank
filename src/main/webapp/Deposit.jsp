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
</head>
<body>
<form  action="Deposit" method="post">
    <label>请输入需要存款的银行卡号:<input type="number" name="card_num" minlength="7"  size="50"></label><br>
    <label>请输入存款金额:<input type="number" name="depositNum" size="50"></label><br>
    <label>请输入银行卡密码:<input type="password" name="password"></label>
    <input type="submit" value="存款">
</form>
</body>
</html>
