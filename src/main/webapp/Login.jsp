<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="UTF-8">
    <title>用户登陆</title>
    <link rel="stylesheet" href="css/index.css">

</head>

<body>
<h1></h1>
<br><br><br><br><br><br><br><br><br>
<!--标题-->
<h1 style="color: PaleVioletRed">登陆界面</h1>
<div class="container">
    <div class="login">
        <form action="Login" method="post">
            <br>
            <!--账号-->
            <span style="color: blueviolet">用户名</span>
            <input type="text" placeholder="Username" name="username">
            <br><br>
            <!--密码-->
            <span style="color: blueviolet">密码</span>
            <input type="password" placeholder="Password" name="password">
            <br><br>
            <!--登陆按键和注册按键-->
            <span style="color: PaleVioletRed">管理员</span>
            <input type="radio" value="manager" name="type" id="manager"><label style="color: PaleVioletRed">用户</label>
            <input type="radio" value="user" name="type" id="user">
            <br><br>
            <button type="submit" style="color:blueviolet">登陆</button>
        </form>
    </div>
</div>
</body>
</html>