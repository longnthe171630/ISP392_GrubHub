<%-- 
    Document   : restaurant_dashboard
    Created on : Jun 10, 2024, 8:44:09 AM
    Author     : phaml
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.*" %> 
<%@ page import="java.util.*" %> 
<%@ page import="java.text.NumberFormat, java.util.Locale" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Boxicons -->
        <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
        <!-- My CSS -->
        <link rel="stylesheet" href="css\restaurant_dashboard.css">

        <title>Restaurant</title>
    </head>
    <style>
        /* CSS for hover effect and black text color */
        .table-link a {
            color: inherit; /* Sử dụng màu chữ mặc định của theme */
            text-decoration: none; /* Loại bỏ gạch chân mặc định của liên kết */
            transition: color 0.3s; /* Hiệu ứng chuyển đổi màu chữ */
        }

        .table-link a:hover {
            color: black; /* Đổi màu chữ sang đen khi di chuột qua */

        </style>

        <style>
            .hidden {
                display: none;
            }

            .search-container {
                display: flex;
                align-items: center;
                margin-bottom: 20px;
            }

            .search-container input[type="text"] {
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 4px;
                margin-right: 10px;
                flex: 1;
                font-size: 16px;
            }

            .search-container button {
                padding: 10px 20px;
                background-color: #007bff;
                color: white;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                font-size: 16px;
            }

            .search-container button:hover {
                background-color: #0056b3;
            }

            .filter-dropdown {
                display: inline-block;
                margin-left: 20px;
            }

            .filter-dropdown label {
                font-size: 16px;
                margin-right: 10px;
            }

            .filter-dropdown select {
                padding: 10px;
                font-size: 16px;
                border: 1px solid #ccc;
                border-radius: 4px;
            }
            .search-container {
                display: flex;
                align-items: center;
                margin-bottom: 20px;
            }

            .search-container input[type="text"] {
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 4px;
                margin-right: 10px;
                flex: 1;
                font-size: 16px;
            }

            .search-container button {
                padding: 10px 20px;
                background-color: #007bff;
                color: white;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                font-size: 16px;
            }

            .search-container button:hover {
                background-color: #0056b3;
            }

            .filter-dropdown {
                display: inline-block;
                margin-left: 20px;
            }

            .filter-dropdown label {
                font-size: 16px;
                margin-right: 10px;
            }

            .filter-dropdown select {
                padding: 10px;
                font-size: 16px;
                border: 1px solid #ccc;
                border-radius: 4px;
            }
        </style>
        <%!
    // Method to format currency
    public String formatCurrency(double amount) {
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(localeVN);
        return currencyFormatter.format(amount);
    }
        public String getStatusClass(String status) {
        switch (status) {
            case "Đang chờ":
                return "process";
            case "Từ chối":
                return "pending";
            case "Đã giao":
                return "completed";
            default:
                return "";
        }
    }
        %>
        <body>


            <!-- SIDEBAR -->
            <section id="sidebar">
                
                
                <ul class="side-menu top">
                    
                    <li>
                        <a href="restaurantdashboard">
                            <i class='bx bxs-dashboard' ></i>
                            <span class="text">Dashboard</span>
                        </a>
                    </li>
                    <li>
                        <a href="productofrestaurant">
                            <i class='bx bxs-shopping-bag-alt' ></i>
                            <span class="text">Product</span>
                        </a>
                    </li>
                    <li class="active">
                        <a href="#">
                            <i class='bx bxs-doughnut-chart' ></i>
                            <span class="text">History</span>
                        </a>
                    </li>
                    <li>
                        <a href="revenue">
                            <i class='bx bxs-message-dots' ></i>
                            <span class="text">Revenue</span>
                        </a>
                    </li>
                    <li>
                        <a href="restaurantfeedback">
                            <i class='bx bxs-group' ></i>
                            <span class="text">Feedback</span>
                        </a>
                    </li>
                    <li>
                        <a href="loadres" id="edit">
                            <i class='bx bxs-group' ></i>
                            <span class="text">Edit Restaurant</span>
                        </a>
                    </li>
                </ul>
                <ul class="side-menu">
                    <li>
                        <a href="#" class="logout">
                            <i class='bx bxs-log-out-circle' ></i>
                            <span class="text">Logout</span>
                        </a>
                    </li>
                </ul>
            </section>
            <!-- SIDEBAR -->



            <!-- CONTENT -->
            <section id="content">
                <!-- NAVBAR -->
<!--                <nav>
                    <i class='bx bx-menu' ></i>
                    <a href="#" class="nav-link">Categories</a>
                    <form action="#">
                        <div class="form-input">
                            <input type="search" placeholder="Search...">
                            <button type="submit" class="search-btn"><i class='bx bx-search' ></i></button>
                        </div>
                    </form>
                    <input type="checkbox" id="switch-mode" hidden>
                    <label for="switch-mode" class="switch-mode"></label>
                    <a href="#" class="notification">
                        <i class='bx bxs-bell' ></i>
                        <span class="num">8</span>
                    </a>
                    <a href="#" class="profile">
                        <img src="img/people.png">
                    </a>
                </nav>-->
                <!-- NAVBAR -->

                <!-- MAIN -->
                <main>
                    <div class="head-title">
                        <div class="left">
                            <h1>History</h1>
                            <ul class="breadcrumb">
                                <li>
                                    <a href="#">Dashboard</a>
                                </li>
                                <li><i class='bx bx-chevron-right' ></i></li>
                                <li>
                                    <a class="active" href="#">History</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="table-data">

                        <div class="order">
                            <div class="head">
                                <h3>Order</h3>
                                <i class='bx bx-search' onclick="toggleSearchContainer()"></i>
                                <i class='bx bx-filter' onclick="toggleFilterDropdown()"></i>
                            </div>
                            <div id="searchContainer" class="search-container hidden">
                                <input type="text" id="searchInput" onkeyup="searchOrders()" placeholder="Search for orders...">
                                <!--<button type="button" onclick="searchOrders()">Search</button>-->
                            </div>
                            <div id="filterDropdown" class="filter-dropdown hidden">
                                <label for="filterStatus">Filter by Status:</label>
                                <select id="filterStatus" onchange="filterOrders(this.value)">
                                    <option value="All">All</option>
                                    <option value="Đang chờ">Đang chờ</option>
                                    <option value="Từ chối">Từ chối</option>
                                    <option value="Đã giao">Đã giao</option>
                                </select>
                            </div>
                            <div class="head">
                                <!-- Search and filter dropdown section omitted for brevity -->

                                <!-- Orders table -->
                                <table>
                                    <thead>
                                        <tr>
                                            <th>User</th>
                                            <th>Amount</th>
                                            <th>Date Order</th>
                                            <th>Status</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <!-- Iterate through customerOrderMap to display orders -->
                                        <% 
                                        Map<Customer, Order> customerOrderMap = (Map<Customer, Order>) request.getAttribute("MapCusOrder");
                                        if (customerOrderMap != null && !customerOrderMap.isEmpty()) {
                                            for (Map.Entry<Customer, Order> entry : customerOrderMap.entrySet()) {
                                                Customer customer = entry.getKey();
                                                Order order = entry.getValue();
                                        %>
                                        <tr>
                                            <td class="table-link">
                                                <a href="detailofhistory?idCustomer=<%= customer.getId() %>&idOrder=<%= order.getId() %>">
                                                    <%= customer.getName() %>
                                                </a>
                                            </td>
                                            <td class="table-link">
                                                <a href="detailofhistory?idCustomer=<%= customer.getId() %>&idOrder=<%= order.getId() %>">
                                                    <%= formatCurrency(order.getTotal_amount()) %>
                                                </a>
                                            </td>
                                            <td class="table-link">
                                                <a href="detailofhistory?idCustomer=<%= customer.getId() %>&idOrder=<%= order.getId() %>">
                                                    <%= order.getOrder_date() %>
                                                </a>
                                            </td>
                                            <td>
                                                <%-- Display status with appropriate CSS class based on status --%>
                                                <span class="status <%= getStatusClass(order.getStatus()) %>"><%= order.getStatus() %></span>
                                            </td>
                                        </tr>
                                        <% 
                                            }
                                        } else {
                                        %>
                                        <tr>
                                            <td colspan="4">Không có đơn hàng đang xử lý.</td>
                                        </tr>
                                        <% 
                                        } 
                                        %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                </main>
                <!-- MAIN -->
            </section>
            <!-- CONTENT -->


            <script src="js\restaurant_dashboard.js"></script>
        </body>
        <script>
                                    function filterOrders(status) {
                                        const rows = document.querySelectorAll('.order table tbody tr');

                                        rows.forEach(row => {
                                            const statusCell = row.querySelector('td:nth-child(4)');
                                            const rowStatus = statusCell.textContent.trim();

                                            if (status === 'All' || rowStatus === status) {
                                                row.style.display = '';
                                            } else {
                                                row.style.display = 'none';
                                            }
                                        });
                                    }

                                    function getStatusClass(status) {
                                        switch (status) {
                                            case 'Đang chờ':
                                                return 'process';
                                            case 'Từ chối':
                                                return 'pending';
                                            case 'Đã giao':
                                                return 'completed';
                                            default:
                                                return '';
                                        }
                                    }
        </script>

        <script>
            function searchOrders() {
                var input = document.getElementById("searchInput").value.toLowerCase();
                var tableRows = document.getElementsByTagName("tr");

                for (var i = 0; i < tableRows.length; i++) {
                    var td = tableRows[i].getElementsByTagName("td")[0];
                    if (td) {
                        var txtValue = td.textContent || td.innerText;
                        if (txtValue.toLowerCase().indexOf(input) > -1) {
                            tableRows[i].style.display = "";
                        } else {
                            tableRows[i].style.display = "none";
                        }
                    }
                }
            }
        </script>
    </html>
