<%--
  Created by IntelliJ IDEA.
  User: 40843
  Date: 2022/6/22
  Time: 8:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.servlet.GaussDBQuery" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %><%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.alibaba.fastjson.JSONObject" %>
<%@ page import="java.io.File" %>
<%@ page import="java.io.IOException" %>
<html>
<head>
    <title>用户信息</title>
    <link rel="stylesheet" href="css/user.css">
</head>
    <%
        Statement stmt = null;
        ResultSet userRs = null;
        ResultSet cardRs = null;
        ResultSet productRs = null;
        ResultSet fundRs = null;
        ResultSet insuranceRs = null;
        PrintWriter printWriter = response.getWriter();
        String c_id = (String) session.getAttribute("c_id");
        System.out.println(c_id);
        String userSql = "select * from finance.client where c_id = '" + c_id + "';";
        String cardSql = "select * from finance.card where c_id = '" + c_id + "';";
        String productSql = "select * from finance.client_products where c_id = '" + c_id + "';";
        String fundSql = "select * from finance.client_fund where c_id = '" + c_id + "';";
        String insuranceSql = "select * from finance.client_insurance where c_id = '" + c_id + "';";
        String name = null;
        Connection conn = GaussDBQuery.getConnetion();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            userRs = stmt.executeQuery(userSql);
            if (userRs.next()) {
                name = userRs.getString("c_name");
            }
        %>
<body>
<div class="container">
    <div class="box1">
        <div class="head1">

        </div>
        <div class="head2">
            <h1> 欢迎您，<%=name %>！</h1>    <br>
        </div>
        <div class="gap1">

        </div>
        <div class="form">
            <div >
                您持有的银行卡信息如下:
                <br>
                <br>
                <%
                    cardRs = stmt.executeQuery(cardSql);
                    if(cardRs.next()){
                        ArrayList<LinkedHashMap<String, Object>> cardrows = GaussDBQuery.getSelectRestult(cardRs);
                        ArrayList<String> cardcols_name = new ArrayList<>(cardrows.get(0).keySet());
                        try {
                            JSONObject attributeName = GaussDBQuery.getAttributeName(new File("D:\\AttributDic.json"));
                            out.println("<style> table, th, td { border:1px solid black;} </style>");
                            out.println("<table>");
                            out.println("<tr>");
                            for(String col:cardcols_name){
                                if("ca_password".equals(col)){
                                    continue;
                                }
                                col = attributeName.getString(col);
//            System.out.println(col);
                                out.print("<th>"+col+ "</th>");
                            }
                            out.println("</tr>");
                            for(LinkedHashMap<String,Object> row: cardrows){
                                row.remove("ca_password");
                                out.println("<tr>");
                                for(String filed:row.keySet()){
                                    out.print("<th>"+row.get(filed)+"</th>");
                                }
                                out.println("</tr>");
                            }
                            out.println("</table>");
                            out.println("<br>");
                        } catch (IOException e) {
                            System.out.println("数据库字典未找到");
                        }
                    }

                %>
            </div>
            <br>
            <div>
                您持有的资产信息如下:
                <br>
                <br>
                <%
                    productRs = stmt.executeQuery(productSql);
                    if(productRs.next()){

                        ArrayList<LinkedHashMap<String, Object>> prrows = GaussDBQuery.getSelectRestult(productRs);
                        ArrayList<String> prcols_name = new ArrayList<>(prrows.get(0).keySet());
                        try {
                            JSONObject attributeName = GaussDBQuery.getAttributeName(new File("D:\\AttributDic.json"));
                            out.println("<style> table, th, td { border:1px solid black;} </style>");
                            out.println("<table>");
                            out.println("<tr>");
                            for(String col:prcols_name){
                                col = attributeName.getString(col);
//            System.out.println(col);
                                out.print("<th>"+col+ "</th>");
                            }
                            out.println("</tr>");
                            for(LinkedHashMap<String,Object> row: prrows){

                                out.println("<tr>");
                                for(String filed:row.keySet()){
                                    out.print("<th>"+row.get(filed)+"</th>");
                                }
                                out.println("</tr>");
                            }
                            out.println("</table>");
                            out.println("<br>");
                        } catch (IOException e) {
                            System.out.println("数据库字典未找到");
                        }
                    }
                    fundRs = stmt.executeQuery(fundSql);
                    if(fundRs.next()){
                        ArrayList<LinkedHashMap<String, Object>> furows = GaussDBQuery.getSelectRestult(fundRs);
                        ArrayList<String> fucols_name = new ArrayList<>(furows.get(0).keySet());
                        try {
                            JSONObject attributeName = GaussDBQuery.getAttributeName(new File("D:\\AttributDic.json"));
                            out.println("<style> table, th, td { border:1px solid black;} </style>");
                            out.println("<table>");
                            out.println("<tr>");
                            for(String col:fucols_name){
                                col = attributeName.getString(col);
//            System.out.println(col);
                                out.print("<th>"+col+ "</th>");
                            }
                            out.println("</tr>");
                            for(LinkedHashMap<String,Object> row: furows){

                                out.println("<tr>");
                                for(String filed:row.keySet()){
                                    out.print("<th>"+row.get(filed)+"</th>");
                                }
                                out.println("</tr>");
                            }
                            out.println("</table>");
                            out.println("<br>");
                        } catch (IOException e) {
                            System.out.println("数据库字典未找到");
                        }
                    }
                    insuranceRs = stmt.executeQuery(insuranceSql);
                    if(insuranceRs.next()){
                        ArrayList<LinkedHashMap<String, Object>> inrows = GaussDBQuery.getSelectRestult(insuranceRs);
                        ArrayList<String> incols_name = new ArrayList<>(inrows.get(0).keySet());
                        try {
                            JSONObject attributeName = GaussDBQuery.getAttributeName(new File("D:\\AttributDic.json"));
                            out.println("<style> table, th, td { border:1px solid black;} </style>");
                            out.println("<table>");
                            out.println("<tr>");
                            for(String col:incols_name){
                                col = attributeName.getString(col);
//            System.out.println(col);
                                out.print("<th>"+col+ "</th>");
                            }
                            out.println("</tr>");
                            for(LinkedHashMap<String,Object> row: inrows){

                                out.println("<tr>");
                                for(String filed:row.keySet()){
                                    out.print("<th>"+row.get(filed)+"</th>");
                                }
                                out.println("</tr>");
                            }
                            out.println("</table>");
                            out.println("<br>");
                        } catch (IOException e) {
                            System.out.println("数据库字典未找到");
                        }
                    }

                %>
            </div>
        </div>

        <br>
<%--        <div class="button">--%>
            <div class="gap2">

            </div>
            <div class="head">
               <br> 请选择您要进行的业务<br><br>
            </div>

            <div class="Button1">
                <a href="Deposit.jsp"><button>存款</button></a>

            </div>

            <div class="Button2">
                <a href="withdraw.jsp"><button>取款</button></a>
            </div>

            <div class="Button3">
                <a href="BuyFund.jsp"><button>购买基金</button></a>
            </div>

            <div class="Button4">
                <a href="BuyProduct.jsp"><button>购买理财产品</button></a>
            </div>
            <div class="Button5">
                <a href="BuyInsurance.jsp"><button>购买保险</button></a>
            </div>




<%--        </div>--%>
        <div class="footer">

        </div>

    </div>
</div>

</body>
</html>
