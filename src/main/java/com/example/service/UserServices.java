package  com.example.service;

import com.example.model.*;

import java.util.HashMap;
import java.util.List;

public interface UserServices {

    public  UserLogin login(UserLogin userLogin);
    public int changeUserPassword(UserLogin userLogin);

    public int buyInsurance(Client_Insurance client_insurance); //买保险
    public List<Client_Insurance> InsuranceQuery(String c_id);  //保险查询
    public  int DeleteInsurance(Client_Insurance client_insurance); //删除保险

    public List<Client_Products> ProductQuery(String c_id);
    public int buyProducts(Client_Products client_products);
    public int DeleteProducts(Client_Products client_products);

}
