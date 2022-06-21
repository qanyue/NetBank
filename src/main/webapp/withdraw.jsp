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
  <title>取款</title>
</head>
<body>
<form  action="Withdraw" method="post">
  <label>请输入需要取款的银行卡号:<input type="number" name="card_num" minlength="7"  size="50"></label><br>
  <label>请输入取款金额:<input type="number" name="withdrawNum" size="50"></label><br>
  <label>请输入银行卡密码:<input type="password" name="password"></label>
  <input type="submit" value="取款">
</form>
</body>
</html>
