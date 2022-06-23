<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>查询</title>
    <link rel="stylesheet" href="css/Query.css">
</head>

<body>




<div class="cotainer">

    <div class="box1">

        <div class="head1"><br><br><br></div>

        <div class="head2">
            <p>请进行查询</p>
        </div>

        <div class="Sidebar">
            <a href="withdraw.jsp"><button>取款</button></a>
        </div>

        <div class="form1">
            <form  action="CardQuery" method="post">
                <label >请输入查询的银行卡号:<br><input type="number" name="card_num" minlength="7"  size="50"></label><br>
                <label >请输入银行卡密码:<br><input type="password" name="password"></label><br><br>
                <input type="submit" value="查询银行卡余额">
            </form>
        </div>

        <div class="form2">
            <form  action="AssetQuery" method="post">
                <br>
                <label>
                    请输入身份证号码:<br>
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
        </div>

            </form>

        </div>
    </div>
</div>


</body>
</html>