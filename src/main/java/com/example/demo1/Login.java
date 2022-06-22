package com.example.demo1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name = "Login", value = "/Login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String type = request.getParameter("type");
        String userSql = "Select * from finance.users where c_id = '" + username + "' and c_password = '" + password + "';";
        String adminSql = "Select * from finance.adminsys where s_id = '" + username + "' and s_password = '" + password+ "';";
        try(Connection conn = GaussDBQuery.getConnetion()) {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = null;
            if("manager".equals(type)){
                System.out.println(1);
                rs = stmt.executeQuery(adminSql);
                if(!rs.next()){
                    out.println("<h1>输入的账号或密码错误，请重试");
                    return;
                }
                System.out.println(2);
                response.sendRedirect("index.jsp");//管理员界面：用户信息增删改查、银行卡信息增删改查、理财产品信息增删改查
            }else if("user".equals(type)){
                rs = stmt.executeQuery(userSql);
                if(!rs.next()){
                    out.println("<h1>输入的账号或密码错误，请重试");
                    return;
                }
                HttpSession session = request.getSession();
                session.setAttribute("c_id",username);
                response.sendRedirect("User.jsp");//客户界面：显示他自己的银行卡、理财产品、个人信息，存取款界面，理财产品购买
            }else{
                out.println("<h1>请选择登陆类别");
                return;
            }

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
