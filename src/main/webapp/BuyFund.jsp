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
    <title>购买基金</title>
</head>
<body>
<h1> 请购买数量不要超过基金余量</h1>
<br>
<form action="BuyFundServlet" method="post">
    <%
        Statement stmt = null;
        ResultSet rs = null;
        try (Connection conn = GaussDBQuery.getConnetion()) {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("select * from finance.fund;");

            GaussDBQuery.printQueryRadio(rs, out, "f_id");
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
    <div style="text-align: center;">
        <label>
            购买数量
            <input type="number" name="fundBuyNum" step="0.01" size="30">
        </label><br>
        <label>
            请输入您的身份证号
            <input type="text" name="idCard"  maxlength="18" size="30">
        </label>
        <input type="submit" value="购买 ">
    </div>
</form>

</body>
</html>
