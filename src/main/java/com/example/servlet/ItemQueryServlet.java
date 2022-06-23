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
import java.lang.reflect.InvocationTargetException;

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
        if("client_products".equals(asset_type.strip())){
                   Product product =  services.s_queryProduct(id);
            try {
                GaussDBQuery.PrintChangeTable(GaussDBQuery.beanToMap(product),out,"p_id");
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } catch (Exception e) {
                e.printStackTrace();
                out.println("<h1>出现错误清重试");
            }

        }else {
            out.println("出错");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
