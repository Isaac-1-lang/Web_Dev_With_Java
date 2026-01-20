package com.helloworld;

import com.helloworld.service.StudentServices;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import com.helloworld.model.StudentModel;

/**
 * Servlet implementation class DashboardServlet
 * Demonstrates how to READ both SESSIONS and COOKIES
 * 
 * @author Isaac-1-lang
 * @version 1.0
 * @date 2026-01-08
 */
public class DashboardServlet extends HttpServlet {

    private static final String FLASH_SUCCESS = "flashSuccess";
    private static final String FLASH_ERROR = "flashError";

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

        // Pull flash messages from session (if any)
        String flashSuccess = (String) session.getAttribute(FLASH_SUCCESS);
        String flashError = (String) session.getAttribute(FLASH_ERROR);
        if (flashSuccess != null) {
            request.setAttribute("success", flashSuccess);
            session.removeAttribute(FLASH_SUCCESS);
        }
        if (flashError != null) {
            request.setAttribute("error", flashError);
            session.removeAttribute(FLASH_ERROR);
        }
        
        // ========== READ Students ==========
        // Use StudentService to retrieve all students
        StudentServices studentService = new StudentServices();
        List<StudentModel> students = studentService.getAll();
        request.setAttribute("students", students);
        
        request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
    }
    /**
     * Handle POST requests - CREATE new student
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
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String school = request.getParameter("school");
        String dobStr = request.getParameter("dob");
        
        // Validate input
        if (name == null || name.trim().isEmpty()
                || email == null || email.trim().isEmpty()
                || school == null || school.trim().isEmpty()
                || dobStr == null || dobStr.trim().isEmpty()) {
            session.setAttribute(FLASH_ERROR, "Name, email, school and date of birth are required");
            response.sendRedirect("dashboard");
            return;
        }
        
        try {
            LocalDate dob = LocalDate.parse(dobStr.trim());

            StudentModel student = new StudentModel();
            student.setName(name.trim());
            student.setEmail(email.trim());
            student.setSchool(school.trim());
            student.setDob(dob);

            StudentServices studentService = new StudentServices();
            studentService.addStudent(student);

            session.setAttribute(FLASH_SUCCESS, "Student added successfully");
        } catch (DateTimeParseException e) {
            session.setAttribute(FLASH_ERROR, "Invalid date of birth format");
        } catch (Exception e) {
            session.setAttribute(FLASH_ERROR, "Failed to add student");
        }
        
        // PRG: redirect to GET
        response.sendRedirect("dashboard");
    }
}
