package com.helloworld;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
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
          String username = request.getParameter("username");
          String password = request.getParameter("password");
          String email = request.getParameter("email");
          String phone = request.getParameter("phone");

          // Basic validation
          if (username == null || password == null || email == null || phone == null
                  || username.isBlank() || password.isBlank() || email.isBlank() || phone.isBlank()) {
              request.setAttribute("error", "All fields are required.");
              request.getRequestDispatcher("/register.jsp").forward(request, response);
              return;
          }

          // IMPORTANT: Update these column names to match your real database schema.
          // This version assumes your table has columns: fullname, code, email, phone
          // and that id is auto-generated (SERIAL/IDENTITY).
          String sql = "INSERT INTO users(fullname, password, email, phone) VALUES (?,?,?,?)";

          try (Connection conn = DatabaseConnection.getConnection();
               PreparedStatement st = conn.prepareStatement(sql)) {

              // Set parameters BEFORE executing
              st.setString(1, username); // fullname (or username)
              st.setString(2, password); // code (your password column; in real apps hash passwords!)
              st.setString(3, email);
              st.setString(4, phone);

              int rows = st.executeUpdate(); // use executeUpdate() for INSERT/UPDATE/DELETE
              if (rows > 0) {
                  response.sendRedirect(request.getContextPath() + "/dashboard");
              } else {
                  request.setAttribute("error", "Registration failed. No rows inserted.");
                  request.getRequestDispatcher("/register.jsp").forward(request, response);
              }
          } catch (Exception e) {
              // Show the error on the page so you don't get a \"blank\" reload
              request.setAttribute("error", "Registration failed: " + e.getMessage());
              request.getRequestDispatcher("/register.jsp").forward(request, response);
          }
      }
}