package com.helloworld.util;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoggingFilter implements Filter {
  @Override
  public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain) throws ServletException,IOException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;
    String path = httpRequest.getRequestURI();
    System.out.println("LoggingFilter: " + path);
    chain.doFilter(request, response);
    httpResponse.getWriter().println("LoggingFilter: " + path + " " + httpRequest.getMethod() + " " + httpRequest.getRemoteAddr());
  }
  
}
