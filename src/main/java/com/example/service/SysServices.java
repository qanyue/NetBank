package com.example.service;

import com.example.model.*;
import org.apache.ibatis.annotations.*;

import java.util.HashMap;
import java.util.List;

public interface SysServices {

    @Insert("insert into finance.users values (#{id},#{password})"
    )
    public int regist_user(UserLogin userLogin);

    @Insert("insert into finance.client values(#{c_id},#{c_name},#{c_mail},#{c_id_card},#{c_phone},#{c_status})\n")
    public int regist_client(UserInfo userInfo);

    @Results(id = "UserMap", value = {
            @Result(property = "id", column = "c_id", id = true),
            @Result(property = "password", column = "c_password")

    })
    @Select("select * from finance.adminsys where s_id=#{id} and s_password=#{password}")
    public UserLogin login(UserLogin userLogin);

    @Insert(" INSERT INTO finance.card(ca_id,c_id,ca_password,ca_type,ca_deposit,ca_status) " +
            " VALUES (#{ca_id},#{c_id},#{capassword},#{ca_type},#{ca_deposit},#{ca_status})")
    public int openCard(Card card);

    @Select("        select * " +
            "        from finance.client_insurance where c_id=#{id};")
    public List<Client_Insurance> InsuranceQuery(String c_id);  //保险查询

    @Select("        select * from finance.client_insurance where c_id=#{c_id,jdbcType=VARCHAR} and i_id = #{i_id,jdbcType=VARCHAR}")
    public List<Client_Insurance> InsuranceQueryOne(HashMap<String, Object> map);  //保险查询

    @Delete("        DELETE  FROM  finance.client_insurance where c_id=#{c_id,jdbcType=VARCHAR} and i_id = #{i_id,jdbcType=VARCHAR}")
    public int DeleteInsurance(HashMap<String, Object> map); //删除保险

    @Select("        select * " +
            "        from finance.client_products where c_id=#{id};")
    public List<Client_Products> ProductQuery(String c_id);

    @Select("        select * from finance.client_products where c_id=#{c_id,jdbcType=VARCHAR} and p_id= #{p_id,jdbcType=VARCHAR}")
    public Client_Products ProductQueryOne(HashMap<String, Object> map);

    @Delete("        DELETE  FROM  finance.client_products where c_id=#{c_id,jdbcType=VARCHAR} and p_id = #{c_id,jdbcType=VARCHAR}")
    public int DeleteProducts(Client_Products client_products);

    @Select("select * from finance.fund where  f_id = #{f_id}")
    public Fund s_queryFund(String f_id);

    @Delete("        DELETE from finance.fund where f_id = #{f_id}")
    public int s_deleteFund(String f_id);

    @Insert("        insert into finance.fund values (#{f_id},#{f_name},#{f_type},#{f_start},#{f_end},#{f_risk},#{f_manager},#{f_remain},#{f_status})")
    public int s_insertFund(Fund fund);

    @Select("select * from finance.products where  p_id = #{p_id}")
    public Product s_queryProduct(String p_id);

    @Delete("DELETE  from finance.fund where f_id=#{f_id}")
    public int s_deleteProduct(String p_id);

    @Insert(" INSERT INTO finance.products(p_id,p_name,p_start,p_end,p_remain,p_describe,p_status) VALUES " +
            "            (#{p_id},#{p_name},#{p_start},#{p_end},#{p_remain},#{p_describe},#{p_status})")
    public int s_insertProduct(Product product);

    @Select("        select * from finance.insurance where  i_id = #{i_id}")

    public Insurance s_queryInsurance(String i_id);

    @Delete("DELETE  from finance.insurance where i_id=#{i_id}")
    public int s_deleteInsurance(String i_id);

    @Insert("        INSERT INTO finance.insurance (i_id,i_name,i_project,i_start,i_end,i_applicable,i_status)  VALUES " +
            "     (#{i_id},#{i_name},#{i_project},#{i_start},#{i_end},#{i_applicable},#{i_status})")
    public int s_insertInsurance(Insurance insurance);

}
