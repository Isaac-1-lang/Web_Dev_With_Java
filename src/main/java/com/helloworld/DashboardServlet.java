package com.helloworld;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

import com.helloworld.model.CustomerModel;
import com.helloworld.service.CustomerService;

/**
 * Servlet implementation class DashboardServlet
 * Demonstrates how to READ both SESSIONS and COOKIES
 * 
 * @author Isaac-1-lang
 * @version 1.0
 * @date 2026-01-08
 */
public class DashboardServlet extends HttpServlet {

    /**
     * Display dashboard - shows how to read session and cookie data
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        
        // Check if session exists and has user attribute
        if(session == null || session.getAttribute("user") == null){
            response.sendRedirect("login");
            return;
        }
        
        // Read data from session
        String username = (String) session.getAttribute("user");
        Long loginTime = (Long) session.getAttribute("loginTime");
        
        // Store in request for JSP to display
        request.setAttribute("username", username);
        if(loginTime != null) {
            request.setAttribute("loginTime", loginTime);
        }
        
        // Get session ID (unique identifier)
        String sessionId = session.getId();
        request.setAttribute("sessionId", sessionId);
        
        // Get session creation time
        long creationTime = session.getCreationTime();
        request.setAttribute("creationTime", creationTime);
        
        // Get last accessed time
        long lastAccessed = session.getLastAccessedTime();
        request.setAttribute("lastAccessed", lastAccessed);
        
        // ========== READING COOKIE DATA ==========
        // Get all cookies from the request
        Cookie[] cookies = request.getCookies();
        
        String rememberedUsername = null;
        String lastLogin = null;
        
        if(cookies != null) {
            // Loop through all cookies to find the ones we want
            for(Cookie cookie : cookies) {
                if("rememberedUsername".equals(cookie.getName())) {
                    rememberedUsername = cookie.getValue();
                }
                if("lastLogin".equals(cookie.getName())) {
                    lastLogin = cookie.getValue();
                }
            }
        }
        
        // Store cookie data in request for JSP
        request.setAttribute("rememberedUsername", rememberedUsername);
        request.setAttribute("lastLogin", lastLogin);
        
        // ========== READ CUSTOMERS ==========
        // Use CustomerService to retrieve all customers
        CustomerService customerService = new CustomerService();
        List<CustomerModel> customers = customerService.getAllCustomers();
        request.setAttribute("customers", customers);
        
        request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
    }
    /**
     * Handle POST requests - CREATE new customer
     * @param request HttpServletRequest
     * @param response HttpServletResponse
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
        String fullName = request.getParameter("fullName");
        String orderIdStr = request.getParameter("orderId");
        
        // Validate input
        if (fullName == null || fullName.trim().isEmpty() || orderIdStr == null || orderIdStr.trim().isEmpty()) {
            request.setAttribute("error", "Full name and Order ID are required");
            doGet(request, response);
            return;
        }
        
        try {
            int orderId = Integer.parseInt(orderIdStr);
            
            // Create customer model
            CustomerModel customer = new CustomerModel();
            customer.setFullName(fullName.trim());
            customer.setOrder_id(orderId);
            
            // Use service to add customer
            CustomerService customerService = new CustomerService();
            boolean success = customerService.addCustomer(customer);
            
            if (success) {
                request.setAttribute("success", "Customer added successfully");
            } else {
                request.setAttribute("error", "Failed to add customer");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid Order ID format");
        }
        
        // Redirect to GET to show updated list
        doGet(request, response);
    }
    
    /**
     * Handle PUT requests - UPDATE existing customer
     * Note: Most browsers don't support PUT directly, so this might be called via POST with _method=PUT
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Check session
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("user") == null){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        
        // Get form parameters
        String idStr = request.getParameter("id");
        String fullName = request.getParameter("fullName");
        String orderIdStr = request.getParameter("orderId");
        
        // Validate input
        if (idStr == null || fullName == null || fullName.trim().isEmpty() || orderIdStr == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing required parameters");
            return;
        }
        
        try {
            int id = Integer.parseInt(idStr);
            int orderId = Integer.parseInt(orderIdStr);
            
            // Create customer model
            CustomerModel customer = new CustomerModel();
            customer.setId(id);
            customer.setFullName(fullName.trim());
            customer.setOrder_id(orderId);
            
            // Use service to update customer
            CustomerService customerService = new CustomerService();
            boolean success = customerService.updateCustomer(customer);
            
            if (success) {
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("Customer updated successfully");
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update customer");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid ID or Order ID format");
        }
    }
    
    /**
     * Handle DELETE requests - DELETE customer
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Check session
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("user") == null){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        
        // Get customer ID from parameter
        String idStr = request.getParameter("id");
        
        if (idStr == null || idStr.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Customer ID is required");
            return;
        }
        
        try {
            int id = Integer.parseInt(idStr);
            
            // Use service to delete customer
            CustomerService customerService = new CustomerService();
            boolean success = customerService.deleteCustomer(id);
            
            if (success) {
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("Customer deleted successfully");
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to delete customer");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid customer ID format");
        }
    }
}
