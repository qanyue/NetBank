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


    <link rel="stylesheet" href="css/passwordchange.css">
    <title>更改密码</title>
    <script type="text/javascript">
        function check() {
            // var oldpwd = document.getElementById("oldpwd").value;
            var newpwd = document.getElementById("newpwd").value;
            var confirmpwd = document.getElementById("confirmpwd").value;
            if( newpwd != "" && confirmpwd != "") {
                if(newpwd != confirmpwd) {
                    document.getElementById("secondpwd").style.display = "inline";
                    return false;
                }
                return true;
            } else {
                alert("密码不能为空！")
            }
        }
    </script>
</head>

<body>


<div class="head">

</div>
<div class="change">
    <br><br><br>
    <form  action="passwordchange" method="post" onsubmit="return check()">

        <div class="form">
            <h1>改变密码</h1>
            <br><br>
            <label>请输入新密码:<input type="password" name="newpwd" id="newpwd">
        <span style="color:red;">*</span>
    </label><br>

    <label>请确认密码:<input type="password" id= "confirmpwd" name="confirmpwd" >
        <span style="color:red;">*</span>
        <span style="display: none;color: red" id="secondpwd">两次密码不一样！</span>
    </label><br><br><br>
    <input type="submit" value="修改" >
        </div>
        <br>
</form>
</div>

</body>
</html>
