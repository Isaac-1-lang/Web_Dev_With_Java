package com.helloWorld;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;


/**
 * Servlet implementation class LoginServlet
 * @author Isaac-1-lang
 * @version 1.0
 * @date 2026-01-08
 */
public class LoginServlet extends HttpServlet {
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = UserStore.getUser(username);
        if(user != null && user.getPassword().equals(password)){
            HttpSession session = request.getSession();
            session.setAttribute("user", user.getUsername());
            response.sendRedirect(request.getContextPath()+"/dashboard");
        } else {
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
