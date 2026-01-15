package com.helloworld;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


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

        if(username == null || password == null || username.isEmpty() || password.isEmpty()) {
            request.setAttribute("error", "Username and password are required");
            // Fetch from the database if the username and password are correct
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement st = conn.prepareStatement(sql);
                ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    request.setAttribute("error", "Invalid username or password");
                } else {
                    request.setAttribute("error", "Invalid username or password");
                }
            } catch (Exception e) {
                request.setAttribute("error", "Database error: " + e.getMessage());
            }
        }
    }
}
