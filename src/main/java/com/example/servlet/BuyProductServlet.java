package com.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;


@WebServlet(name = "BuyProductServlet", value = "/BuyProductServlet")
public class BuyProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String fundBuyNum = request.getParameter("fundBuyNum");
        String p_id = request.getParameter("radioSelect");
        HttpSession session = request.getSession();
        String c_id = (String) session.getAttribute("c_id");
        System.out.println(c_id);
        System.out.println(p_id);
        String updateFund = "update finance.client_products cpd set cp_sum=cp_sum+" + fundBuyNum + "from finance.client cli where " +
                "cli.c_id = cpd.c_id and cli.c_id='" + c_id + "' and cpd.p_id = '" + p_id + "'; ";
        String queryFund = "select * from finance.client_products cpd, finance.client cli " +
                "where cpd.c_id = cli.c_id and cli.c_id = '" + c_id + "' and cpd.p_id = '" + p_id + "';";
        String remainFund = "SELECT p_remain from finance.products where products.p_id = '" + p_id + "';";
        String buyFund = "UPDATE finance.products SET p_remain = p_remain - " + fundBuyNum + "where p_id = '" + p_id + "';";
        Statement stmt = null;
        ResultSet rs = null;
        try (Connection conn = GaussDBQuery.getConnetion()) {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
//            System.out.println(idCard);
//            System.out.println(getCid);
            rs = stmt.executeQuery(remainFund);
            double remain = 0;
            if(rs.next()){
                remain = rs.getDouble("p_remain");
            }
            if(remain < Double.parseDouble(fundBuyNum)){
                out.println("<h2>余量不足</h2>");
                return ;
            }
            String insertFund = String.format("insert into finance.client_products values('%s','%s','1','盈利','0','%s',%s);", c_id.strip(), p_id.strip(), LocalDateTime.now(), fundBuyNum);
//            System.out.println(insertFund);
            rs = stmt.executeQuery(queryFund);
            if(rs.next()){
                stmt.executeUpdate(buyFund);
                stmt.executeUpdate(updateFund);
                rs.close();
            }else{
                stmt.executeUpdate(buyFund);
                stmt.executeUpdate(insertFund);
                rs.close();
            }
            rs = stmt.executeQuery("select * from finance.client_products cpd where c_id=" + c_id);
            out.println("<h2>购买成功</h2>");
            out.println("您的理财产品资产目前如下:<br>");
            GaussDBQuery.printQueryResult(rs, out);
            out.println("<br><a href=\"User.jsp\"><button>返回主页</button></a>");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
