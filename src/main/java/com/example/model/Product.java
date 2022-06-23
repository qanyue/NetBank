package com.example.model;

import com.example.DAOUtils.DaoUtil;

import java.util.Date;

public class Product {
    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }




    public double getP_remain() {
        return p_remain;
    }

    public String getP_start() {
        return p_start;
    }

    public void setP_start(String p_start) {
        this.p_start = p_start;
    }

    public String getP_end() {
        return p_end;
    }

    public void setP_end(String p_end) {
        this.p_end = p_end;
    }

    public void setP_remain(double p_remain) {
        this.p_remain = p_remain;
    }


    public String getP_describe() {
        return p_describe;
    }

    public void setP_describe(String p_describe) {
        this.p_describe = p_describe;
    }

    public String getP_status() {
        return p_status;
    }

    public void setP_status(String p_status) {
        this.p_status = p_status;
    }

    private String p_id;
    private String p_name;
    private String p_start;
    private String p_end;
    private double p_remain;
    private String p_describe;
    private String p_status;
}
