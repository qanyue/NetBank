package com.example.servlet;

import com.example.DAOUtils.DaoUtil;
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

@WebServlet(name = "ProductServlet", value = "/ProductServlet")
public class ProductServlet extends HttpServlet {

    //TODO 未登录跳转
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
        if("client_products".equals(asset_type.strip())){
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
        String id = request.getParameter("id");
        //TODO 密码 支持名字模糊搜索
//        String password = request.getParameter("password");
        SqlSession sqlSession = DaoUtil.getSqlSession();
        SysServices services = sqlSession.getMapper(SysServices.class);
        String name = request.getParameter("name");
        Product product = new Product();

        product.setP_id(request.getParameter("id"));
        if(name != null){
            product.setP_name("%"+name+"%");
        }
        product.setP_status(request.getParameter("status"));
            List<Product> products =  services.s_queryProductMulty(product);
            if(products.isEmpty()) {
                out.println("<h1>无法查找，请精简查找条件");
            }
        ArrayList<LinkedHashMap<String,Object>> list = new ArrayList<>();
            try {
                for(Product p:products) {
                    System.out.println(p.getP_name());
                    list.add(GaussDBQuery.beanToMap(p));
                }
                GaussDBQuery.PrintItemTable(list,out,"p_id");
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

