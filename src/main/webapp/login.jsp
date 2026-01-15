<html>

<head>
  <title>Login</title>
  <link rel="stylesheet" href="css/style.css">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <style>
    body {
      background: black;
    }
    .footer-text {
      color: black;
      font-size: 14px;
      margin-top: 20px;
    }
    .footer-text a {
      color: black;
    }
    .footer-text a:hover {
      color: #764ba2;
      text-decoration: underline;
    }
    input[type="submit"] {
      background: black;
      color: white;
      border: none;
      border-radius: 12px;
      font-size: 16px;
      font-weight: 600;
      cursor: pointer;
    }

  </style>
</head>

<body>
  <div class="container">
    <div class="card">
      <h2>Login</h2>
      <c:if test="${not empty error}">
        <p class="error">${error}</p>
      </c:if>
      <form method="post">
        <div>
          <label for="username">Username</label>
          <input type="text" id="username" name="username" placeholder="Enter your username" required>
        </div>
        <div>
          <label for="password">Password</label>
          <input type="password" id="password" name="password" placeholder="Enter your password" required>
        </div>
        <input type="submit" value="Login">
      </form>
      <div class="mt-4 text-center">
        <p class="footer-text">Don't have an account? <a href="register">Register</a></p>
      </div>
    </div>
  </div>
  <script src="js/script.js"></script>
</body>

</html>