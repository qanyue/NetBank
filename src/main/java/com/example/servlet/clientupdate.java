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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

@WebServlet(name = "clientupdate", value = "/clientupdate")
public class clientupdate extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<link rel=\"stylesheet\" href=\"css/index.css\">");
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
        String selectsql = "select * from finance.client where c_id = '" + id + "';";
        String insertsql = String.format("insert into finance.client values('%s','%s','%s','%s','%s','使用');"
                , id.strip(), name.strip(), mail, card,phone);
        String updatesql = "update finance.client";
        updatesql = GaussDBQuery.sqlhandle(updatesql,infos,cols,"set");
        updatesql = updatesql + " where c_id = '" + id + "';";
        String insertuser = String.format("insert into finance.users values('%s','123456');",id.strip());
        if(id.isEmpty()){
            out.println("<h1>请输入用户编号！");
            return;
        }
        System.out.println(selectsql);
        System.out.println(insertsql);
        System.out.println(updatesql);
        try (Connection conn = GaussDBQuery.getConnetion()) {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(selectsql);
            if(!rs.next()){
                int i = stmt.executeUpdate(insertsql);
                int j = stmt.executeUpdate(insertuser);
                if(i == 0 || j == 0){
                    out.println("<h1>添加失败");
                }else{
                    out.println("<h1>添加成功");
                }
            }else{
                if(name.isEmpty() && mail.isEmpty() && card.isEmpty() && phone.isEmpty()){
                    out.println("<h1>修改失败");
                }else{
                    int i = stmt.executeUpdate(updatesql);
                    if(i == 0){
                        out.println("<h1>修改失败");
                    }else{
                        out.println("<h1>修改成功");
                    }
                }
            }
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
