package  com.example.service;

import com.example.model.*;

import java.util.HashMap;
import java.util.List;

public interface UserServices {

    public  UserLogin login(UserLogin userLogin);
    public int buyInsurance(Client_Insurance client_insurance);
    public int buyProducts(Client_Products client_products);
    public int changeUserPassword(UserLogin userLogin);

}
