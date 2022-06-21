package com.example.model;

public class Card {
    private String ca_id;

    public String getCa_id() {
        return ca_id;
    }

    public void setCa_id(String ca_id) {
        this.ca_id = ca_id;
    }

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public String getCa_password() {
        return ca_password;
    }

    public void setCa_password(String ca_password) {
        this.ca_password = ca_password;
    }

    public String getCa_type() {
        return ca_type;
    }

    public void setCa_type(String ca_type) {
        this.ca_type = ca_type;
    }

    public Double getCa_deposit() {
        return ca_deposit;
    }

    public void setCa_deposit(Double ca_deposit) {
        this.ca_deposit = ca_deposit;
    }

    public String getCa_status() {
        return ca_status;
    }

    public void setCa_status(String ca_status) {
        this.ca_status = ca_status;
    }

    private String c_id;
    private String ca_password;
    private String ca_type;
    private Double ca_deposit;
    private String ca_status;
}
