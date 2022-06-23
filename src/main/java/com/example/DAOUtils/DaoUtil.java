package com.example.DAOUtils;

import com.example.model.*;
import com.example.service.SysServices;
import com.example.service.UserServices;
import com.example.servlet.UpdateServlet;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;

public class DaoUtil {
    public  static String resource = "mybatis-config.xml";
    public  static InputStream inputStream;

    static {
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

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
            int modify=services.Password(userLogin);
            //事务提交
            sqlSession.commit();
            if(modify >= 1){
                System.out.println(userLogin.getId()+"修改密码成功"+modify);
                return true;
            }else {
                System.out.println("修改米麦失败");
                return false;
            }
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
    public static String checkDate(String str){
        String format1 = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
            java.util.Date date= sdf.parse(str);
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            format1 = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return format1;
    }
    public static Date strToData(String str){
        Date format1 = null;
        try {
            SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date date= sdf.parse(str);
//            sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
//            format1 = sdf.format(date);
            return format1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static ArrayList<String> getbeanInfo(Object object) throws IntrospectionException {
        ArrayList<String> list = new ArrayList<>();
        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field:fields) {
            field.setAccessible(true);
            String key = field.getName();
            list.add(key);
        }
        System.out.println(list.toString());
        return list;
    }

    public  static void main(String[] args){
        UserLogin user = new UserLogin("11","1234567");
        DaoUtil.modifyUserPassword(user);
        System.out.println(checkDate("Tue Aug 18 00:00:00 CST 2020"));
    }

}


