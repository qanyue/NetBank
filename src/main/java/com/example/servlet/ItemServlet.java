package com.example.servlet;

import com.example.DAOUtils.DaoUtil;
import com.example.model.Fund;
import com.example.model.Insurance;
import com.example.model.Product;
import com.example.service.SysServices;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;

@WebServlet(name = "ItemServlet", value = "/ItemServlet")
public class ItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
            String way = request.getParameter("button").strip();
            String item = request.getParameter("type");
        PrintWriter out = response.getWriter();
        System.out.println("\n"+way+"产品");
        System.out.println("\n"+item+"产品");
        if("删除".equals(way)){
                System.out.println("进入删除产品");
                if("insurance".equals(item) && insuranceDelete(request,response)){

                    out.println("删除保险成功");
                    System.out.println("删除保险成功");
                }else if("products".equals(item)&& productDelete(request,response)){
                    System.out.println("删除理财产品成功");
                    out.println("删除理财产品成功");
                }else if("fund".equals(item) && fundDelete(request,response)){
                    System.out.println("删除基金成功");
                    out.println("删除基金成功");
                }
            }else if( "修改".equals(way)) {
                System.out.println("进入更新产品");
                try {
                    if ("insurance".equals(item)) insuranceUpdate(request, response);
                    if ("products".equals(item)) productUpdate(request, response);
                    if ("fund".equals(item)) fundUpdate(request, response);
                } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }else{
                System.out.println("修改产品出错");
            }

    }
    public  static boolean productDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("radioSelect");

        PrintWriter out = response.getWriter();
        if(id.isEmpty()){
            out.println("出错了请重试");
            return false;
        }
        return DaoUtil.s_deleteProduct(id);
    }
    public  static boolean fundDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("radioSelect");
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
        String id = request.getParameter("radioSelect");
        PrintWriter out = response.getWriter();
        if(id.isEmpty()){
            out.println("出错了请重试");
            return false;
        }
        return DaoUtil.s_deleteInsurance(id);
    }
    public static void insuranceUpdate(HttpServletRequest request,HttpServletResponse response) throws IOException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        System.out.println("进入保险更新");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
       String id = request.getParameter("radioSelect");
       PrintWriter out = response.getWriter();
        SqlSession sqlSession = DaoUtil.getSqlSession();
        SysServices services = sqlSession.getMapper(SysServices.class);
        Insurance insurance = services.s_queryInsurance(id);
        out.println("<form action=\"UpdateServlet\" method=\"post\">");
        LinkedHashMap<String,Object> item = GaussDBQuery.beanToMap(insurance);
        GaussDBQuery.PrintChangeTable(item,out);
        out.println(" <input type=\"submit\" name='button' value=\"提交\" > </form>");
        HttpSession session = request.getSession();
        session.setAttribute("item",insurance);
        session.setAttribute("type","insurance");
    }
    public static void fundUpdate(HttpServletRequest request,HttpServletResponse response) throws IOException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        System.out.println("进入基金更新");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("radioSelect");
        PrintWriter out = response.getWriter();
        SqlSession sqlSession = DaoUtil.getSqlSession();
        SysServices services = sqlSession.getMapper(SysServices.class);
        Fund fund = services.s_queryFund(id);
        out.println("<form action=\"UpdateServlet\" method=\"post\">\n ");
        LinkedHashMap<String, Object> item = GaussDBQuery.beanToMap(fund);
        GaussDBQuery.PrintChangeTable(item, out);
        out.println(" <input type=\"submit\" name='button' value=\"提交\" > </form>");
        HttpSession session = request.getSession();
        session.setAttribute("item", fund);
        session.setAttribute("type","fund");
    }
    public static void productUpdate(HttpServletRequest request,HttpServletResponse response) throws IOException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        System.out.println("进入理财产品更新");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("radioSelect");
        PrintWriter out = response.getWriter();
        SqlSession sqlSession = DaoUtil.getSqlSession();
        SysServices services = sqlSession.getMapper(SysServices.class);
        Product product = services.s_queryProduct(id);
        out.println("<form action='UpdateServlet' method='post'> <br>");
        LinkedHashMap<String,Object> item = GaussDBQuery.beanToMap(product);
        GaussDBQuery.PrintChangeTable(item,out);
        out.println(" <input type=\"submit\" name='button' value=\"提交\" > </form>");
        HttpSession session = request.getSession();
        session.setAttribute("item",product);
        session.setAttribute("type","products");
    }

}
