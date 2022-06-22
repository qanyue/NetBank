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

@WebServlet(name = "AssetQuery", value = "/AssetQuery")
public class AssetQuery extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        String idCard = request.getParameter("id_card");
        String[] assetQuery = request.getParameterValues("asset");
        String base = "select  * from finance.%s where c_id='%s';" ;
        String getCid = String.format("select c_id,c_id_card from finance.client where c_id_card='%s';", idCard);
        String c_id = "";
        ResultSet rs = null;
        Statement stmt = null;
        try(Connection conn = GaussDBQuery.getConnetion()) {
              stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            System.out.println(idCard);
            System.out.println(getCid);
            rs = stmt.executeQuery(getCid);
            if(rs.next()){
                c_id = rs.getString("c_id").strip();

                if (c_id == null) {
                    out.println("<h1>对不起，查找不到你的身份证");
                    return;
                }
            }else {
                out.println("<h1>对不起，查找不到你的身份证");
                return;
            }
            for(String assetVariety : assetQuery) {
                String assertSql = String.format(base,assetVariety,c_id);
                System.out.println(assertSql);
                rs = stmt.executeQuery(assertSql);
//                out.println("<h2>"+assetVariety.substring(7)+"</h2>");
                if(rs.next())
                {
                    rs.beforeFirst();
                    GaussDBQuery.printQueryResult(rs,out);
                    rs.close();
                }else {
                    out.println("您尚未拥有此项资产<br>");
                }
            }
            out.println("<a href=\"index.jsp\"><button>返回主页</button></a>");
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            if (rs != null) {
                System.out.println("正在关闭资产ResultSet");
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
           if (stmt != null) {
                System.out.println("正在关闭资产ResultSet");
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
