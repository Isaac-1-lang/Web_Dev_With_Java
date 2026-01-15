package com.helloworld.dao;

import com.helloworld.model.CustomerModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.helloworld.DatabaseConnection;

public class CustomerDAO {

    // ===== READ ALL =====
    public List<CustomerModel> getAllCustomers() throws SQLException {
        List<CustomerModel> customers = new ArrayList<>();
        String sql = "SELECT Id, Fullname, Order_Id FROM customers";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                CustomerModel c = new CustomerModel();
                c.setId(rs.getInt("Id"));
                c.setFullName(rs.getString("Fullname"));
                c.setOrder_id(rs.getInt("Order_Id"));
                customers.add(c);
            }
        }

        return customers;
    }

    // ===== ADD / CREATE =====
    public void addCustomer(CustomerModel c) throws SQLException {
        String sql = "INSERT INTO customers (Fullname, Order_Id) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getFullName());
            ps.setInt(2, c.getOrderId());
            ps.executeUpdate();
        }
    }

    // ===== UPDATE =====
    public void updateCustomer(CustomerModel c) throws SQLException {
        String sql = "UPDATE customers SET Fullname=?, Order_Id=? WHERE Id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getFullName());
            ps.setInt(2, c.getOrderId());
            ps.setInt(3, c.getId());
            ps.executeUpdate();
        }
    }

    // ===== DELETE =====
    public void deleteCustomer(int id) throws SQLException {
        String sql = "DELETE FROM customers WHERE Id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    // ===== READ BY ID =====
    public CustomerModel getCustomerById(int id) throws SQLException {
        String sql = "SELECT Id, Fullname, Order_Id FROM customers WHERE Id=?";
        CustomerModel c = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    c = new CustomerModel();
                    c.setId(rs.getInt("Id"));
                    c.setFullName(rs.getString("Fullname"));
                    c.setOrder_id(rs.getInt("Order_Id"));
                }
            }
        }
        return c;
    }
}
