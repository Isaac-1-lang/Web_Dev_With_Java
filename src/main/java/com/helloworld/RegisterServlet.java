package com.helloworld;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;


/**
 * Servlet implementation class RegisterServlet
 * @author Isaac-1-lang
 * @version 1.0
 * @date 2026-01-08
 */
public class RegisterServlet extends HttpServlet {
  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doGet(HttpServletRequest request,HttpServletResponse response) 
      throws ServletException,IOException {
        request.getRequestDispatcher("/register.jsp").forward(request,response);
      }
      /**
       * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
       */
      @Override
      protected void doPost(HttpServletRequest request,HttpServletResponse response) 
         throws ServletException,IOException {
          String username=request.getParameter("username");
          String password = request.getParameter("password");
          if(username==null || password==null || username.isEmpty() || password.isEmpty()) {
            request.setAttribute("error","Username or password can't be empty");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
            return;
          }
          boolean added = UserStore.addUser(new User(username,password));
          if(added) {
            response.sendRedirect(request.getContextPath()+"/login");
          } else {
            request.setAttribute("error","Username already exists");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
          }
        }
      }