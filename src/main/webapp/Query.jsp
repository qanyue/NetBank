<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<form  action="CardQuery" method="post">
    <label >请输入查询的银行卡号:<input type="number" name="card_num" minlength="7"  size="50"></label><br>
    <label >请输入银行卡密码:<input type="password" name="password"></label><br>
    <input type="submit" value="查询银行卡余额">
</form>

<form  action="AssetQuery" method="post">
    <br>
    <label>
        请输入身份证号码:
        <input type="number" name="id_card" maxlength="18">
    </label><br>
    请选择资产需要查询的项目:<br>
    <label>
        <input type="checkbox" name="asset" value="client_products">基金
    </label>
    <label>
        <input type="checkbox" name="asset" value="client_insurance">保险
    </label>
    <label>
        <input type="checkbox" name="asset" value="client_fund">理财产品
    </label><br>
    <input type="submit" value="查询资产">
</form>


</body>
</html>