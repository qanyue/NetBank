package com.example.servlet;

import com.example.DAOUtils.DaoUtil;
import com.example.model.Fund;
import com.example.model.Insurance;
import com.example.model.Product;
import com.example.model.UserLogin;
import org.apache.commons.beanutils.BeanUtils;
import org.jetbrains.annotations.NotNull;

import javax.enterprise.inject.spi.Bean;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet(name = "UpdateServlet", value = "/UpdateServlet")
public class UpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String type = session.getAttribute("type").toString().strip();
        Object item =  session.getAttribute("item");
        System.out.println("UpdateServlet:doGet():"+type);
        if ("products".equals(type) && updateAsset(item,request,"products")) {
            System.out.println("更新理财产品成功");
        }else  if("fund".equals(type) && updateAsset(item,request,"fund")){
            System.out.println("更新基金成功");
        }else if("insurance".equals(type) && updateAsset(item,request,"insurance")){
            System.out.println("更新保险成功");
        }else {
            System.out.println("更新产品出错");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    public boolean updateAsset(Object object, HttpServletRequest request, String type) {
        System.out.println("UpdataServlet"+type);
        if (type.isEmpty()) {
            System.out.println("更新产品出错");
            return false;
        }
        Fund fund;
        Product product;
        Insurance insurance;
        if (type.equals("products")) {
            System.out.println("开始更新理财产品");
            product = (Product) object;
            try {
                ArrayList<String> list = DaoUtil.getbeanInfo(product);
                for (String str : list) {
                    String temp = request.getParameter(str);
                    if (!temp.isEmpty()) {
                        BeanUtils.setProperty(product, str, temp);
                    }
                }
                return  DaoUtil.s_updateProduct(product);
            } catch (IntrospectionException | InvocationTargetException | IllegalAccessException e) {
                System.out.println("更新理财产品错误");
                e.printStackTrace();
                return false;
            }

        } else if ("fund".equals(type)) {
            System.out.println("开始更新基金");
            fund = (Fund) object;
            try {
                ArrayList<String> list = DaoUtil.getbeanInfo(fund);
                for (String str : list) {
                    String temp = request.getParameter(str);
                    if (!temp.isEmpty()) {
                        BeanUtils.setProperty(fund, str, temp);
                    }
                }
                return DaoUtil.s_updateFund(fund);

            } catch (IntrospectionException | InvocationTargetException | IllegalAccessException e) {
                System.out.println("更新基金错误");
                e.printStackTrace();
                return false;
            }

        } else if (type.equals("insurance")) {
            System.out.println("开始更新保险");
            insurance = (Insurance) object;
            try {
                ArrayList<String> list = DaoUtil.getbeanInfo(insurance);
                for (String str : list) {
                    String temp = request.getParameter(str);
                    if (!temp.isEmpty()) {
                        BeanUtils.setProperty(insurance, "str", temp);
                    }
                }
               return DaoUtil.s_updateInsurancn(insurance);
            } catch (IntrospectionException | InvocationTargetException | IllegalAccessException e) {
                System.out.println("更新保险错误");
                e.printStackTrace();
                return false;
            }
        }else {
            System.out.println("updateServlet开始更新出错");
        }
            return false;

    }
}
