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
      <!-- Session Information -->
    </div>
  </main>
  <script src="js/script.js"></script>
</body>

</html>