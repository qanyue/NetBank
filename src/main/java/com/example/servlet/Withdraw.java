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

@WebServlet(name = "Withdraw", value = "/Withdraw")
public class Withdraw extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        out.println("<meta http-equiv=\"refresh\" content=\"2;URL=index.jsp\">");
        String cardNum = request.getParameter("card_num");
        String withdrawNum = request.getParameter("withdrawNum");
        String password = request.getParameter("password");
        String sql = "UPDATE finance.card set card.ca_deposit=card.ca_deposit-"+withdrawNum+" where card.ca_id="+cardNum;
        String depositQuery = "select ca_deposit from finance.card where card.ca_id="+cardNum;
        try(Connection conn = GaussDBQuery.getConnetion()) {
            Statement stmt = conn.createStatement();
            ResultSet rs   = stmt.executeQuery(depositQuery);
            if(!rs.next()){
                 out.println("<h1>对不起输入的银行卡错误，请重试");
                 return;
            }
            double depositNum = rs.getDouble("ca_deposit");
            if(Double.parseDouble(withdrawNum) <= depositNum){
                if(stmt.executeUpdate(sql)>0) {
                    out.println("<h1>取款成功");
                } else  {
                    out.println("<h1>取款失败，清重试");
                }
            }else {
                out.println("<h1>你的余额不足");
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
