package com.helloworld;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import com.helloworld.util.VerificationCodeGenerator;
import com.helloworld.util.EmailSender;
import com.helloworld.util.PasswordHasher;

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

          // Generate 6-digit verification code
          String verificationCode = VerificationCodeGenerator.generateCode();
          
          // IMPORTANT: Update these column names to match your real database schema.
          // This version assumes your table has columns: fullname, password, email, phone, is_verified, verification_code
          // and that id is auto-generated (SERIAL/IDENTITY).
        //   We need to hash the password before storing it in the database
        String hashedPassword = PasswordHasher.hashPassword(password);
          String sql = "INSERT INTO users(fullname, password, email, phone, is_verified, verification_code) VALUES (?,?,?,?,?,?)";

          try (Connection conn = DatabaseConnection.getConnection();
               PreparedStatement st = conn.prepareStatement(sql)) {

              // Set parameters BEFORE executing
              st.setString(1, username); // fullname (or username)
              st.setString(2, hashedPassword); // password (in real apps hash passwords!)
              st.setString(3, email);
              st.setString(4, phone);
              st.setBoolean(5, false); // is_verified - set to false initially
              st.setString(6, verificationCode); // verification_code

              int rows = st.executeUpdate(); // use executeUpdate() for INSERT/UPDATE/DELETE
              if (rows > 0) {
                  // Send verification email
                  String emailSubject = "Email Verification Code";
                  String emailBody = "Hello " + username + ",\n\n"
                          + "Thank you for registering! Your verification code is: " + verificationCode + "\n\n"
                          + "Please enter this code to verify your email address.\n\n"
                          + "If you did not register, please ignore this email.\n\n"
                          + "Best regards,\n"
                          + "Your App Team";
                  
                  try {
                      EmailSender.sendEmail(email, emailSubject, emailBody);
                      // Store email in session for verification page
                      HttpSession session = request.getSession();
                      session.setAttribute("verificationEmail", email);
                      // Redirect to verification page
                      response.sendRedirect(request.getContextPath() + "/verifyCode?email=" + email);
                  } catch (Exception e) {
                      // Email sending failed, but registration succeeded
                      // Still redirect to verification page
                      HttpSession session = request.getSession();
                      session.setAttribute("verificationEmail", email);
                      request.setAttribute("warning", "Registration successful, but email could not be sent. Please contact support.");
                      response.sendRedirect(request.getContextPath() + "/verifyCode?email=" + email);
                  }
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