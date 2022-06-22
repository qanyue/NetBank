package com.example.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

@WebServlet(name = "BuyFundServlet", value = "/BuyFundServlet")
public class BuyFundServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String fundBuyNum = request.getParameter("fundBuyNum");
        String f_id = request.getParameter("radioSelect");
        HttpSession session = request.getSession();
        String c_id = (String) session.getAttribute("c_id");
        System.out.println(c_id);
        System.out.println(f_id);
        String updateFund = "update finance.client_fund cfd set cf_sum=cf_sum+" + fundBuyNum + "from finance.client cli where " +
                "cli.c_id = cfd.c_id and cli.c_id='" + c_id + "' and cfd.f_id = '" + f_id + "'; ";
        String queryFund = "select * from finance.client_fund cfd, finance.client cli " +
                "where cfd.c_id = cli.c_id and cli.c_id = '" + c_id + "' and cfd.f_id = '" + f_id + "';";
        Statement stmt = null;
        ResultSet rs = null;
        try (Connection conn = GaussDBQuery.getConnetion()) {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
//            System.out.println(idCard);
//            System.out.println(getCid);

            String insertFund = String.format("insert into finance.client_fund values('%s','%s','1','盈利','0','%s',%s,'20');", c_id.strip(), f_id.strip(), LocalDate.now(), fundBuyNum);
//            System.out.println(insertFund);
            rs = stmt.executeQuery(queryFund);
            if(rs.next()){
                stmt.executeUpdate(updateFund);
                rs.close();
            }else{
                stmt.executeUpdate(insertFund);
                rs.close();
            }
            rs = stmt.executeQuery("select * from finance.client_fund cfd where c_id=" + c_id);
            out.println("<h2>购买成功</h2>");
            out.println("您的基金资产目前如下:<br>");
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
}
