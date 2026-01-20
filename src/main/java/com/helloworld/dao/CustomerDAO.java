package com.helloworld.dao;

import com.helloworld.model.StudentModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.helloworld.DatabaseConnection;

public class ustomerDAO {

    // ===== READ ALL =====
    public List<StudentModel> getAllCustomers() throws SQLException {
        List<StudentModel> customers = new ArrayList<>();
        String sql = "SELECT Id, Fullname, Order_Id FROM customers";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                StudentModel c = new StudentModel();
                c.setId(rs.getInt("Id"));
                c.setFullName(rs.getString("Fullname"));
                c.setOrder_id(rs.getInt("Order_Id"));
                customers.add(c);
            }
        }

        return customers;
    }

    // ===== ADD / CREATE =====
    public void addCustomer(StudentModel c) throws SQLException {
        String sql = "INSERT INTO customers (Fullname, Order_Id) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getFullName());
            ps.setInt(2, c.getOrderId());
            ps.executeUpdate();
        }
    }

    // ===== UPDATE =====
    public void updateCustomer(StudentModel c) throws SQLException {
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
    public StudentModel getCustomerById(int id) throws SQLException {
        String sql = "SELECT Id, Fullname, Order_Id FROM customers WHERE Id=?";
        StudentModel c = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    c = new StudentModel();
                    c.setId(rs.getInt("Id"));
                    c.setFullName(rs.getString("Fullname"));
                    c.setOrder_id(rs.getInt("Order_Id"));
                }
            }
        }
        return c;
    }
}
