<%--
  Created by Isaac-1-lang
  User: user
  Date: 20/01/2026
  Time: 10:20
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Student Dashboard</title>

    <style>
        /* ===== Global ===== */
        body {
            margin: 0;
            font-family: "Inter", "Segoe UI", sans-serif;
            background: #f1f5f9;
            color: #0f172a;
        }

        h2, h3 {
            margin-left: 140px;
            font-weight: 600;
        }

        /* ===== Page Wrapper ===== */
        .container {
            max-width: 500px;
            margin: 40px auto;
            padding: 20px;
        }

        /* ===== Card ===== */
        .card {
            background: #ffffff;
            padding: 24px;
            border-radius: 12px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.08);
            margin-bottom: 30px;
        }

        .card-header {
            margin-bottom: 20px;
        }

        .card-header h3 {
            font-size: 20px;
            color: #1e293b;
        }

        /* ===== Form ===== */
        .form-grid {
            display: grid;
            gap: 16px;
            align-items: end;
            align-content: center;
        }

        .form-group label {
            display: block;
            margin-bottom: 6px;
            font-size: 14px;
            font-weight: 600;
            color: #334155;
        }

        .form-group input {
            width: 50%;
            padding: 11px 12px;
            border: 1px solid #e5e7eb;
            border-radius: 8px;
            font-size: 15px;
            transition: all 0.2s ease;
            display: block;
            margin: 0 auto;
          }


        .form-group input:focus {
            outline: none;
            border-color: #2563eb;
            box-shadow: 0 0 0 3px rgba(37, 99, 235, 0.15);
        }

        /* ===== Buttons ===== */
        .btn {
            display: inline-flex;
            align-items: center;
            justify-content: center;
            padding: 11px 22px;
            border-radius: 8px;
            font-size: 14px;
            font-weight: 600;
            text-decoration: none;
            border: none;
            cursor: pointer;
            transition: all 0.2s ease;
            width: 50%;
            margin: 0 auto;
            display: block;
        }

        .btn-primary {
            background: #2563eb;
            color: #ffffff;
        }

        .btn-primary:hover {
            background: #1d4ed8;
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

        /* ===== Table ===== */
        .data-table {
            width: 100%;
            border-collapse: collapse;
            background: #ffffff;
            border-radius: 12px;
            overflow: hidden;
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.08);
        }

        .data-table thead {
            background: #2563eb;
            color: #ffffff;
        }

        .data-table th {
            padding: 14px 16px;
            text-align: left;
            font-size: 13px;
            letter-spacing: 0.5px;
            text-transform: uppercase;
        }

        .data-table td {
            padding: 14px 16px;
            font-size: 15px;
            color: #334155;
            border-bottom: 1px solid #e5e7eb;
        }

        .data-table tbody tr:nth-child(even) {
            background: #f8fafc;
        }

        .data-table tbody tr:hover {
            background: #eef2ff;
        }

        .actions {
            display: flex;
            gap: 8px;
        }

        /* ===== Responsive Table ===== */
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
                margin-bottom: 16px;
                border-radius: 10px;
                box-shadow: 0 4px 12px rgba(0, 0, 0, 0.06);
            }

            .data-table td {
                display: flex;
                justify-content: space-between;
                padding: 12px 14px;
            }

            .data-table td::before {
                content: attr(data-label);
                font-weight: 600;
                color: #64748b;
            }
        }
    </style>
</head>

<body>
<div class="container">

    <!-- Add Student Card -->
    <div class="card">
        <div class="card-header">
            <h3>Add New Student</h3>
        </div>

        <form method="POST" action="dashboard" class="form-grid">
            <div class="form-group">
                <input type="text" id="name" name="name" required placeholder="Enter your name">
            </div>

            <div class="form-group">
                <input type="email" id="email" name="email" required placeholder="Enter your email">
            </div>

            <div class="form-group">
                <input type="text" id="school" name="school" required placeholder="Enter your school">
            </div>

            <div class="form-group">
                <input type="date" id="dob" name="dob" required placeholder="Enter your date of birth">
            </div>

            <button type="submit" class="btn btn-primary">
                Add Student
            </button>
        </form>
    </div>

</div>
</body>
</html>
