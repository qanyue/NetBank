package com.example.model;

import java.util.Date;

public class Insurance {
    private String i_id;
    private String i_name;

    public String getI_id() {
        return i_id;
    }

    public void setI_id(String i_id) {
        this.i_id = i_id;
    }

    public String getI_name() {
        return i_name;
    }

    public void setI_name(String i_name) {
        this.i_name = i_name;
    }

    public String getI_project() {
        return i_project;
    }

    public void setI_project(String i_project) {
        this.i_project = i_project;
    }

    public Date getI_start() {
        return i_start;
    }

    public void setI_start(Date i_start) {
        this.i_start = i_start;
    }

    public Date getI_end() {
        return i_end;
    }

    public void setI_end(Date i_end) {
        this.i_end = i_end;
    }

    public String getI_applicable() {
        return i_applicable;
    }

    public void setI_applicable(String i_applicable) {
        this.i_applicable = i_applicable;
    }

    public String getI_status() {
        return i_status;
    }

    public void setI_status(String i_status) {
        this.i_status = i_status;
    }

    private String i_project;
    private Date i_start;
    private Date i_end;
    private String i_applicable;
    private String i_status;
}
