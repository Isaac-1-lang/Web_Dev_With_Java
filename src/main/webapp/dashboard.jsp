<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix='c' %>
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
      <!-- Table displaying the data from customer table -->
       <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Order_Id</th>
            <th>Action</th>
          </tr>
          <tbody>
            <c:forEach var="c" items="${customers}"
              <tr>
                <td>${c.id}</td>
                <td>${c.fullname}</td>
                <td>${c.order_id}</td>
                <td><a href="edit?id=${c.id}">Edit</a> | <a href="delete?id=${c.id}">Delete</a></td>
              </tr>
            </c:forEach>
          </tbody>
        </thead>
       </table>
    </div>
  </main>
  <script src="js/script.js"></script>
</body>

</html>