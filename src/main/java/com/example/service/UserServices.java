package com.example.service;

import com.example.model.*;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserServices {

    @Results(id = "UserMap", value = {
            @Result(property = "id", column = "c_id", id = true),
            @Result(property = "password", column = "c_password")

    })
    @Select("select * from finance.users where c_id=#{id} and c_password=#{password}")
    public UserLogin login(UserLogin userLogin);

    //    public int changeUserPassword(UserLogin userLogin);
    @Update("update finance.users set c_password = #{password} where c_id = #{id}")
    public int Password(UserLogin userLogin);

    public int buyInsurance(Client_Insurance client_insurance); //买保险

    public List<Client_Insurance> InsuranceQuery(String c_id);  //保险查询

    public int DeleteInsurance(Client_Insurance client_insurance); //删除保险

    public List<Client_Products> ProductQuery(String c_id);

    public int buyProducts(Client_Products client_products);

    public int DeleteProducts(Client_Products client_products);

}
