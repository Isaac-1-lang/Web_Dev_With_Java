package com.helloworld;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

import com.helloworld.model.StudentModel;

/**
 * Servlet for editing customer information
 * Handles both GET (display edit form) and POST (update customer) requests
 * 
 * @author Isaac-1-lang
 * @version 1.0
 */
public class EditStudentServlet extends HttpServlet {

    /**
     * Display edit form with customer data
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Check session
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("user") == null){
            response.sendRedirect("login");
            return;
        }
        
        // Get customer ID from parameter
        String idStr = request.getParameter("id");
        
        if (idStr == null || idStr.trim().isEmpty()) {
            request.setAttribute("error", "Customer ID is required");
            response.sendRedirect("dashboard");
            return;
        }
        
        try {
            int id = Integer.parseInt(idStr);
            
            // Use service to get customer by ID
            StudentService customerService = new StudentService();
            StudentModel customer = customerService.getCustomerById(id);
            
            if (customer == null) {
                request.setAttribute("error", "Customer not found");
                response.sendRedirect("dashboard");
                return;
            }
            
            // Set customer data for edit form
            request.setAttribute("customer", customer);
            request.getRequestDispatcher("/edit-student.jsp").forward(request, response);
            
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid customer ID format");
            response.sendRedirect("dashboard");
        }
    }

    /**
     * Handle customer update
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Check session
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("user") == null){
            response.sendRedirect("login");
            return;
        }
        
        // Get form parameters
        String idStr = request.getParameter("id");
        String fullName = request.getParameter("fullName");
        String orderIdStr = request.getParameter("orderId");
        
        // Validate input
        if (idStr == null || fullName == null || fullName.trim().isEmpty() || orderIdStr == null || orderIdStr.trim().isEmpty()) {
            request.setAttribute("error", "All fields are required");
            doGet(request, response);
            return;
        }
        
        try {
            int id = Integer.parseInt(idStr);
            int orderId = Integer.parseInt(orderIdStr);
            
            // Create customer model with updated data
            StudentModel customer = new StudentModel();
            customer.setId(id);
            customer.setFullName(fullName.trim());
            customer.setOrder_id(orderId);
            
            // Use service to update customer
            StudentService customerService = new StudentService();
            boolean success = customerService.updateCustomer(customer);
            
            if (success) {
                request.setAttribute("success", "Customer updated successfully");
                response.sendRedirect("dashboard");
            } else {
                request.setAttribute("error", "Failed to update customer");
                doGet(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid ID or Order ID format");
            doGet(request, response);
        }
    }
}
