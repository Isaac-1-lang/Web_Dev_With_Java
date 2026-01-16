package com.helloworld.service;


import java.sql.PreparedStatement;
import java.sql.Connection;
import com.helloworld.DatabaseConnection;


import com.helloworld.model.CustomerModel;

public class CustomerService {
  public void addCustomer(CustomerModel customer) {
    String sql = "INSERT INTO customer(fullname,order_id) VALUES (?,?)";
    try(Connection conn = DatabaseConnection.getConnection();PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(0,customer.getFullName());
        pstmt.setInt(0,customer.getOrderId());

        pstmt.executeUpdate();
        System.out.println("Customer added successfully");
    }catch(Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
