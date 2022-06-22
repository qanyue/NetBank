<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
  <meta charset="UTF-8">
  <title>用户登陆</title>
  <link rel="stylesheet" href="css/login.css">

</head>

<body>
<h1></h1>
<br><br>
<!--标题-->
<h1 style="color: #555555">欢迎光临</h1>
<div class="container">
  <div class="login">

    <form action="Login" method="post">
      <div class="form">
        <br><br>
<%--        <span style="color: black">用户名</span>--%>
        <input type="text" placeholder="用户名" name="username">
        <br><br>
        <!--密码-->
<%--        <span style="color: black">密码</span>--%>
        <input type="password" placeholder="密码" name="password">
        <br><br><br>
        <!--登陆按键和注册按键-->
        <input type="radio" value="manager" name="type" id="manager" >
        <span style="color: #555555">管理员</span>
        <input type="radio" value="user" name="type" id="user">
        <label style="color: #555555">用户</label>
      </div>
      <br>
      <!--账号-->



      <br><br>
      <div class="button">
        <button type="submit" style="color:#f4f4f4">登陆</button>
      </div>

    </form>
  </div>
</div>
</body>
</html>