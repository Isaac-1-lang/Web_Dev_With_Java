package com.helloworld;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;


/**
 * Servlet implementation class LoginServlet
 * Demonstrates both SESSIONS and COOKIES
 * 
 * SESSIONS vs COOKIES:
 * - SESSIONS: Stored on SERVER, more secure, expire when browser closes (or timeout)
 * - COOKIES: Stored on CLIENT (browser), can persist longer, less secure
 * 
 * @author Isaac-1-lang
 * @version 1.0
 * @date 2026-01-08
 */
public class LoginServlet extends HttpServlet {
    
    /**
     * Display login page
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
    
    /**
     * Handle login form submission
     * Demonstrates:
     * 1. How to use SESSIONS (server-side storage)
     * 2. How to use COOKIES (client-side storage)
     * 
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = UserStore.getUser(username);
        if(user != null && user.getPassword().equals(password)){
            
            // ========== SESSION EXAMPLE ==========
            // Sessions are stored on the SERVER
            // They automatically expire after a period of inactivity (default: 30 minutes)
            // They're more secure because data stays on server
            
            // Get or create a session
            // request.getSession() - creates new session if doesn't exist
            // request.getSession(false) - returns null if session doesn't exist (doesn't create)
            HttpSession session = request.getSession();
            
            // Store data in session (can store any Object)
            session.setAttribute("user", user.getUsername());
            session.setAttribute("loginTime", System.currentTimeMillis());
            
            // Set session timeout (in seconds) - optional
            // session.setMaxInactiveInterval(60 * 60); // 1 hour
            
            // ========== COOKIE EXAMPLE ==========
            // Cookies are stored on the CLIENT (browser)
            // They persist even after browser closes (if maxAge is set)
            // Less secure - can be read/modified by client
            
            // Create a cookie to remember username (for "Remember Me" functionality)
            Cookie usernameCookie = new Cookie("rememberedUsername", username);
            usernameCookie.setMaxAge(60 * 60 * 24 * 7); // 7 days in seconds
            usernameCookie.setPath("/"); // Available to entire application
            // usernameCookie.setHttpOnly(true); // Prevents JavaScript access (security)
            // usernameCookie.setSecure(true); // Only sent over HTTPS
            response.addCookie(usernameCookie);
            
            // Create another cookie example - last login time
            Cookie lastLoginCookie = new Cookie("lastLogin", String.valueOf(System.currentTimeMillis()));
            lastLoginCookie.setMaxAge(60 * 60 * 24 * 30); // 30 days
            lastLoginCookie.setPath("/");
            response.addCookie(lastLoginCookie);
            
            response.sendRedirect(request.getContextPath()+"/dashboard");
        } else {
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
