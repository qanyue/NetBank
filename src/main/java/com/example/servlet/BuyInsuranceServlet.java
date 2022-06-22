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
import java.time.LocalDate;
import java.time.LocalDateTime;

@WebServlet(name = "BuyInsuranceServlet", value = "/BuyInsuranceServlet")
public class BuyInsuranceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String fundBuyNum = request.getParameter("fundBuyNum");
        String i_id = request.getParameter("radioSelect");
        HttpSession session = request.getSession();
        String c_id = (String) session.getAttribute("c_id");
        System.out.println(c_id);
        System.out.println(i_id);
        String updateInsurance = "update finance.client_insurance cid set ci_sum=ci_sum+" + fundBuyNum + "from finance.client cli where " +
                "cli.c_id = cid.c_id and cli.c_id='" + c_id + "' and cid.i_id = '" + i_id + "'; ";
        String queryInsurance = "select * from finance.client_insurance cid, finance.client cli " +
                "where cid.c_id = cli.c_id and cli.c_id = '" + c_id + "' and cid.i_id = '" + i_id + "';";
        Statement stmt = null;
        ResultSet rs = null;
        try (Connection conn = GaussDBQuery.getConnetion()) {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
//            System.out.println(idCard);
//            System.out.println(getCid);
            String insertInsurance = String.format("insert into finance.client_insurance values('%s','%s','1','正常','0','%s',%s,'20');", c_id.strip(), i_id.strip(), LocalDateTime.now(), fundBuyNum);
//            System.out.println(insertFund);
            rs = stmt.executeQuery(queryInsurance);
            if(rs.next()){
                stmt.executeUpdate(updateInsurance);
                rs.close();
            }else{
                stmt.executeUpdate(insertInsurance);
                rs.close();
            }
            rs = stmt.executeQuery("select * from finance.client_insurance cid where c_id=" + c_id);
            out.println("<h2>购买成功</h2>");
            out.println("您的保险资产目前如下:<br>");
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
