<%--
  Created by IntelliJ IDEA.
  User: 40843
  Date: 2022/6/22
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>更改密码</title>
    <link rel="stylesheet" href="css/passwordchange.css">
</head>


<body>

    <div class="head">

    </div>
    <div class="change">
        <br><br><br>
        <form  action="passwordchange" method="post">

            <div class="form">
                <h1>改变密码</h1>
                <br><br>
                <%--        <span style="color: black">用户名</span>--%>
                <label>请输入新密码:<br><br><input type="password" name="password" placeholder="新密码" ></label><br><br>
                <label>请确认密码:<br><br><input type="password" name="confirm" placeholder="确认密码"></label><br><br>
                <br><br><br>
                <!--登陆按键和注册按键-->



                <input type="submit" value="修改">
            </div>
            <br>
            <!--账号-->



        </form>
    </div>


</body>
</html>
