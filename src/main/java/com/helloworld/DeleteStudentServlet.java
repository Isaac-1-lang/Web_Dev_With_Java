package com.helloworld;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

/**
 * Servlet for deleting customers
 * 
 * @author Isaac-1-lang
 * @version 1.0
 */
public class DeleteStudentServlet extends HttpServlet {

    /**
     * Handle customer deletion
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
            
            // Use service to delete customer
            StudentService customerService = new StudentService();
            boolean success = customerService.deleteCustomer(id);
            
            if (success) {
                request.setAttribute("success", "Customer deleted successfully");
            } else {
                request.setAttribute("error", "Failed to delete customer");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid customer ID format");
        }
        
        // Redirect back to dashboard
        response.sendRedirect("dashboard");
    }
}
