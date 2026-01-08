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
        
        // Increment visit counter
        visitCount++;
        
        // Get current time
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy 'at' hh:mm:ss a");
        String currentTime = now.format(formatter);
        
        // Get user agent for personalization
        String userAgent = req.getHeader("User-Agent");
        String browser = detectBrowser(userAgent);
        
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("    <title>Welcome - Isaac's Web App</title>");
            out.println("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("    <style>");
            out.println("        * {");
            out.println("            margin: 0;");
            out.println("            padding: 0;");
            out.println("            box-sizing: border-box;");
            out.println("        }");
            out.println("        ");
            out.println("        body {");
            out.println("            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;");
            out.println("            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);");
            out.println("            min-height: 100vh;");
            out.println("            display: flex;");
            out.println("            justify-content: center;");
            out.println("            align-items: center;");
            out.println("            padding: 20px;");
            out.println("        }");
            out.println("        ");
            out.println("        .container {");
            out.println("            background: rgba(255, 255, 255, 0.95);");
            out.println("            border-radius: 20px;");
            out.println("            padding: 50px;");
            out.println("            box-shadow: 0 20px 60px rgba(0, 0, 0, 0.3);");
            out.println("            max-width: 600px;");
            out.println("            width: 100%;");
            out.println("            animation: fadeIn 0.6s ease-out;");
            out.println("        }");
            out.println("        ");
            out.println("        @keyframes fadeIn {");
            out.println("            from {");
            out.println("                opacity: 0;");
            out.println("                transform: translateY(30px);");
            out.println("            }");
            out.println("            to {");
            out.println("                opacity: 1;");
            out.println("                transform: translateY(0);");
            out.println("            }");
            out.println("        }");
            out.println("        ");
            out.println("        h1 {");
            out.println("            color: #667eea;");
            out.println("            font-size: 2.5em;");
            out.println("            margin-bottom: 20px;");
            out.println("            text-align: center;");
            out.println("            animation: slideDown 0.8s ease-out;");
            out.println("        }");
            out.println("        ");
            out.println("        @keyframes slideDown {");
            out.println("            from {");
            out.println("                opacity: 0;");
            out.println("                transform: translateY(-20px);");
            out.println("            }");
            out.println("            to {");
            out.println("                opacity: 1;");
            out.println("                transform: translateY(0);");
            out.println("            }");
            out.println("        }");
            out.println("        ");
            out.println("        .info-card {");
            out.println("            background: #f8f9fa;");
            out.println("            border-left: 4px solid #667eea;");
            out.println("            padding: 15px;");
            out.println("            margin: 15px 0;");
            out.println("            border-radius: 8px;");
            out.println("            transition: transform 0.3s ease;");
            out.println("        }");
            out.println("        ");
            out.println("        .info-card:hover {");
            out.println("            transform: translateX(5px);");
            out.println("            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);");
            out.println("        }");
            out.println("        ");
            out.println("        .info-label {");
            out.println("            font-weight: bold;");
            out.println("            color: #764ba2;");
            out.println("            font-size: 0.9em;");
            out.println("            text-transform: uppercase;");
            out.println("            letter-spacing: 1px;");
            out.println("        }");
            out.println("        ");
            out.println("        .info-value {");
            out.println("            color: #2c3e50;");
            out.println("            font-size: 1.1em;");
            out.println("            margin-top: 5px;");
            out.println("        }");
            out.println("        ");
            out.println("        .badge {");
            out.println("            display: inline-block;");
            out.println("            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);");
            out.println("            color: white;");
            out.println("            padding: 8px 20px;");
            out.println("            border-radius: 25px;");
            out.println("            font-size: 0.9em;");
            out.println("            font-weight: bold;");
            out.println("            margin-top: 20px;");
            out.println("            animation: pulse 2s infinite;");
            out.println("        }");
            out.println("        ");
            out.println("        @keyframes pulse {");
            out.println("            0%, 100% {");
            out.println("                transform: scale(1);");
            out.println("            }");
            out.println("            50% {");
            out.println("                transform: scale(1.05);");
            out.println("            }");
            out.println("        }");
            out.println("        ");
            out.println("        .footer {");
            out.println("            text-align: center;");
            out.println("            color: #7f8c8d;");
            out.println("            font-size: 0.9em;");
            out.println("            margin-top: 30px;");
            out.println("            padding-top: 20px;");
            out.println("            border-top: 1px solid #e0e0e0;");
            out.println("        }");
            out.println("        ");
            out.println("        .emoji {");
            out.println("            font-size: 1.5em;");
            out.println("            display: inline-block;");
            out.println("            animation: rotate 3s linear infinite;");
            out.println("        }");
            out.println("        ");
            out.println("        @keyframes rotate {");
            out.println("            from {");
            out.println("                transform: rotate(0deg);");
            out.println("            }");
            out.println("            to {");
            out.println("                transform: rotate(360deg);");
            out.println("            }");
            out.println("        }");
            out.println("        ");
            out.println("        @media (max-width: 600px) {");
            out.println("            .container {");
            out.println("                padding: 30px 20px;");
            out.println("            }");
            out.println("            ");
            out.println("            h1 {");
            out.println("                font-size: 2em;");
            out.println("            }");
            out.println("        }");
            out.println("    </style>");
            out.println("</head>");
            out.println("<body>");
            out.println("    <div class=\"container\">");
            out.println("        <h1>Welcome to Isaac's Web App <span class=\"emoji\">🚀</span></h1>");
            out.println("        ");
            out.println("        <div class=\"info-card\">");
            out.println("            <div class=\"info-label\">Current Time</div>");
            out.println("            <div class=\"info-value\">" + currentTime + "</div>");
            out.println("        </div>");
            out.println("        ");
            out.println("        <div class=\"info-card\">");
            out.println("            <div class=\"info-label\">Page Visits</div>");
            out.println("            <div class=\"info-value\">" + visitCount + " time(s)</div>");
            out.println("        </div>");
            out.println("        ");
            out.println("        <div class=\"info-card\">");
            out.println("            <div class=\"info-label\">Your Browser</div>");
            out.println("            <div class=\"info-value\">" + browser + "</div>");
            out.println("        </div>");
            out.println("        ");
            out.println("        <div class=\"info-card\">");
            out.println("            <div class=\"info-label\">Server Status</div>");
            out.println("            <div class=\"info-value\">&#x2705; Running smoothly</div>");
            out.println("        </div>");
            out.println("        ");
            out.println("        <div style=\"text-align: center;\">");
            out.println("            <span class=\"badge\">Powered by Java Servlet</span>");
            out.println("        </div>");
            out.println("        ");
            out.println("        <div class=\"footer\">");
            out.println("            Built with &#x2764;&#xFE0F; by Isaac | Jakarta EE");
            out.println("        </div>");
            out.println("    </div>");
            out.println("</body>");
            out.println("</html>");
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