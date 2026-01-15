package com.helloworld.model;

public class CustomerModel {
  private int id;
  private String fullName;
  private int orderId;


  public int  getId() {
    return id;
  }
  public void setId(int id) {
    this.id=id;
  } 


  public String getFullName() {
    return fullName;
  }
  public void setFullName(String fullName) {
    this.fullName=fullName;
  }
  public int getOrderId() {
    return orderId;
  }
  public void setOrder_id(int order_id) {
    this.orderId=order_id;
  }
 }
