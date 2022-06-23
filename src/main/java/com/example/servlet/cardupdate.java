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

@WebServlet(name = "cardupdate", value = "/cardupdate")
public class cardupdate extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<link rel=\"stylesheet\" href=\"css/index.css\">");
        Statement stmt = null;
        ResultSet rs = null;
        String id =request.getParameter("id");
        String caid =request.getParameter("caid");
        String password =request.getParameter("password");
        String type =request.getParameter("type");
        ArrayList<String> infos = new ArrayList<>();
        ArrayList<String> cols = new ArrayList<>();
        Collections.addAll(infos,id,caid,password,type);
        Collections.addAll(cols,"c_id","ca_id","ca_password","ca_type");
        String selectsql = "select * from finance.card where c_id = '" + id + "' and ca_id = '" + caid + "';";
        String insertsql = String.format("insert into finance.card values('%s','%s','%s','%s','0','使用');"
                , caid.strip(), id.strip(), password, type);
        String updatesql = "update finance.card";
        String selectclient = "select * from finance.client where c_id = '" + id + "';";
        updatesql = GaussDBQuery.sqlhandle(updatesql,infos,cols,"set");
        updatesql = updatesql + " where c_id = '" + id + "' and ca_id = '" + caid + "';";
        if(id.isEmpty() || caid.isEmpty()){
            out.println("<h1>请输入用户编号和银行卡编号！");
            return;
        }
        System.out.println(selectsql);
        System.out.println(insertsql);
        System.out.println(updatesql);
        try (Connection conn = GaussDBQuery.getConnetion()) {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(selectclient);
            if(!rs.next()){
                out.println("<h1>用户尚未注册！");
            }else{
                rs = stmt.executeQuery(selectsql);
                if(!rs.next()){
                    int i = stmt.executeUpdate(insertsql);
                    if(i == 0){
                        out.println("<h1>添加失败");
                    }else{
                        out.println("<h1>添加成功");
                    }
                }else{
                    if(password.isEmpty() && type.isEmpty()){
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