package com.helloWorld;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;



public class RegisterServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request,HttpServletResponse response) 
      throws ServletException,IOException {
        request.getRequestDispatcher("/register.jsp").forward(request,response);
      }
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