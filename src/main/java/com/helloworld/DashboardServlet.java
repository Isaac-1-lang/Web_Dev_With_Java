package com.helloWorld;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

/**
 * Servlet implementation class DashboardServlet
 * @author Isaac-1-lang
 * @version 1.0
 * @date 2026-01-08
 */
public class DashboardServlet extends HttpServlet {

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("user") == null){
            response.sendRedirect("login");
            return;
        }

        request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
    }
}
