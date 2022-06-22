package com.example.model;

import java.util.Date;

public class Client_Products {
    private String c_id ;

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public int getCp_number() {
        return cp_number;
    }

    public void setCp_number(int cp_number) {
        this.cp_number = cp_number;
    }

    public String getCp_status() {
        return cp_status;
    }

    public void setCp_status(String cp_status) {
        this.cp_status = cp_status;
    }

    public double getCp_income() {
        return cp_income;
    }

    public void setCp_income(double cp_income) {
        this.cp_income = cp_income;
    }

    public Date getCp_time() {
        return cp_time;
    }

    public void setCp_time(Date cp_time) {
        this.cp_time = cp_time;
    }

    public double getCp_sum() {
        return cp_sum;
    }

    public void setCp_sum(double cp_sum) {
        this.cp_sum = cp_sum;
    }

    private String p_id ;
    private int  cp_number ;
    private String cp_status;
    private double cp_income;
    private Date cp_time ;
    private double cp_sum ;
}
