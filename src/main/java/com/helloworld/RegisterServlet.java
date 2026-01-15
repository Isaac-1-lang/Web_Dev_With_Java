package com.helloworld;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;


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
          String email   = request.getParameter("email");
          String phone  = request.getParameter("phone");
          if(username==null || password==null || username.isEmpty() || password.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            request.setAttribute("error","Username or password can't be empty");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
            String sql  = "INSERT INTO users(Id,fullname,code,email) VALUES (?,?,?,?)";
            try (Connection conn  = DatabaseConnection.getConnection();
            PreparedStatement st  = conn.prepareStatement(sql); ResultSet rs  = st.executeQuery()) {
              st.setString(1, username);
              st.setString(2, password);
              st.setString(3, email);
              st.setString(4, phone);
              st.executeUpdate();
              System.out.println("User registered successfully");
              response.sendRedirect(request.getContextPath()+"/login");
            } catch (Exception e) {
              e.printStackTrace();
            } finally {
              System.out.println("Finally the app ends withoutt clearing the results");
          }
          }
        }
      }