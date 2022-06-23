package com.example.servlet;

import com.example.DAOUtils.DaoUtil;
import com.example.model.UserLogin;

import com.example.service.UserServices;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

@WebServlet(name = "passwordchange", value = "/passwordchange")
public class passwordchange extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        out.println("<meta http-equiv=\"refresh\" content=\"2;URL=Login.jsp\">");
        HttpSession session = request.getSession();
        String username = session.getAttribute("c_id").toString();
        String password = request.getParameter("newpwd").strip();
        UserLogin user = new UserLogin(username,password);
        if(DaoUtil.modifyUserPassword(user)){
          out.println("<h1>修改密码成功");
      }else {
            out.println("<h1>修改密码出错");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
