package com.example.model;

import java.util.Date;

public class Client_Insurance {
    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public String getI_id() {
        return i_id;
    }

    public void setI_id(String i_id) {
        this.i_id = i_id;
    }

    public int getCi_number() {
        return ci_number;
    }

    public void setCi_number(int ci_number) {
        this.ci_number = ci_number;
    }

    public String getCi_status() {
        return ci_status;
    }

    public void setCi_status(String ci_status) {
        this.ci_status = ci_status;
    }

    public double getCi_income() {
        return ci_income;
    }

    public void setCi_income(double ci_income) {
        this.ci_income = ci_income;
    }

    public Date getCi_time() {
        return ci_time;
    }

    public void setCi_time(Date ci_time) {
        this.ci_time = ci_time;
    }

    public double getCi_sum() {
        return ci_sum;
    }

    public void setCi_sum(double ci_sum) {
        this.ci_sum = ci_sum;
    }

    public int getCi_year() {
        return ci_year;
    }

    public void setCi_year(int ci_year) {
        this.ci_year = ci_year;
    }

    private String c_id ;
    private String i_id ;
    private int  ci_number ;
    private String ci_status;
    private double ci_income;
    private Date ci_time ;
    private double ci_sum ;
    private int ci_year;
}
