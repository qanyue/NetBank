package com.example.demo1;

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
import java.util.Locale;

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
        String idCard = request.getParameter("idCard");
        String c_id = "";
        String updateFund = "update finance.client_fund cfd set cf_sum=cf_sum+" + fundBuyNum + "from finance.client cli where " +
                "cli.c_id = cfd.c_id and cli.c_id_card=" + idCard + ";";
        String queryFund = "select f_id from finance.client_fund cfd, finance.client cli where c_id_card='" + idCard + "'and cli.c_id=cfd.c_id";
        String getCid = String.format("select c_id,c_id_card from finance.client where c_id_card='%s';", idCard);

        Statement stmt = null;
        ResultSet rs = null;
        try (Connection conn = GaussDBQuery.getConnetion()) {
            stmt = conn.createStatement();
//            System.out.println(idCard);
//            System.out.println(getCid);
            rs = stmt.executeQuery(getCid);
            if(rs.next()){
                c_id = rs.getString("c_id");
                if (c_id == null) {
                    out.println("<h1>对不起，查找不到你的身份证");
                    return;
                }
            }

            String insertFund = String.format("insert into finance.client_fund values('%s','%s','1','盈利','0','%s',%s,'20');", c_id.strip(), f_id.strip(), LocalDate.now(), fundBuyNum);
//            System.out.println(insertFund);
            rs = stmt.executeQuery(queryFund);
            if (rs.getRow() != 0) {
                stmt.executeUpdate(updateFund);
                rs.close();
            } else {
                stmt.executeUpdate(insertFund);
                rs.close();
            }
            rs = stmt.executeQuery("select * from finance.client_fund cfd where c_id=" + c_id);
            out.println("<h2>购买成功</h2>");
            out.println("您的基金资产目前如下:<br>");
            GaussDBQuery.printQueryResult(rs, out);
            out.println("<br><a href=\"index.jsp\"><button>返回主页</button></a>");
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
