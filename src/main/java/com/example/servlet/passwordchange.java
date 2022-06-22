package com.example.servlet;

import com.example.DAOUtils.DaoUtil;
import com.example.model.UserLogin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "passwordchange", value = "/passwordchange")
public class passwordchange extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        String password = request.getParameter("password");
        String confirm = request.getParameter("confirm");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("c_id");
        if(!confirm.equals(password)){
            out.println("<h1>两次密码不一致，请重试");
        }
        UserLogin user = new UserLogin();
        user.setId(username);
        user.setPassword(password);
        DaoUtil.modifyUserPassword(user);
            out.println("<h1>修改密码成功");
            response.sendRedirect("Login.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
