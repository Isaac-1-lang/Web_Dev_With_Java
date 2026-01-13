package com.helloworld;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

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

        // ========== READING SESSION DATA ==========
        // Get existing session (false = don't create if doesn't exist)
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
        
        // ========== SESSION METHODS YOU CAN USE ==========
        // session.getAttribute("key") - get value
        // session.setAttribute("key", value) - set value
        // session.removeAttribute("key") - remove value
        // session.invalidate() - destroy entire session
        // session.getMaxInactiveInterval() - get timeout in seconds
        // session.setMaxInactiveInterval(seconds) - set timeout
        // session.isNew() - check if session was just created
        
        request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
    }
}
