package com.example.servlet;

import java.io.FileReader;
import java.io.Reader;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class CustomServletContextListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent event)
    {
        ServletContext ctx = event.getServletContext();

        String resource = "mybatis-config.xml";
        try{
            //load mybatis configuration
            Reader reader = Resources.getResourceAsReader(resource);
//            Reader reader = new FileReader(ctx.getRealPath("mybatisConfigLocation"));
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            ctx.setAttribute("sqlSessionFactory", sqlSessionFactory);
        }
        catch(Exception e){
            System.out.println("FATAL ERROR: myBatis could not be initialized");
            System.exit(1);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event){

    }
}


