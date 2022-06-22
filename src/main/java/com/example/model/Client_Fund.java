package com.example.model;

import java.util.Date;

public class Client_Fund {
    private String c_id ;

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public String getF_id() {
        return f_id;
    }

    public void setF_id(String f_id) {
        this.f_id = f_id;
    }

    public int getCf_number() {
        return cf_number;
    }

    public void setCf_number(int cf_number) {
        this.cf_number = cf_number;
    }

    public String getCf_status() {
        return cf_status;
    }

    public void setCf_status(String cf_status) {
        this.cf_status = cf_status;
    }

    public double getCf_income() {
        return cf_income;
    }

    public void setCf_income(double cf_income) {
        this.cf_income = cf_income;
    }

    public Date getCf_time() {
        return cf_time;
    }

    public void setCf_time(Date cf_time) {
        this.cf_time = cf_time;
    }

    public double getCf_sum() {
        return cf_sum;
    }

    public void setCf_sum(double cf_sum) {
        this.cf_sum = cf_sum;
    }

    public int getCf_year() {
        return cf_year;
    }

    public void setCf_year(int cf_year) {
        this.cf_year = cf_year;
    }

    private String f_id ;
    private int  cf_number ;
    private String cf_status;
    private double cf_income;
    private Date cf_time ;
    private double cf_sum ;
    private  int cf_year;
}
