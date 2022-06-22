package com.example.DAOUtils;

import com.example.model.*;
import com.example.service.SysServices;
import com.example.service.UserServices;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

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
                System.out.println("用户注册 "+"用户"+userInfo.getC_id());
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

//    public static boolean userLogin(UserLogin userLogin) {
//        try (SqlSession sqlSession = DaoUtil.getSqlSession()) {
//            SysServices userServices = sqlSession.getMapper(SysServices.class);
//            UserLogin login= userServices.login(userLogin);
//            if (login != null) {
//                System.out.println("用户" + login.getId() + " 登录成功");
//                return true;
//            }
//            System.out.println("登录失败");
//            return false;
//
//        } catch (Exception exception) {
//            exception.printStackTrace();
//            return false;
//        }
//    }

    public static boolean openCard(Card card) {
        SqlSession sqlSession = DaoUtil.getSqlSession();
        try{
            SysServices services = sqlSession.getMapper(SysServices.class);
            int card_num = services.openCard(card);
            //事务提交
            if(card_num >=1){
                System.out.println("开卡成功" + card_num);
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
                System.out.println(userLogin.getId()+"修改密码成功"+modify);
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
                System.out.println("管理员：" + user.getId() + "登录成功");
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean s_deleteInsurance(String i_id){
        SqlSession sqlSession = DaoUtil.getSqlSession();
        try {
           SysServices services =  sqlSession.getMapper(SysServices.class);
            int delete = services.s_deleteInsurance(i_id);
            sqlSession.commit();
            if(delete >=1){
               System.out.println("删除保险"+i_id);
               return true;
           }
           return false;
        }catch (Exception e ){
            sqlSession.rollback();
            e.printStackTrace();
            return false;
        }finally {
            sqlSession.close();
        }
    }

    public static boolean s_insertInsurance(Insurance insurance){
        SqlSession sqlSession = DaoUtil.getSqlSession();
        try{
            SysServices services = sqlSession.getMapper(SysServices.class);
            int insert = services.s_insertInsurance(insurance);
            sqlSession.commit();
            if(insert >=1 ){
               System.out.println("新建保险成功" + insurance.getI_id());
               return true;
           }
            return false;
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            return false;
        }finally {
            sqlSession.close();
        }
    }
    public static  boolean s_updateInsurancn(Insurance insurance) {
        SqlSession sqlSession = DaoUtil.getSqlSession();
        try {
            SysServices services = sqlSession.getMapper(SysServices.class);
            services.s_deleteInsurance(insurance.getI_id());
            services.s_insertInsurance(insurance);
            sqlSession.commit();
            System.out.println(insurance.getI_id() + "更新成功");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            return false;
        } finally {
            sqlSession.close();
        }
    }

    public static boolean s_deleteFund(String f_id){
        SqlSession sqlSession = DaoUtil.getSqlSession();
        try {
            SysServices services =  sqlSession.getMapper(SysServices.class);
            int delete = services.s_deleteFund(f_id);
            sqlSession.commit();
            if(delete >=1){
                System.out.println("删除基金"+f_id);
                return true;
            }
            return false;
        }catch (Exception e ){
            sqlSession.rollback();
            e.printStackTrace();
            return false;
        }finally {
            sqlSession.close();
        }
    }
        public static boolean s_insertFund(Fund fund){
            SqlSession sqlSession = DaoUtil.getSqlSession();
            try{
                SysServices services = sqlSession.getMapper(SysServices.class);
                int insert = services.s_insertFund(fund);
                sqlSession.commit();
                if(insert >=1 ){
                    System.out.println("新建基金成功" +fund.getF_id());
                    return true;
                }
                return false;
            }catch (Exception e){
                e.printStackTrace();
                sqlSession.rollback();
                return false;
            }finally {
                sqlSession.close();
            }
        }
        public static  boolean s_updateFund(Fund fund){
            SqlSession sqlSession = DaoUtil.getSqlSession();
            try{
                SysServices services = sqlSession.getMapper(SysServices.class);
                services.s_deleteFund(fund.getF_id());
                services.s_insertFund(fund);
                sqlSession.commit();
                System.out.println(fund.getF_id()+ "更新成功");
                return true;
            }catch (Exception e){
                e.printStackTrace();
                sqlSession.rollback();
                return false;
            }finally {
                sqlSession.close();
            }
        }


    public static boolean s_deleteProduct(String p_id){
        SqlSession sqlSession = DaoUtil.getSqlSession();
        try {
            SysServices services =  sqlSession.getMapper(SysServices.class);
            int delete = services.s_deleteFund(p_id);
            sqlSession.commit();
            if(delete>=1){
                System.out.println("删除理财产品"+p_id);
                return true;
            }
            return false;
        }catch (Exception e ){
            sqlSession.rollback();
            e.printStackTrace();
            return false;
        }finally {
            sqlSession.close();
        }
    }
    public static boolean s_insertProduct(Product product){
        SqlSession sqlSession = DaoUtil.getSqlSession();
        try{
            SysServices services = sqlSession.getMapper(SysServices.class);
            int insert = services.s_insertProduct(product);
            sqlSession.commit();
            if(insert >=1 ){
                System.out.println("新建基金成功" +product.getP_id());
                return true;
            }
            return false;
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            return false;
        }finally {
            sqlSession.close();
        }
    }
    public static  boolean s_updateProduct(Product product){
        SqlSession sqlSession = DaoUtil.getSqlSession();
        try{
            SysServices services = sqlSession.getMapper(SysServices.class);
            services.s_deleteProduct(product.getP_id());
            services.s_insertProduct(product);
            sqlSession.commit();
            System.out.println(product.getP_name()+"更新成功");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            return false;
        }finally {
            sqlSession.close();
        }
    }


    public static void  main(String[] args){
        UserLogin userLogin = new UserLogin();
        userLogin.setId("01");
        userLogin.setPassword("12345678");
        DaoUtil.modifyUserPassword(userLogin);
    }
}


