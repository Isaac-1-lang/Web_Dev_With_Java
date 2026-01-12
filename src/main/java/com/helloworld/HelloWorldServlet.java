package com.helloWorld;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HelloWorldServlet extends HttpServlet {
    
    private int visitCount = 0;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        
        // Get user agent for personalization
        String userAgent = req.getHeader("User-Agent");
        String browser = detectBrowser(userAgent);
        
        try (PrintWriter out = resp.getWriter()) {
            String html = """
           <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <title>Welcome Page</title>
                    <style>
                        body { font-family: Arial, sans-serif; background-color: #f0f0f0; padding: 20px; }
                        h1 { color: #333; }
                        p { font-size: 16px; }
                        ul { list-style-type: square; }
                        a { color: blue; text-decoration: none; }
                        a:hover { text-decoration: underline; }
                    </style>
                </head>
                <body>
                    <h1>Welcome to My Servlet!</h1>
                    <p>This is a simple HTML page generated from a Java servlet.</p>
                    <h2>Things you can do:</h2>
                    <ul>
                        <li>Learn Java Servlets</li>
                        <li>Explore HTML and CSS</li>
                        <li>Build dynamic web pages</li>
                    </ul>
                    <p>Check out more on 
                        <a href="https://www.oracle.com/java/technologies/servlet.html" target="_blank">
                            Java Servlets
                        </a>.
                    </p>
                </body>
                </html>
            """;
            out.println(html);
        }
    }
    
    private String detectBrowser(String userAgent) {
        if (userAgent == null) {
            return "Unknown Browser";
        }
        
        if (userAgent.contains("Edg")) {
            return "Microsoft Edge";
        } else if (userAgent.contains("Chrome")) {
            return "Google Chrome";
        } else if (userAgent.contains("Firefox")) {
            return "Mozilla Firefox";
        } else if (userAgent.contains("Safari") && !userAgent.contains("Chrome")) {
            return "Safari";
        } else if (userAgent.contains("Opera") || userAgent.contains("OPR")) {
            return "Opera";
        }
        
        return "Unknown Browser";
    }
}