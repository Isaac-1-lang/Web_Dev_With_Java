<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Register - Create Your Account</title>
  <style>
    * {
      margin: 0;
      padding: 0;
      box-sizing: border-box;
    }

    body {
      font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, sans-serif;
      background: black;
      min-height: 100vh;
      display: flex;
      align-items: center;
      justify-content: center;
      padding: 20px;
    }

    .container {
      width: 100%;
      max-width: 440px;
    }

    .card {
      background: rgba(255, 255, 255, 0.95);
      backdrop-filter: blur(10px);
      border-radius: 20px;
      padding: 40px;
      box-shadow: 0 20px 60px rgba(0, 0, 0, 0.3);
      animation: slideUp 0.5s ease-out;
    }

    @keyframes slideUp {
      from {
        opacity: 0;
        transform: translateY(30px);
      }
      to {
        opacity: 1;
        transform: translateY(0);
      }
    }

    h2 {
      color: #1a202c;
      font-size: 32px;
      margin-bottom: 10px;
      font-weight: 700;
    }

    .subtitle {
      color: #718096;
      margin-bottom: 30px;
      font-size: 15px;
    }

    .error {
      background: #fee;
      color: #c53030;
      padding: 12px 16px;
      border-radius: 10px;
      font-size: 14px;
      margin-bottom: 20px;
      border-left: 4px solid #c53030;
    }

    .success {
      background: #f0fff4;
      color: #22543d;
      padding: 12px 16px;
      border-radius: 10px;
      font-size: 14px;
      margin-bottom: 20px;
      border-left: 4px solid #38a169;
    }

    form {
      display: flex;
      flex-direction: column;
      gap: 20px;
    }

    .input-group {
      position: relative;
    }

    .input-group label {
      display: block;
      color: #4a5568;
      font-size: 14px;
      font-weight: 600;
      margin-bottom: 8px;
    }

    input[type="text"],
    input[type="email"],
    input[type="password"],
    input[type="tel"] {
      width: 100%;
      padding: 14px 16px;
      border: 2px solid #e2e8f0;
      border-radius: 12px;
      font-size: 15px;
      transition: all 0.3s ease;
      background: white;
    }

    input[type="text"]:focus,
    input[type="email"]:focus,
    input[type="password"]:focus,
    input[type="tel"]:focus {
      outline: none;
      border-color: #667eea;
      box-shadow: 0 0 0 4px rgba(102, 126, 234, 0.1);
    }

    input::placeholder {
      color: #a0aec0;
    }

    input[type="submit"] {
      width: 100%;
      padding: 16px;
      background: black;
      color: white;
      border: none;
      border-radius: 12px;
      font-size: 16px;
      font-weight: 600;
      cursor: pointer;
      transition: all 0.3s ease;
      margin-top: 10px;
    }

    input[type="submit"]:hover {
      transform: translateY(-2px);
      box-shadow: 0 10px 25px rgba(102, 126, 234, 0.4);
    }

    input[type="submit"]:active {
      transform: translateY(0);
    }

    .footer-text {
      text-align: center;
      margin-top: 24px;
      color:black;
      font-size: 14px;
    }

    .footer-text a {
      color:black;
      text-decoration: none;
      font-weight: 600;
      transition: color 0.3s ease;
    }

    .footer-text a:hover {
      color: #764ba2;
      text-decoration: underline;
    }

    @media (max-width: 480px) {
      .card {
        padding: 30px 24px;
      }

      h2 {
        font-size: 28px;
      }
    }
  </style>
</head>
<body>
  <div class="container">
    <div class="card">
      <h2>Create Account</h2>
      <p class="subtitle">Join us today and get started</p>
      
      <!-- These would be dynamically rendered by your server -->
      <!-- <c:if test="${not empty error}">
        <p class="error">${error}</p>
      </c:if>
      <c:if test="${not empty success}">
        <p class="success">${success}</p>
      </c:if> -->
      
      <form method="post">
        <div class="input-group">
          <label for="username">Username</label>
          <input type="text" id="username" name="username" placeholder="Choose a username" required>
        </div>

        <div class="input-group">
          <label for="email">Email Address</label>
          <input type="email" id="email" name="email" placeholder="your.email@example.com" required>
        </div>

        <div class="input-group">
          <label for="password">Password</label>
          <input type="password" id="password" name="password" placeholder="Choose a secure password" required>
        </div>

        <div class="input-group">
          <label for="phone">Phone Number</label>
          <input type="tel" id="phone" name="phone" placeholder="250 7XXXXXXXX" required>
        </div>

        <input type="submit" value="Create Account">
      </form>

      <div class="footer-text">
        <p>Already have an account? <a href="login">Sign in</a></p>
      </div>
    </div>
  </div>
  <script src="js/script.js"></script>
</body>
</html>