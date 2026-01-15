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
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
            // Fetch from the database if the username and password are correct

            String sql = "SELECT * FROM users WHERE fullname = ? AND password = ?";

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement st = conn.prepareStatement(sql)) {

                st.setString(1, username);
                st.setString(2, password);

                try (ResultSet rs = st.executeQuery()) {   // executeQuery only here
                    if (rs.next()) {
                        // ---------- SUCCESSFUL LOGIN ----------
                        // 1) Create session and store user info (used by DashboardServlet)
                        HttpSession session = request.getSession();
                        session.setAttribute("user", username);

                        // 2) (Optional) create a simple \"rememberedUsername\" cookie
                        Cookie usernameCookie = new Cookie("rememberedUsername", username);
                        usernameCookie.setMaxAge(60 * 60 * 24 * 7); // 7 days
                        usernameCookie.setPath("/");
                        response.addCookie(usernameCookie);

                        // 3) Redirect to dashboard
                        response.sendRedirect(request.getContextPath() + "/dashboard");
                        return;
                    } else {
                        request.setAttribute("error", "Invalid username or password");
                        request.getRequestDispatcher("/login.jsp").forward(request, response);
                        return;
                    }
                }
            } catch (Exception e) {
                request.setAttribute("error", "Database error: " + e.getMessage());
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        }
    }