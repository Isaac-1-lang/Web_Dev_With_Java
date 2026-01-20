<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix='c' %>
<html>
<head>
  <title>Edit Student</title>
  <link rel="stylesheet" href="css/style.css">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <style>
    .form-container {
      max-width: 600px;
      margin: 40px auto;
      padding: 30px;
      background: #ffffff;
      border-radius: 8px;
      box-shadow: 0 10px 25px rgba(0, 0, 0, 0.08);
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
      padding: 12px;
      border: 1px solid #e5e7eb;
      border-radius: 6px;
      font-size: 15px;
      box-sizing: border-box;
    }

    .form-group input:focus {
      outline: none;
      border-color: #2563eb;
      box-shadow: 0 0 0 3px rgba(37, 99, 235, 0.1);
    }

    .btn {
      padding: 12px 24px;
      border-radius: 6px;
      font-size: 15px;
      font-weight: 600;
      text-decoration: none;
      transition: all 0.2s ease;
      border: none;
      cursor: pointer;
      display: inline-block;
    }

    .btn-primary {
      background: #2563eb;
      color: #ffffff;
    }

    .btn-primary:hover {
      background: #1d4ed8;
    }

    .btn-secondary {
      background: #64748b;
      color: #ffffff;
      margin-left: 10px;
    }

    .btn-secondary:hover {
      background: #475569;
    }

    .alert {
      padding: 12px 16px;
      border-radius: 6px;
      margin-bottom: 20px;
    }

    .alert-error {
      background: #fee2e2;
      color: #991b1b;
      border: 1px solid #fecaca;
    }

    .alert-success {
      background: #d1fae5;
      color: #065f46;
      border: 1px solid #a7f3d0;
    }
  </style>
</head>

<body class="dashboard">
  <header class="dashboard-header">
    <h2>Edit Student</h2>
    <a href="dashboard" class="btn" style="width: auto; margin: 0;">Back to Dashboard</a>
  </header>

  <main>
    <div class="form-container">
      <c:if test="${not empty error}">
        <div class="alert alert-error">${error}</div>
      </c:if>
      
      <c:if test="${not empty success}">
        <div class="alert alert-success">${success}</div>
      </c:if>

      <c:if test="${not empty student}">
        <form method="POST" action="edit">
          <input type="hidden" name="id" value="${student.id}">
          
          <div class="form-group">
            <label for="name">Name</label>
            <input type="text" id="name" name="name" value="${student.name}" required>
          </div>

          <div class="form-group">
            <label for="email">Email</label>
            <input type="email" id="email" name="email" value="${student.email}" required>
          </div>

          <div class="form-group">
            <label for="school">School</label>
            <input type="text" id="school" name="school" value="${student.school}" required>
          </div>

          <div class="form-group">
            <label for="dob">Date of Birth</label>
            <input type="date" id="dob" name="dob" value="${student.dob}" required>
          </div>

          <div class="form-group">
            <button type="submit" class="btn btn-primary">Update Student</button>
            <a href="dashboard" class="btn btn-secondary">Cancel</a>
          </div>
        </form>
      </c:if>
    </div>
  </main>
</body>
</html>
