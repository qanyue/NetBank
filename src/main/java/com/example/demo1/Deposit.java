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

@WebServlet(name = "Deposit", value = "/Deposit")
public class Deposit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        out.println("<meta http-equiv=\"refresh\" content=\"2;URL=User.jsp\">");
        String cardNum = request.getParameter("card_num");
        String depositNum = request.getParameter("depositNum");
        String password = request.getParameter("password");
        String sql = "UPDATE finance.card set card.ca_deposit=card.ca_deposit+"+depositNum+" where card.ca_id="+cardNum;
        String checksql = "select * from finance.card where card.ca_id = '" + cardNum + "' and card.ca_password = '" + password + "';";
        try(Connection conn = GaussDBQuery.getConnetion()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(checksql);
            if(!rs.next()){
                out.println("<h1>输入的账号或密码错误，请重试");
                return;
            }
           if(stmt.executeUpdate(sql)>0) {
               out.println("<h1>存款成功");
           } else  {
               out.println("<h1>存款失败");
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
