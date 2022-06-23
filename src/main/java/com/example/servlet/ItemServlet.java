package com.example.servlet;

import com.example.DAOUtils.DaoUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

@WebServlet(name = "ItemServlet", value = "/ItemServlet")
public class ItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String way = request.getParameter("button");
            String p_id = request.getParameter("p_id");
            String item = request.getParameter("type");
            boolean result=false;
            if("删除".equals(way)){
                if("insurance".equals(item)){
                    result =insuranceDelete(request,response);
                }else if("product".equals(item)){
                    result =productDelete(request,response);
                }else if("fund".equals(item)){
                   result = fundDelete(request,response);
                }
            }else "删除".equals(way) {
                return;
        }


    }
    public  static boolean productDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("p_id");

        PrintWriter out = response.getWriter();
        if(id.isEmpty()){
            out.println("出错了请重试");
            return false;
        }
        return DaoUtil.s_deleteFund(id);
    }
    public  static boolean fundDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("f_id");
        PrintWriter out = response.getWriter();
        if(id.isEmpty()){
            out.println("出错了请重试");
            return false;
        }
        return DaoUtil.s_deleteFund(id);
    }
    public  static boolean insuranceDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("i_id");
        PrintWriter out = response.getWriter();
        if(id.isEmpty()){
            out.println("出错了请重试");
            return false;
        }
        return DaoUtil.s_deleteInsurance(id);
    }
    public static boolean insuranceUpdate(HttpServletRequest request,HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
       String id = request.getParameter("i_id");
       PrintWriter out = response.getWriter();

        return false;
    }

}
