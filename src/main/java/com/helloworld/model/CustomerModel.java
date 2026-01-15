package com.helloworld.model;

public class CustomerModel {
  private int id;
  private String fullName;
  private int Order_Id;


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
    return Order_Id;
  }
  public void setOrder_id(int order_id) {
    this.Order_Id=order_id;
  }
 }
