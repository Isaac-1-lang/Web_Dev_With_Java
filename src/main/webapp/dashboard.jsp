<html>

<head>
  <title>Dashboard</title>
  <link rel="stylesheet" href="css/style.css">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body class="dashboard">
  <header class="dashboard-header">
    <h2>Dashboard</h2>
    <a href="logout" class="btn" style="width: auto; margin: 0;">Logout</a>
  </header>

  <main class="dashboard-content container">
    <div class="card">
      <h3>Welcome, ${sessionScope.user}</h3>
      <p class="mt-4">You have successfully logged in.</p>
      <p>This is your dashboard area where you can manage your settings and view your profile.</p>
      
      <!-- Session Information -->
      <div style="margin-top: 20px; padding: 15px; background: #f5f5f5; border-radius: 5px;">
        <h4>Session Information (Server-side Storage)</h4>
        <p><strong>Username from Session:</strong> ${username != null ? username : 'N/A'}</p>
        <p><strong>Session ID:</strong> ${sessionId != null ? sessionId : 'N/A'}</p>
        <% if (request.getAttribute("loginTime") != null) { %>
          <p><strong>Login Time:</strong> ${loginTime}</p>
        <% } %>
        <% if (request.getAttribute("creationTime") != null) { %>
          <p><strong>Session Created:</strong> ${creationTime}</p>
        <% } %>
        <p><small><em>Sessions are stored on the server. Data is secure and can store objects.</em></small></p>
      </div>
      
      <!-- Cookie Information -->
      <div style="margin-top: 20px; padding: 15px; background: #e8f4f8; border-radius: 5px;">
        <h4>Cookie Information (Client-side Storage)</h4>
        <% if (request.getAttribute("rememberedUsername") != null) { %>
          <p><strong>Remembered Username:</strong> ${rememberedUsername}</p>
        <% } else { %>
          <p><em>No remembered username cookie found</em></p>
        <% } %>
        <% if (request.getAttribute("lastLogin") != null) { %>
          <p><strong>Last Login (from cookie):</strong> ${lastLogin}</p>
        <% } %>
        <p><small><em>Cookies are stored in the browser. They persist after browser closes and can be read by JavaScript.</em></small></p>
      </div>
      
      <!-- Quick Reference -->
      <div style="margin-top: 20px; padding: 15px; background: #fff3cd; border-radius: 5px; border-left: 4px solid #ffc107;">
        <h4>💡 Quick Reference</h4>
        <p><strong>Sessions:</strong> Use for authentication, sensitive data, temporary storage</p>
        <p><strong>Cookies:</strong> Use for "Remember Me", preferences, non-sensitive data</p>
        <p><small>Check <code>SESSIONS_AND_COOKIES_GUIDE.md</code> for detailed explanation!</small></p>
      </div>
    </div>
  </main>
  <script src="js/script.js"></script>
</body>

</html>