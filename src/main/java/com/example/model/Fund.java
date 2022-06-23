package com.example.model;

import com.example.DAOUtils.DaoUtil;

import java.util.Date;

public class Fund {
  public String getF_id() {
    return f_id;
  }

  public void setF_id(String f_id) {
    this.f_id = f_id;
  }

  public String getF_name() {
    return f_name;
  }

  public void setF_name(String f_name) {
    this.f_name = f_name;
  }

  public String getF_type() {
    return f_type;
  }

  public void setF_type(String f_type) {
    this.f_type = f_type;
  }


  public String getF_start() {
    return f_start;
  }

  public String getF_end() {
    return f_end;
  }


  public String getF_risk() {
    return f_risk;
  }

  public void setF_risk(String f_risk) {
    this.f_risk = f_risk;
  }

  public String getF_manager() {
    return f_manager;
  }

  public void setF_manager(String f_manager) {
    this.f_manager = f_manager;
  }

  public Double getF_remain() {
    return f_remain;
  }

  public void setF_remain(Double f_remain) {
    this.f_remain = f_remain;
  }

  public String getF_status() {
    return f_status;
  }

  public void setF_status(String f_status) {
    this.f_status = f_status;
  }

  private String  f_id ;
  private String  f_name ;
  private String  f_type ;
  private String f_start ;
  private String f_end ;
  private String  f_risk ;
  private String  f_manager ;
  private Double  f_remain ;
  private String  f_status ;
}
