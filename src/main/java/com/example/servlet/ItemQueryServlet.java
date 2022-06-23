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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@WebServlet(name = "ItemQueryServlet", value = "/ItemQueryServlet")
public class ItemQueryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        String id = request.getParameter("id");
        String asset_type = request.getParameter("asset").toString();
//        String password = request.getParameter("password");
        SqlSession sqlSession = DaoUtil.getSqlSession();
        SysServices services = sqlSession.getMapper(SysServices.class);
        System.out.println(asset_type);
        if("products".equals(asset_type.strip())){
            ProductQuery(request,response);
        }else {
            out.println("出错");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
    public boolean ProductQuery(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        //TODO 密码 支持名字模糊搜索
//        String password = request.getParameter("password");
        SqlSession sqlSession = DaoUtil.getSqlSession();
        SysServices services = sqlSession.getMapper(SysServices.class);
        Product product = new Product();
        if(!request.getParameter("id").isEmpty()){
            product.setP_id(request.getParameter("id"));
        }
        if(!request.getParameter("name").isEmpty()){
            product.setP_name("%"+request.getParameter("name")+"%");
        }
        if(!request.getParameter("status").isEmpty()){
            product.setP_status(request.getParameter("status"));
        }
        List<Product> products =  services.s_queryProductMulty(product);
        if(products.isEmpty()) {
            out.println("<h1>无法查找，请精简查找条件");
            return false;
        }
        out.println("<br>请选择操作：<br>");
        out.println("<form action=\"ItemServlet\" method=\"post\">\n ");


        ArrayList<LinkedHashMap<String,Object>> list = new ArrayList<>();
        try {
            for(Product p:products) {
                System.out.println(p.getP_name());
                list.add(GaussDBQuery.beanToMap(p));
            }
            GaussDBQuery.PrintItemTable(list,out,"p_id");
            out.println("＜input type=\"hidden\" name=\"type\" value=\"product\"＞ ");
            out.println(" <input type=\"submit\" name='button' value=\"修改 \"> ");
            out.println(" <input type=\"submit\" name='button' value=\"删除\" > </form>");
            return true;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h1>出现错误请重试");
            return false;
        }

    }
    public boolean InsuranceQuery(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        String id = request.getParameter("id");
        //TODO 密码 支持名字模糊搜索
//        String password = request.getParameter("password");
        SqlSession sqlSession = DaoUtil.getSqlSession();
        SysServices services = sqlSession.getMapper(SysServices.class);
        String name = request.getParameter("name");
        Insurance insurance = new Insurance();
        if(!request.getParameter("id").isEmpty()){
            insurance.setI_id(request.getParameter("id"));
        }
        if(!request.getParameter("name").isEmpty()){
            insurance.setI_name("%"+request.getParameter("name")+"%");
        }
        if(!request.getParameter("status").isEmpty()){
            insurance.setI_status(request.getParameter("status"));
        }
        List<Insurance> insurances =  services.s_queryInsuranceMulty(insurance);
        if(insurances.isEmpty()) {
            out.println("<h1>无法查找，请精简查找条件");
            return  false;
        }
        out.println("<br>请选择操作：<br>");
        out.println("<form action=\"ItemServlet\" method=\"post\">\n ");
        ArrayList<LinkedHashMap<String,Object>> list = new ArrayList<>();
        try {
            for(Insurance i:insurances) {
                System.out.println(i.getI_name());
                list.add(GaussDBQuery.beanToMap(i));
            }
            GaussDBQuery.PrintItemTable(list,out,"i_id");
            out.println("＜input type=\"hidden\" name=\"type\" value=\"insurance\"＞ ");
            out.println(" <input type=\"submit\" name='button' value=\"修改 \"> ");
            out.println(" <input type=\"submit\" name='button' value=\"删除\" > </form>");
            return true;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h1>出现错误请重试");
            return false;
        }

    }
    public boolean FundQuery(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        String id = request.getParameter("id");
        //TODO 密码 支持名字模糊搜索
//        String password = request.getParameter("password");
        SqlSession sqlSession = DaoUtil.getSqlSession();
        SysServices services = sqlSession.getMapper(SysServices.class);
        String name = request.getParameter("name");
        Fund fund = new Fund();
        if(!request.getParameter("id").isEmpty()){
            fund.setF_id(request.getParameter("id"));
        }
        if(!request.getParameter("name").isEmpty()){
            fund.setF_name("%"+request.getParameter("name")+"%");
        }
        if(!request.getParameter("status").isEmpty()){
            fund.setF_status(request.getParameter("status"));
        }
        List<Fund> funds =  services.s_queryFundMulty(fund);
        if(funds.isEmpty()) {
            out.println("<h1>无法查找，请精简查找条件");
            return false;
        }
        out.println("<br>请选择操作：<br>");
        out.println("<form action=\"ItemServlet\" method=\"post\">\n ");
        ArrayList<LinkedHashMap<String,Object>> list = new ArrayList<>();
        try {
            for(Fund f: funds) {
                System.out.println(f.getF_name());
                list.add(GaussDBQuery.beanToMap(f));
            }
            GaussDBQuery.PrintItemTable(list,out,"f_id");
            out.println("＜input type=\"hidden\" name=\"type\" value=\"fund\"＞ ");
            out.println(" <input type=\"submit\" name='button' value=\"修改 \"> ");
            out.println(" <input type=\"submit\" name='button' value=\"删除\" > </form>");
            return true;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h1>出现错误请重试");
            return false;
        }
    }
}
