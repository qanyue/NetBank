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

@WebServlet(name = "cardsearch", value = "/cardsearch")
public class cardsearch extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Statement stmt = null;
        ResultSet rs = null;
        out.println("<link rel=\"stylesheet\" href=\"css/temp.css\">");
        String id =request.getParameter("id");
        String caid =request.getParameter("caid");
        String password =request.getParameter("password");
        String type =request.getParameter("type");
        ArrayList<String> infos = new ArrayList<>();
        ArrayList<String> cols = new ArrayList<>();
        Collections.addAll(infos,id,caid,password,type);
        Collections.addAll(cols,"c_id","ca_id","ca_password","ca_type");
        String sql = "select * from finance.card";
        sql = GaussDBQuery.sqlhandle(sql,infos,cols,"where");
        System.out.println(sql);
        try (Connection conn = GaussDBQuery.getConnetion()) {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(sql);
            if(!rs.next()){
                out.println("<h1>查询不到有关信息");
            }
            GaussDBQuery.printQueryResult(rs,out);
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

