package com.example.service;

import com.example.model.Card;
import com.example.model.UserInfo;
import com.example.model.UserLogin;

public interface SysServices {

    public int regist_user(UserLogin userLogin);
    public int regist_client(UserInfo userInfo);

    public  UserLogin login(UserLogin userLogin);
    public int openCard(Card card);
}
