package com.helloworld;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet to handle email verification code submission
 * 
 * @author Isaac-1-lang
 * @version 1.0
 */
public class VerifyCodeServlet extends HttpServlet {

    /**
     * Display verification code entry form
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Get email from session or parameter
        String email = request.getParameter("email");
        if (email != null) {
            request.setAttribute("email", email);
        }
        
        request.getRequestDispatcher("/verifyCode.jsp").forward(request, response);
    }

    /**
     * Handle verification code submission
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String code = request.getParameter("code");
        
        // Validate input
        if (email == null || email.trim().isEmpty() || code == null || code.trim().isEmpty()) {
            request.setAttribute("error", "Email and verification code are required");
            request.setAttribute("email", email);
            request.getRequestDispatcher("/verifyCode.jsp").forward(request, response);
            return;
        }
        
        try {
            // Check if the code matches for this email
            // Use email to identify the user since we don't know the exact primary key column name
            String sql = "SELECT fullname FROM users WHERE email = ? AND verification_code = ?";
            
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement st = conn.prepareStatement(sql)) {
                
                st.setString(1, email.trim());
                st.setString(2, code.trim());
                
                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        // Code is correct - get username
                        String username = rs.getString("fullname");
                        
                        // Update verification status using email (more reliable than id)
                        String updateSql = "UPDATE users SET is_verified = true WHERE email = ?";
                        try (PreparedStatement updateSt = conn.prepareStatement(updateSql)) {
                            updateSt.setString(1, email.trim());
                            updateSt.executeUpdate();
                        }
                        
                        // Clear verification code for security
                        String clearCodeSql = "UPDATE users SET verification_code = NULL WHERE email = ?";
                        try (PreparedStatement clearSt = conn.prepareStatement(clearCodeSql)) {
                            clearSt.setString(1, email.trim());
                            clearSt.executeUpdate();
                        }
                        
                        // Create session and redirect to login
                        HttpSession session = request.getSession();
                        session.setAttribute("user", username);
                        request.setAttribute("success", "Email verified successfully! You can now login.");
                        response.sendRedirect(request.getContextPath() + "/login?verified=true");
                        return;
                    } else {
                        // Invalid code
                        request.setAttribute("error", "Invalid verification code. Please try again.");
                        request.setAttribute("email", email);
                        request.getRequestDispatcher("/verifyCode.jsp").forward(request, response);
                        return;
                    }
                }
            }
        } catch (Exception e) {
            request.setAttribute("error", "Verification failed: " + e.getMessage());
            request.setAttribute("email", email);
            request.getRequestDispatcher("/verifyCode.jsp").forward(request, response);
        }
    }
}
