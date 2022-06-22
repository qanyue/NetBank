package com.example.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description:
 * @time: 2021/12/24 15:41
 */

public class CheckUtil {
    public static boolean checkDate(String date) {
        if (date == null) {
            return false;
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false);
            Date userDate = dateFormat.parse(date);


            Date now = new Date();
            DateFormat df1 = DateFormat.getDateInstance();//日期格式，精确到日
            String nowDateString = df1.format(now);
            SimpleDateFormat dateFormats = new SimpleDateFormat("yyyy-MM-dd");
            Date nowDate = dateFormats.parse(nowDateString);
            if(userDate.after(nowDate)){
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getNowDateString() {
        Date now = new Date();
        DateFormat df1 = DateFormat.getDateInstance();//日期格式，精确到日
        return df1.format(now);
    }
}
