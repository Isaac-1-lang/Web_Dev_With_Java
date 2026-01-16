package com.helloworld.service;

import java.sql.SQLException;
import java.util.List;
import com.helloworld.model.CustomerModel;
import com.helloworld.dao.CustomerDAO;

/**
 * Service layer for Customer CRUD operations
 * Acts as a business logic layer between servlets and DAO
 * 
 * @author Isaac-1-lang
 * @version 1.0
 */
public class CustomerService {
    private CustomerDAO customerDAO;

    public CustomerService() {
        this.customerDAO = new CustomerDAO();
    }

    /**
     * CREATE - Add a new customer
     * @param customer CustomerModel object with customer data
     * @return true if successful, false otherwise
     */
    public boolean addCustomer(CustomerModel customer) {
        try {
            if (customer == null || customer.getFullName() == null || customer.getFullName().trim().isEmpty()) {
                return false;
            }
            customerDAO.addCustomer(customer);
            return true;
        } catch (SQLException e) {
            System.err.println("Error adding customer: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * READ - Get all customers
     * @return List of all customers
     */
    public List<CustomerModel> getAllCustomers() {
        try {
            return customerDAO.getAllCustomers();
        } catch (SQLException e) {
            System.err.println("Error retrieving customers: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * READ - Get customer by ID
     * @param id Customer ID
     * @return CustomerModel if found, null otherwise
     */
    public CustomerModel getCustomerById(int id) {
        try {
            return customerDAO.getCustomerById(id);
        } catch (SQLException e) {
            System.err.println("Error retrieving customer by ID: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * UPDATE - Update an existing customer
     * @param customer CustomerModel object with updated data
     * @return true if successful, false otherwise
     */
    public boolean updateCustomer(CustomerModel customer) {
        try {
            if (customer == null || customer.getId() <= 0) {
                return false;
            }
            customerDAO.updateCustomer(customer);
            return true;
        } catch (SQLException e) {
            System.err.println("Error updating customer: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * DELETE - Delete a customer by ID
     * @param id Customer ID to delete
     * @return true if successful, false otherwise
     */
    public boolean deleteCustomer(int id) {
        try {
            if (id <= 0) {
                return false;
            }
            customerDAO.deleteCustomer(id);
            return true;
        } catch (SQLException e) {
            System.err.println("Error deleting customer: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
