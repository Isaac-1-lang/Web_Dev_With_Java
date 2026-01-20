package com.helloworld;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import com.helloworld.model.StudentModel;
import com.helloworld.service.StudentServices;

/**
 * Servlet for editing customer information
 * Handles both GET (display edit form) and POST (update customer) requests
 * 
 * @author Isaac-1-lang
 * @version 1.0
 */
public class EditStudentServlet extends HttpServlet {

    private static final String FLASH_SUCCESS = "flashSuccess";
    private static final String FLASH_ERROR = "flashError";

    /**
     * Display edit form with student data
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
            request.setAttribute("error", "Student ID is required");
            response.sendRedirect("dashboard");
            return;
        }
        
        try {
            int id = Integer.parseInt(idStr);
            
            // Use service to get student by ID
            StudentServices studentService = new StudentServices();
            StudentModel student = studentService.getStudentById(id);
            
            if (student == null) {
                request.setAttribute("error", "Student not found");
                response.sendRedirect("dashboard");
                return;
            }
            
            // Set student data for edit form
            request.setAttribute("student", student);
            request.getRequestDispatcher("/edit-student.jsp").forward(request, response);
            
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid student ID format");
            response.sendRedirect("dashboard");
        }
    }

    /**
     * Handle student update
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
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String school = request.getParameter("school");
        String dobStr = request.getParameter("dob");
        
        // Validate input
        if (idStr == null
                || name == null || name.trim().isEmpty()
                || email == null || email.trim().isEmpty()
                || school == null || school.trim().isEmpty()
                || dobStr == null || dobStr.trim().isEmpty()) {
            request.setAttribute("error", "All fields are required");
            doGet(request, response);
            return;
        }
        
        try {
            int id = Integer.parseInt(idStr);
            LocalDate dob = LocalDate.parse(dobStr.trim());
            
            // Create student model with updated data
            StudentModel student = new StudentModel();
            student.setId(id);
            student.setName(name.trim());
            student.setEmail(email.trim());
            student.setSchool(school.trim());
            student.setDob(dob);
            
            // Use service to update student
            StudentServices studentService = new StudentServices();
            studentService.updateStudent(student);

            session.setAttribute(FLASH_SUCCESS, "Student updated successfully");
            response.sendRedirect("dashboard");
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid student ID format");
            doGet(request, response);
        } catch (DateTimeParseException e) {
            request.setAttribute("error", "Invalid date of birth format");
            doGet(request, response);
        } catch (Exception e) {
            session.setAttribute(FLASH_ERROR, "Failed to update student");
            response.sendRedirect("dashboard");
        }
    }
}
