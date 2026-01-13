package com.helloworld;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

/**
 * Servlet implementation class LogoutServlet
 * Demonstrates how to CLEAR both SESSIONS and COOKIES
 * 
 * @author Isaac-1-lang
 * @version 1.0
 * @date 2026-01-08
 */
public class LogoutServlet extends HttpServlet {

    /**
     * Handle logout - clears session and cookies
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // ========== CLEARING SESSION ==========
        HttpSession session = request.getSession(false);
        
        if(session != null) {
            // Option 1: Remove specific attributes
            // session.removeAttribute("user");
            // session.removeAttribute("loginTime");
            
            // Option 2: Invalidate entire session (recommended for logout)
            // This destroys the session and all its data
            session.invalidate();
        }
        
        // ========== CLEARING COOKIES ==========
        // To delete a cookie, you must create a new cookie with:
        // - Same name
        // - Same path (if you set it before)
        // - Same domain (if you set it before)
        // - maxAge = 0 (this tells browser to delete it)
        
        Cookie[] cookies = request.getCookies();
        
        if(cookies != null) {
            for(Cookie cookie : cookies) {
                // Delete specific cookies we created
                if("rememberedUsername".equals(cookie.getName()) || 
                   "lastLogin".equals(cookie.getName())) {
                    
                    // Create a new cookie with same name and path
                    Cookie deleteCookie = new Cookie(cookie.getName(), "");
                    deleteCookie.setMaxAge(0); // This tells browser to delete it
                    deleteCookie.setPath(cookie.getPath()); // Must match original path
                    response.addCookie(deleteCookie);
                }
            }
        }
        
        // Redirect to login page
        response.sendRedirect(request.getContextPath() + "/login");
    }
    
    /**
     * Handle POST logout (same as GET)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
