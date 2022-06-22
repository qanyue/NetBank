package com.example.service;

import com.example.model.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.List;

public interface SysServices {

    public int regist_user(UserLogin userLogin);
    public int regist_client(UserInfo userInfo);

    public  UserLogin login(UserLogin userLogin);
    public int openCard(Card card);
    public List<Client_Insurance> InsuranceQuery(String c_id);  //保险查询
    public List<Client_Insurance> InsuranceQueryOne(HashMap<String,Object> map );  //保险查询
    public  int DeleteInsurance(HashMap<String,Object> map); //删除保险

    public List<Client_Products> ProductQuery(String c_id);
    public Client_Products  ProductQueryOne(HashMap<String,Object> map );
    public int DeleteProducts(Client_Products client_products);

    public Fund s_queryFund(String f_id);
    public int s_deleteFund(String f_id);
    public int s_insertFund(Fund fund);

    public Product s_queryProduct(String p_id);
    public int s_deleteProduct(String p_id);
    public int s_insertProduct(Product product);

    public Insurance s_queryInsurance(String i_id);
    public int s_deleteInsurance(String i_id);
    public int s_insertInsurance(Insurance insurance);

}
