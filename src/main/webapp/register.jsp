<html>

<head>
  <title>Register</title>
  <link rel="stylesheet" href="css/style.css">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>
  <div class="container">
    <div class="card">
      <h2>Register</h2>
      <c:if test="${not empty error}">
        <p class="error">${error}</p>
      </c:if>
      <form method="post">
        <div>
          <label for="username">Username</label>
          <input type="text" id="username" name="username" placeholder="Choose a username" required>
        </div>
        <div>
          <label for="email">Email</label>
          <input type="email" id="email" name="email" placeholder="isaprecieux112@gmail.com" required class="email">
        </div>
        <div>
          <label for="password">Password</label>
          <input type="password" id="password" name="password" placeholder="Choose a secure password" required>
        </div>
        <div>
          <label for="phone">Phone</label>
          <input type="phone" id="phone" name="phone" placeholder="250 7XXXXXXXX" required>
        </div>
        <input type="submit" value="Register">
      </form>
      <div class="mt-4 text-center">
        <p>Already have an account? <a href="login">Login</a></p>
      </div>
    </div>
  </div>
  <script src="js/script.js"></script>
</body>

</html>