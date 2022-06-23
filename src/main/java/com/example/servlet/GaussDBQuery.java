package com.example.servlet;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;

import javax.servlet.jsp.JspWriter;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;

public class GaussDBQuery {

    private static final String JDBC_DRIVER = "org.opengauss.Driver";
    private static final String DB_URL = "jdbc:opengauss://luoxq.top:5432/finance?ApplicationName=app1";
    private  static final String jsonFile = "AttributDic.json";
    // 数据库的用户名与密码，需要根据自己的设置
    private static final String USER = "gaussdb";
    private static final String PASS = "Hohai@123";

    public static  String jsonPath() {
        String path = Objects.requireNonNull(GaussDBQuery.class.getClassLoader().getResource(jsonFile)).toString();
        path = path.replace("\\", "/");
        if (path.contains(":")) {
            path = path.replace("file:/", "");
        }
        System.out.println(path);
        return path;
    }
    public static ArrayList<LinkedHashMap<String,Object>> getSelectRestult(ResultSet rs) throws SQLException {
        ResultSetMetaData  MeteData = rs.getMetaData();
        int ColumCount = MeteData.getColumnCount();
        //out.println("Websites tanle col_num is "+ ColumCount);
        ArrayList<String> cols_name= new ArrayList<String>();
        for(int i=1;i<=ColumCount;i++){
            cols_name.add(MeteData.getColumnName(i));
        }
        ArrayList<LinkedHashMap<String,Object>> rows = new ArrayList<>();
        rs.beforeFirst();
        while (rs.next()) {
            LinkedHashMap<String,Object> row = new LinkedHashMap<>();
            for (String colName:cols_name) {
                Object val = rs.getObject(colName);
                row.put(colName,val);
            }
            rows.add(row);
        }
        return rows;
    }

    public static  void printQueryResult(ResultSet rs, PrintWriter out) throws SQLException {
        ArrayList<LinkedHashMap<String, Object>> rows = GaussDBQuery.getSelectRestult(rs);
        ArrayList<String> cols_name = new ArrayList<>(rows.get(0).keySet());

        try {
        JSONObject attributeName = GaussDBQuery.getAttributeName(new File(GaussDBQuery.jsonPath()));

        out.println("<style> table, th, td { border:1px solid black;} </style>");
        out.println("<table>");
        out.println("<tr>");
        for(String col:cols_name){
            col = attributeName.getString(col);
//            System.out.println(col);
            out.print("<th>"+col+ "</th>");
        }
        out.println("</tr>");
        for(LinkedHashMap<String,Object> row: rows){

            out.println("<tr>");
            for(String filed:row.keySet()){
                out.print("<th>"+row.get(filed)+"</th>");
            }
            out.println("</tr>");
        }
        out.println("</table>");
        out.println("<br>");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("数据库字典未找到");
        }
    }
    public static  void printQueryRadio(ResultSet rs, JspWriter out, String radioValueName) throws SQLException {
        ArrayList<LinkedHashMap<String, Object>> rows = GaussDBQuery.getSelectRestult(rs);
        ArrayList<String> cols_name = new ArrayList<>(rows.get(0).keySet());
        try {
            JSONObject attributeName = GaussDBQuery.getAttributeName(new File(GaussDBQuery.jsonPath()));
            out.println("<style> table, th, td { border:1px solid black;} </style>");
            out.println("选择购买");
            out.println("<table>");
            out.println("<tr>");
            for (String col : cols_name) {
                col = attributeName.getString(col);
                out.print("<th>" + col + "</th>");
            }
            out.println("</tr>");
            for (LinkedHashMap<String, Object> row : rows) {
                if (!((String) row.get("f_status")).strip().equals("在售")) {
                    continue;
                }
                out.println("<tr>");
                for (String filed : row.keySet()) {
                    out.print("<th>" + row.get(filed) + "</th>");
                }
                out.println("<th> <input type=\"radio\" name=\"radioSelect\" value=\""+
                        ((String) row.get(radioValueName)).strip()+"\"> </th>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("<br>");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("数据库字典未找到");
        }
    }
        public static Connection getConnetion() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement stmt = null;
        System.out.println("连接数据库...");
        // 注册 JDBC 驱动
        Class.forName(JDBC_DRIVER);
        // 打开链接
        System.out.println(" 建立连接...");
        conn = DriverManager.getConnection(DB_URL,USER,PASS);
        return conn;

    }
    public static JSONObject getAttributeName(File file) throws IOException {
        String dict = FileUtils.readFileToString(file, "UTF-8");
        JSONObject obj =  JSONObject.parseObject(dict);
         return  JSONObject.parseObject(dict);
    }
    public static LinkedHashMap<String, Object> beanToMap(Object object) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Field[] fields = object.getClass().getDeclaredFields();
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();

        for (Field field:fields) {
            field.setAccessible(true);
            String key = field.getName();
            String method = key.substring(0,1).toUpperCase()+key.substring(1);
            Method m = object.getClass().getDeclaredMethod("get"+method);
            Object value = m.invoke(object);
            map.put(key,value);
        }
        return map;
    }
    public static  void PrintChangeTable(LinkedHashMap<String,Object> field,PrintWriter out,String id){
        ArrayList<String> cols = new ArrayList<>(field.keySet());

        try {

            JSONObject attributeName = GaussDBQuery.getAttributeName(new File(GaussDBQuery.jsonPath()));

            out.println("<style> table, th, td { border:1px solid black;} </style>");
            out.println("<table>");
            out.println("<tr>");
            out.println("<th> 属性名 </th>");
            out.println("<th> 原值 </th>");
            out.println("<th> 新值 </th>");
            out.println("</tr>");
            for(String col:cols){
                String col_name = attributeName.getString(col);
                out.println("<tr>");
                out.println("<th>"+col_name+ "</th>");
                out.println("<th>"+field.get(col)+"</th>");
                out.println("<th> <input type=\"text\" name=\""+col+"\"> </th>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("<br>");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("数据库字典未找到");
        }
    }
    public static  void PrintItemTable(ArrayList<LinkedHashMap<String,Object>> field,PrintWriter out,String id){
        ArrayList<String> rows_name = new ArrayList<>(field.get(0).keySet());
        try {

            JSONObject attributeName = GaussDBQuery.getAttributeName(new File(GaussDBQuery.jsonPath()));
            out.println("<style> table, th, td { border:1px solid black;} </style>");
            out.println("选择要修改的产品:");
            out.println("<table>");
            out.println("<tr>");
            for (String row : rows_name) {
                row = attributeName.getString(row);
                out.print("<th>" + row + "</th>");
            }
            out.println("</tr>");
            for (LinkedHashMap<String, Object> item: field) {
                out.println("<tr>");
                for (String filed : item.keySet()) {
                    out.print("<th>" + item.get(filed) + "</th>");
                }
                out.println("<th> <input type=\"radio\" name=\"radioSelect\" value=\""+
                        ((String) item.get(id)).strip()+"\"> </th>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("<br>");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("数据库字典未找到");
        }
    }

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            System.out.println("连接数据库...");
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
            // 打开链接
            System.out.println(" 建立连接...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            ResultSet rs=stmt.executeQuery("select * from finance.fund;");
            PrintWriter out = new PrintWriter(System.out);
//            GaussDBQuery.printQueryRadio(rs,System.out,"f_id");
            out.println("");

        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}
