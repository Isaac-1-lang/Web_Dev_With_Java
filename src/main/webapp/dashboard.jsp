<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix='c' %>
<html>

<head>
  <title>Dashboard</title>
  <link rel="stylesheet" href="css/style.css">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <style>
    /* Table container */
.container {
  max-width: 1100px;
  margin: 40px auto;
  padding: 20px;
}

/* Table */
.data-table {
  width: 100%;
  border-collapse: collapse;
  background: #ffffff;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 10px 25px rgba(0, 0, 0, 0.08);
}

/* Header */
.data-table thead {
  background: #2563eb;
  color: #ffffff;
}

.data-table th {
  padding: 14px 16px;
  text-align: left;
  font-size: 14px;
  letter-spacing: 0.5px;
  text-transform: uppercase;
}

/* Body */
.data-table td {
  padding: 14px 16px;
  font-size: 15px;
  color: #334155;
  border-bottom: 1px solid #e5e7eb;
}

/* Zebra striping */
.data-table tbody tr:nth-child(even) {
  background-color: #f8fafc;
}

/* Hover effect */
.data-table tbody tr:hover {
  background-color: #eef2ff;
  transition: background-color 0.2s ease-in-out;
}

/* Actions */
.actions {
  display: flex;
  gap: 8px;
}

/* Buttons */
.btn {
  padding: 6px 12px;
  border-radius: 6px;
  font-size: 13px;
  font-weight: 600;
  text-decoration: none;
  transition: all 0.2s ease;
}

.btn-edit {
  background: #22c55e;
  color: #ffffff;
}

.btn-edit:hover {
  background: #16a34a;
}

.btn-delete {
  background: #ef4444;
  color: #ffffff;
}

.btn-delete:hover {
  background: #dc2626;
}

/* Responsive table */
@media (max-width: 768px) {
  .data-table thead {
    display: none;
  }

  .data-table,
  .data-table tbody,
  .data-table tr,
  .data-table td {
    display: block;
    width: 100%;
  }

  .data-table tr {
    margin-bottom: 15px;
    border-radius: 8px;
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.05);
  }

  .data-table td {
    display: flex;
    justify-content: space-between;
    padding: 12px;
  }

  .data-table td::before {
    content: attr(data-label);
    font-weight: 600;
    color: #64748b;
  }
}
  </style>
</head>

<body class="dashboard">
  <header class="dashboard-header">
    <h2>Dashboard</h2>
    <a href="logout" class="btn" style="width: auto; margin: 0;">Logout</a>
  </header>

  <!-- <main class="dashboard-content container">
    <div class="card">
      <h3>Welcome, ${sessionScope.user}</h3>
      <p class="mt-4">You have successfully logged in.</p>
      <p>This is your dashboard area where you can manage your settings and view your profile.</p>
      
      <a href="secondDashboard" class="btn">Second Dashboard</a>
    </div>
  </main> -->
  <main>
    <div class="container">
      <!-- Success/Error Messages -->
      <c:if test="${not empty success}">
        <div style="padding: 12px 16px; background: #d1fae5; color: #065f46; border-radius: 6px; margin-bottom: 20px;">
          ${success}
        </div>
      </c:if>
      <c:if test="${not empty error}">
        <div style="padding: 12px 16px; background: #fee2e2; color: #991b1b; border-radius: 6px; margin-bottom: 20px;">
          ${error}
        </div>
      </c:if>

      <!-- Add New Customer Form -->
      <div style="background: #ffffff; padding: 20px; border-radius: 8px; margin-bottom: 30px; box-shadow: 0 4px 10px rgba(0, 0, 0, 0.05);">
        <h3 style="margin-top: 0; margin-bottom: 20px;">Add New Student</h3>
        <form method="POST" action="dashboard" style="display: flex; gap: 10px; align-items: flex-end;">
          <div style="flex: 1;">
            <label for="fullName" style="display: block; margin-bottom: 5px; font-weight: 600; color: #334155;">Full Name</label>
            <input type="text" id="fullName" name="fullName" required 
                   style="width: 100%; padding: 10px; border: 1px solid #e5e7eb; border-radius: 6px; font-size: 15px; box-sizing: border-box;">
          </div>
          <div style="flex: 1;">
            <label for="orderId" style="display: block; margin-bottom: 5px; font-weight: 600; color: #334155;">Order ID</label>
            <input type="number" id="orderId" name="orderId" required 
                   style="width: 100%; padding: 10px; border: 1px solid #e5e7eb; border-radius: 6px; font-size: 15px; box-sizing: border-box;">
          </div>
          <div>
            <button type="submit" class="btn btn-edit" style="padding: 10px 20px; margin: 0;">Add Customer</button>
          </div>
        </form>
      </div>

      <!-- Table displaying the data from customer table -->
      <table class="data-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Class ID</th>
            <th>Actions</th>
          </tr>
        </thead>
      
        <tbody>
          <c:forEach var="c" items="${customers}">
            <tr>
              <td>${c.id}</td>
              <td>${c.fullName}</td>
              <td>${c.orderId}</td>
              <td class="actions">
                <a href="edit?id=${c.id}" class="btn btn-edit">Edit</a>
                <a href="delete?id=${c.id}" class="btn btn-delete">Delete</a>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>      
    </div>
  </main>
  <script src="js/script.js"></script>
</body>

</html>