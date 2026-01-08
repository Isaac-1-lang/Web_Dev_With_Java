<html>

<head>
  <title>Login</title>
  <link rel="stylesheet" href="css/style.css">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
        <p>Don't have an account? <a href="register">Register</a></p>
      </div>
    </div>
  </div>
  <script src="js/script.js"></script>
</body>

</html>