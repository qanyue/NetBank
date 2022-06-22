<%--
  Created by IntelliJ IDEA.
  User: qanyu
  Date: 2022/6/2
  Time: 21:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.servlet.GaussDBQuery" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<html>
<head>
    <title>购买理财产品</title>
    <link rel="stylesheet" href="css/buyproduct.css">
</head>

<body>
<div class="box1">


    <div class="head1">

    </div>

    <div class="head2">
        <h1>欢迎您购买理财产品</h1>
    </div>
    <div class="Sidebar">
        <a href="User.jsp"><button>主页</button></a>
    </div>
    <br>
    <div class="form">
        <h1> 请购买数量不要超过理财产品余量</h1>
        <form action="BuyProductServlet" method="post">
            <div class="table">
                <%
                    Statement stmt = null;
                    ResultSet rs = null;
                    try (Connection conn = GaussDBQuery.getConnetion()) {
                        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                        rs = stmt.executeQuery("select * from finance.products;");
                        GaussDBQuery.printQueryRadioP(rs, out, "p_id");
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    } finally {
                        if (rs != null) {
                            System.out.println("关闭Rusultset....");
                            try {
                                rs.close();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                        if (stmt != null) {
                            System.out.println("关闭Statement....");
                            try {
                                stmt.close();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }


                    }

                %>
            </div>

            <div style="text-align: center;">
                <label>
                    购买金额
                    <input type="number" name="fundBuyNum" step="0.01" size="30">
                </label><br><br>
                <input type="submit" value="购买 ">
            </div>
        </form>
    </div>
    <div class="footer">

    </div>
</div>

</body>
</html>
