package com.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

@WebServlet(name = "clientsearch", value = "/clientsearch")
public class clientsearch extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<link rel=\"stylesheet\" href=\"css/temp.css\">");
        Statement stmt = null;
        ResultSet rs = null;
        String id =request.getParameter("id");
        String name =request.getParameter("name");
        String mail =request.getParameter("mail");
        String card =request.getParameter("card");
        String phone =request.getParameter("phone");
        ArrayList<String> infos = new ArrayList<>();
        ArrayList<String> cols = new ArrayList<>();
        Collections.addAll(infos,id,name,mail,card,phone);
        Collections.addAll(cols,"c_id","c_name","c_mail","c_id_card","c_phone");
        String sql = "select * from finance.client";
        sql = GaussDBQuery.sqlhandle(sql,infos,cols,"where");
        System.out.println(sql);
        try (Connection conn = GaussDBQuery.getConnetion()) {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(sql);
            out.println("<div class=\"head\">");
            out.println("</div><br>\n" );
            out.println(
                    "<div class=\"table\">\n"
            );
            if(!rs.next()){
                out.println("<h1>查询不到有关信息</h1>");
            }

            GaussDBQuery.printQueryResult(rs,out);

            out.println("</div>\n" );
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
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
