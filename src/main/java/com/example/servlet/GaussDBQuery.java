package com.example.servlet;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;

import javax.servlet.jsp.JspWriter;
import java.io.*;
import java.sql.*;
import java.util.*;

public class GaussDBQuery {

    private static final String JDBC_DRIVER = "org.opengauss.Driver";
    private static final String DB_URL = "jdbc:opengauss://luoxq.top:5432/finance?ApplicationName=app1";
    // 数据库的用户名与密码，需要根据自己的设置
    private static final String USER = "gaussdb";
    private static final String PASS = "Hohai@123";

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
        JSONObject attributeName = GaussDBQuery.getAttributeName(new File("D:\\AttributDic.json"));
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
            System.out.println("数据库字典未找到");
        }
    }

    public static  void printQueryRadio(ResultSet rs, JspWriter out, String radioValueName,String status) throws SQLException {
        ArrayList<LinkedHashMap<String, Object>> rows = GaussDBQuery.getSelectRestult(rs);
        ArrayList<String> cols_name = new ArrayList<>(rows.get(0).keySet());
        try {
            JSONObject attributeName = GaussDBQuery.getAttributeName(new File("D:\\AttributDic.json"));
            out.println("<style> table, th, td { border:1px solid #b4aaaa;} </style>");
            out.println("选择购买");
            out.println("<table>");
            out.println("<tr>");
            for (String col : cols_name) {
                col = attributeName.getString(col);
                out.print("<th>" + col + "</th>");
            }
            out.println("</tr>");
            for (LinkedHashMap<String, Object> row : rows) {
                if (!((String) row.get(status)).strip().equals("在售")) {
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
            System.out.println("数据库字典未找到");
        }
    }


//    public static  void printQueryRadioI(ResultSet rs, JspWriter out, String radioValueName) throws SQLException {
//        ArrayList<LinkedHashMap<String, Object>> rows = GaussDBQuery.getSelectRestult(rs);
//        ArrayList<String> cols_name = new ArrayList<>(rows.get(0).keySet());
//        try {
//            JSONObject attributeName = GaussDBQuery.getAttributeName(new File("D:\\AttributDic.json"));
//            out.println("<style> table, th, td { border:1px solid black;} </style>");
//            out.println("选择购买");
//            out.println("<table>");
//            out.println("<tr>");
//            for (String col : cols_name) {
//                col = attributeName.getString(col);
//                out.print("<th>" + col + "</th>");
//            }
//            out.println("</tr>");
//            for (LinkedHashMap<String, Object> row : rows) {
//                if (!((String) row.get("i_status")).strip().equals("在售")) {
//                    continue;
//                }
//                out.println("<tr>");
//                for (String filed : row.keySet()) {
//                    out.print("<th>" + row.get(filed) + "</th>");
//                }
//                out.println("<th> <input type=\"radio\" name=\"radioSelect\" value=\""+
//                        ((String) row.get(radioValueName)).strip()+"\"> </th>");
//                out.println("</tr>");
//            }
//            out.println("</table>");
//            out.println("<br>");
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("数据库字典未找到");
//        }
//    }
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

    public static String sqlhandle(String sql,ArrayList<String> infos,ArrayList<String> cols,String ws){
        if(ws.equals("set")){
            boolean flag = false;
            for(int i = 0 ; i< cols.size() ; i++){
                if(cols.get(i).equals("c_id")){
                    continue;
                }
                if(!infos.get(i).isEmpty() && !flag){
                    sql = sql + " " + ws + " " + cols.get(i) + "='" + infos.get(i) + "'";
                    flag = true;
                }else if(!infos.get(i).isEmpty() && flag){
                    sql = sql + " , " + cols.get(i) + "='" + infos.get(i) + "'";
                }
            }
        }else{
            boolean flag = false;
            for(int i = 0 ; i< cols.size() ; i++){
                if(!infos.get(i).isEmpty() && !flag){
                    sql = sql + " " + ws + " " + cols.get(i) + "='" + infos.get(i) + "'";
                    flag = true;
                }else if(!infos.get(i).isEmpty() && flag){
                    sql = sql + " and " + cols.get(i) + "='" + infos.get(i) + "'";
                }
            }
            sql = sql + ";";
        }



        return sql;
    }

//    public static  void printQueryRadioP(ResultSet rs, JspWriter out, String radioValueName) throws SQLException {
//        ArrayList<LinkedHashMap<String, Object>> rows = GaussDBQuery.getSelectRestult(rs);
//        ArrayList<String> cols_name = new ArrayList<>(rows.get(0).keySet());
//        try {
//            JSONObject attributeName = GaussDBQuery.getAttributeName(new File("D:/AttributDic.json"));
//            out.println("<style> table, th, td { border:1px solid black;} </style>");
//            out.println("选择购买");
//            out.println("<table>");
//            out.println("<tr>");
//            for (String col : cols_name) {
//                col = attributeName.getString(col);
//                out.print("<th>" + col + "</th>");
//            }
//            out.println("</tr>");
//            for (LinkedHashMap<String, Object> row : rows) {
//                if (!((String) row.get("p_status")).strip().equals("在售")) {
//                    continue;
//                }
//                out.println("<tr>");
//                for (String filed : row.keySet()) {
//                    out.print("<th>" + row.get(filed) + "</th>");
//                }
//                out.println("<th> <input type=\"radio\" name=\"radioSelect\" value=\""+
//                        ((String) row.get(radioValueName)).strip()+"\"> </th>");
//                out.println("</tr>");
//            }
//            out.println("</table>");
//            out.println("<br>");
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("数据库字典未找到");
//        }
//    }

}
