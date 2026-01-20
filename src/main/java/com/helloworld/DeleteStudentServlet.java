package com.helloworld;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

import com.helloworld.service.StudentServices;

/**
 * Servlet for deleting students
 * 
 * @author Isaac-1-lang
 * @version 1.0
 */
public class DeleteStudentServlet extends HttpServlet {

    private static final String FLASH_SUCCESS = "flashSuccess";
    private static final String FLASH_ERROR = "flashError";

    /**
     * Handle student deletion
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
        
        // Get student ID from parameter
        String idStr = request.getParameter("id");
        
        if (idStr == null || idStr.trim().isEmpty()) {
            request.getSession().setAttribute(FLASH_ERROR, "Student ID is required");
            response.sendRedirect("dashboard");
            return;
        }
        
        try {
            int id = Integer.parseInt(idStr);
            
            StudentServices studentService = new StudentServices();
            studentService.deleteStudent(id);
            request.getSession().setAttribute(FLASH_SUCCESS, "Student deleted successfully");
        } catch (NumberFormatException e) {
            request.getSession().setAttribute(FLASH_ERROR, "Invalid student ID format");
        } catch (Exception e) {
            request.getSession().setAttribute(FLASH_ERROR, "Failed to delete student");
        }
        
        // Redirect back to dashboard
        response.sendRedirect("dashboard");
    }
}
