<%--
  Created by IntelliJ IDEA.
  User: forever
  Date: 2022/6/22
  Time: 9:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理人员界面</title>
    <link rel="stylesheet" href="./css/style.css">
</head>

<body>
<div class="total">
    <div class="head1">

    </div>
    <div class="head2">
        <h1>管理员界面<br><br></h1>
    </div>
    <div class="head3">
        <h1>选择你要进行的业务</h1>
    </div>



    <div class="adminsclient">

        <h2>用户数据信息</h2>
        <a href="clientdelupadd.jsp"> <button>查询或增删改信息</button></a>
    </div>

    <div class="adminscard" >
        <h2>银行卡信息</h2>
        <a href="carddelupadd.jsp" ><button>查询或增删改信息</button></a>
    </div>

    <div class="adminasset">
        <h2>金融产品修改</h2>
        <a href="s_itemQuery.jsp"><button>金融产品修改</button></a>
    </div>
<%--    <div class="adminsfund">--%>
<%--        <h2>基金信息</h2>--%>
<%--        <a href="../../../../../../../../Users/forever/Desktop/111/adminfund.jsp"><button>查询或增删改信息</button></a>--%>
<%--    </div>--%>
<%--    <div class="adminsproducts">--%>
<%--        <h2>理财产品信息</h2>--%>
<%--        <a href="adminproducts.jsp"><button>查询或增删改信息</button></a>--%>
<%--    </div>--%>

</div>


</body>
</html>
