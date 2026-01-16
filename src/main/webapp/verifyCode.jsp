<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix='c' %>
<html>
<head>
  <title>Verify Email</title>
  <link rel="stylesheet" href="css/style.css">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <style>
    .verification-container {
      max-width: 500px;
      margin: 60px auto;
      padding: 40px;
      background: #ffffff;
      border-radius: 12px;
      box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1);
    }

    .verification-container h2 {
      margin-top: 0;
      margin-bottom: 10px;
      color: #1e293b;
      text-align: center;
    }

    .verification-container p {
      color: #64748b;
      text-align: center;
      margin-bottom: 30px;
    }

    .form-group {
      margin-bottom: 20px;
    }

    .form-group label {
      display: block;
      margin-bottom: 8px;
      font-weight: 600;
      color: #334155;
    }

    .form-group input {
      width: 100%;
      padding: 14px;
      border: 2px solid #e5e7eb;
      border-radius: 8px;
      font-size: 16px;
      box-sizing: border-box;
      text-align: center;
      letter-spacing: 8px;
      font-weight: 600;
      font-size: 24px;
    }

    .form-group input:focus {
      outline: none;
      border-color: #2563eb;
      box-shadow: 0 0 0 3px rgba(37, 99, 235, 0.1);
    }

    .btn {
      width: 100%;
      padding: 14px;
      border-radius: 8px;
      font-size: 16px;
      font-weight: 600;
      text-decoration: none;
      transition: all 0.2s ease;
      border: none;
      cursor: pointer;
      display: block;
      text-align: center;
      background: #2563eb;
      color: #ffffff;
    }

    .btn:hover {
      background: #1d4ed8;
    }

    .alert {
      padding: 12px 16px;
      border-radius: 8px;
      margin-bottom: 20px;
    }

    .alert-error {
      background: #fee2e2;
      color: #991b1b;
      border: 1px solid #fecaca;
    }

    .alert-warning {
      background: #fef3c7;
      color: #92400e;
      border: 1px solid #fde68a;
    }

    .alert-success {
      background: #d1fae5;
      color: #065f46;
      border: 1px solid #a7f3d0;
    }

    .email-display {
      background: #f1f5f9;
      padding: 12px;
      border-radius: 6px;
      text-align: center;
      margin-bottom: 20px;
      color: #475569;
      font-weight: 500;
    }

    .resend-link {
      text-align: center;
      margin-top: 20px;
      color: #64748b;
    }

    .resend-link a {
      color: #2563eb;
      text-decoration: none;
      font-weight: 600;
    }

    .resend-link a:hover {
      text-decoration: underline;
    }
  </style>
</head>

<body class="dashboard">
  <div class="verification-container">
    <h2>Verify Your Email</h2>
    <p>We've sent a 6-digit verification code to your email address.</p>

    <c:if test="${not empty email}">
      <div class="email-display">
        ${email}
      </div>
    </c:if>

    <c:if test="${not empty error}">
      <div class="alert alert-error">${error}</div>
    </c:if>

    <c:if test="${not empty warning}">
      <div class="alert alert-warning">${warning}</div>
    </c:if>

    <c:if test="${not empty success}">
      <div class="alert alert-success">${success}</div>
    </c:if>

    <form method="POST" action="verifyCode">
      <input type="hidden" name="email" value="${param.email != null ? param.email : email}">
      
      <div class="form-group">
        <label for="code">Enter Verification Code</label>
        <input type="text" 
               id="code" 
               name="code" 
               maxlength="6" 
               pattern="[0-9]{6}" 
               placeholder="000000" 
               required 
               autocomplete="off"
               autofocus>
      </div>

      <button type="submit" class="btn">Verify Email</button>
    </form>

    <div class="resend-link">
      <p>Didn't receive the code? <a href="register">Register again</a> or <a href="login">Go to Login</a></p>
    </div>
  </div>

  <script>
    // Auto-format code input (only numbers, max 6 digits)
    document.getElementById('code').addEventListener('input', function(e) {
      this.value = this.value.replace(/[^0-9]/g, '').slice(0, 6);
    });

    // Auto-submit when 6 digits are entered (optional)
    document.getElementById('code').addEventListener('input', function(e) {
      if (this.value.length === 6) {
        // Optional: auto-submit
        // this.form.submit();
      }
    });
  </script>
</body>
</html>
