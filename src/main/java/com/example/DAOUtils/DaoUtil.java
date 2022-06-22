package com.example.DAOUtils;

import com.example.model.Card;
import com.example.model.Client_Insurance;
import com.example.model.UserInfo;
import com.example.model.UserLogin;
import com.example.service.SysServices;
import com.example.service.UserServices;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class DaoUtil {
    static String resource = "mybatis-config.xml";
    static InputStream inputStream;

    static {
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }

    public static boolean regist(UserInfo userInfo, UserLogin userLogin) {
        SqlSession sqlSession = DaoUtil.getSqlSession();
        try {

            SysServices services = sqlSession.getMapper(SysServices.class);
           int user = services.regist_user(userLogin);
           int client = services.regist_client(userInfo);
            sqlSession.commit();
            if(user >=1 && client >=1){
                System.out.println("用户表："+user + "用户信息表:"+client);
                return true;
            }
            return false;
        } catch (Exception exception) {
            //异常回滚
            sqlSession.rollback();
            return false;
        } finally {
            sqlSession.close();
        }

    }

    public static boolean userLogin(UserLogin userLogin) {
        try (SqlSession sqlSession = DaoUtil.getSqlSession()) {
            UserServices userServices = sqlSession.getMapper(UserServices.class);
            UserLogin login= userServices.login(userLogin);
            if (login != null) {
                System.out.println("用户" + login.getId() + " 登录成功");
                return true;
            }
            System.out.println("登录失败");
            return false;

        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public static boolean openCard(Card card) {
        SqlSession sqlSession = DaoUtil.getSqlSession();
        try{
            SysServices services = sqlSession.getMapper(SysServices.class);
            int card_num = services.openCard(card);
            //事务提交
            if(card_num >=1){
                System.out.println("开卡数量" + card_num);
                return true;
            }
            sqlSession.commit();
            return false;

        } catch (Exception exception) {
            //异常回滚
            sqlSession.rollback();
            return false;
        } finally {
            sqlSession.close();
        }
    }

    public static boolean modifyUserPassword(UserLogin userLogin) {
        SqlSession sqlSession = DaoUtil.getSqlSession();
        try {
            UserServices services = sqlSession.getMapper(UserServices.class);
            int modify = services.changeUserPassword(userLogin);
            //事务提交
            sqlSession.commit();
            if(modify >=1){
                System.out.println("修改密码成功"+modify);
                return true;
            }

            return false;
        } catch (Exception exception) {
            sqlSession.rollback();
            exception.printStackTrace();
            return false;
        } finally {
            sqlSession.close();
        }
    }

    public  static  boolean Syslogin(UserLogin userLogin){
        try (SqlSession sqlSession = DaoUtil.getSqlSession()) {
            SysServices services = sqlSession.getMapper(SysServices.class);
            UserLogin user = services.login(userLogin);
            if (user != null) {
                System.out.println("用户：" + user.getId() + "登录成功");
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean deleteInsurance(Client_Insurance client_insurance){
        SqlSession sqlSession = DaoUtil.getSqlSession();
        try {
           UserServices services =  sqlSession.getMapper(UserServices.class);
            return false;
        }catch (Exception e ){
            sqlSession.rollback();
            e.printStackTrace();
            return false;
        }finally {
            sqlSession.close();
        }

    }
    public static void  main(String[] args){
        UserLogin userLogin = new UserLogin();
        userLogin.setId("01");
        userLogin.setPassword("123456");
        DaoUtil.userLogin(userLogin);
    }
}


