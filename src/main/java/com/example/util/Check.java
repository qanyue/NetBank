package com.example.util;

import com.example.DAOUtils.DaoUtil;
import com.example.model.UserLogin;
import com.example.service.UserServices;
import org.apache.ibatis.session.SqlSession;


public class Check {
    
    /**
     * @Param: texts
     * @Description: 检测字符串是否为空
     * @Return: boolean
     * @Date: 2022/5/25 下午8:54
     */
    public static boolean isEmpty(String... texts) {
        for (String text : texts) {
            if (text == null || "".equals(text.trim())) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean checkPassword(UserLogin userLogin){
        SqlSession sqlSession = DaoUtil.getSqlSession();
        UserServices services = sqlSession.getMapper(UserServices.class);
        return false;
    }

}
