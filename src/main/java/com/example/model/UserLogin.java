package com.example.model;

public class UserLogin {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String id;

    public UserLogin(String id, String password) {
        this.id = id;
        this.password = password;
    }
    public UserLogin(){

    }
    private String password;
}
