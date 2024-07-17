
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %> 
<%@ page import="model.*" %> 
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Locale" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Boxicons -->
        <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
        <!-- My CSS -->
        <link rel="stylesheet" href="css/restaurant_dashboard.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>

        <title>Restaurant</title>
    </head>

    <body>
        <!-- SIDEBAR -->
        <section id="sidebar">
            <a href="#" class="brand">
                <i class='bx bxs-smile'></i>
                <span class="text">Product</span>
            </a>
            <ul class="side-menu top">
                <li>
                    <a href="restaurantdashboard">
                        <i class='bx bxs-dashboard'></i>
                        <span class="text">Dashboard</span>
                    </a>
                </li>
                <li>
                    <a href="productofrestaurant">
                        <i class='bx bxs-shopping-bag-alt'></i>
                        <span class="text">Product</span>
                    </a>
                </li>
                <li >
                    <a href="historyrestaurant">
                        <i class='bx bxs-doughnut-chart'></i>
                        <span class="text">History</span>
                    </a>
                </li>
                <li class="active">
                    <a href="#">
                        <i class='bx bxs-message-dots'></i>
                        <span class="text">Revenue</span>
                    </a>
                </li>
                <li>
                    <a href="restaurantfeedback">
                        <i class='bx bxs-group'></i>
                        <span class="text">Feedback</span>
                    </a>
                </li>
                <li>
                    <a href="loadres" id="edit">
                        <i class='bx bxs-group'></i>
                        <span class="text">Edit Restaurant</span>
                    </a>
                </li>
            </ul>
            <ul class="side-menu">
                <li>
                    <a href="#" class="logout">
                        <i class='bx bxs-log-out-circle'></i>
                        <span class="text">Logout</span>
                    </a>
                </li>
            </ul>
        </section>
        <!-- SIDEBAR -->

        <!-- CONTENT -->
        <section id="content">
            <!-- NAVBAR -->
            <!--            <nav>
                            <i class='bx bx-menu'></i>
                            <a href="#" class="nav-link">Categories</a>
                            <form action="#">
                                <div class="form-input">
                                    <input type="search" placeholder="Search...">
                                    <button type="submit" class="search-btn"><i class='bx bx-search'></i></button>
                                </div>
                            </form>
                            <input type="checkbox" id="switch-mode" hidden>
                            <label for="switch-mode" class="switch-mode"></label>
                            <a href="#" class="notification">
                                <i class='bx bxs-bell'></i>
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
                        <h1>Dashboard</h1>
                        <ul class="breadcrumb">
                            <li>
                                <a href="#">Dashboard</a>
                            </li>
                            <li><i class='bx bx-chevron-right'></i></li>
                            <li>
                                <a class="active" href="#">Revenue</a>
                            </li>
                        </ul>
                    </div>
                </div>

                <!-- Display error message if any -->
                <% if (request.getAttribute("errorMessage") != null) { %>
                <div class="error-message">
                    <%= request.getAttribute("errorMessage") %>
                </div>
                <% } %>

                <!-- Form to select month range and year -->
                <table>
                    <tbody>
                        <tr>
                            <td class="form-container table-cell-content">
                                <!-- Form -->
                                <form action="revenue" method="get" class="search-container">
                                    <label for="startMonth">Start Month:</label>
                                    <select id="startMonth" name="startMonth">
                                        <% for (int i = 1; i <= 12; i++) { %>
                                        <option value="<%= i %>" <% if (request.getAttribute("startMonth") != null && Integer.parseInt(request.getAttribute("startMonth").toString()) == i) { %> selected <% } %>><%= i %></option>
                                        <% } %>
                                    </select>

                                    <label for="endMonth">End Month:</label>
                                    <select id="endMonth" name="endMonth">
                                        <% for (int i = 1; i <= 12; i++) { %>
                                        <option value="<%= i %>" <% if (request.getAttribute("endMonth") != null && Integer.parseInt(request.getAttribute("endMonth").toString()) == i) { %> selected <% } %>><%= i %></option>
                                        <% } %>
                                    </select>

                                    <label for="year">Year:</label>
                                    <select id="year" name="year">
                                        <% int currentYear = Calendar.getInstance().get(Calendar.YEAR); %>
                                        <% for (int i = currentYear; i >= currentYear - 10; i--) { %>
                                        <option value="<%= i %>" <% if (request.getAttribute("year") != null && Integer.parseInt(request.getAttribute("year").toString()) == i) { %> selected <% } %>><%= i %></option>
                                        <% } %>
                                    </select>

                                    <button type="submit">Get Revenue</button>
                                </form>
                                <!-- Chart -->
                                <div class="chart-container">
                                    <canvas id="salerevenue"></canvas>
                                </div>
                            </td>
                            <td>

                                <%
                                Object listOrderObj = request.getAttribute("listOrder");
                                if (listOrderObj != null && listOrderObj instanceof Map) {
                                    Map<Order, List<OrderDetails>> listOrder = (Map<Order, List<OrderDetails>>) listOrderObj;
                                    int totalProductsSold = 0;
                                    double totalRevenue = 0;
                                    Set<Integer> uniqueCustomerIds = new HashSet<>();

                                    // Calculate total products sold and unique customer ids
                                    for (Map.Entry<Order, List<OrderDetails>> entry : listOrder.entrySet()) {
                                        List<OrderDetails> orderDetailsList = entry.getValue();
                                        totalProductsSold += orderDetailsList.stream().mapToInt(OrderDetails::getQuantity).sum();
                                        totalRevenue += entry.getKey().getTotal_amount();
                                        uniqueCustomerIds.add(entry.getKey().getCustomer_id());
                                    }

                                    int totalCustomers = uniqueCustomerIds.size();

                                    // Format totalRevenue to VND
                                    Locale locale = new Locale("vi", "VN");
                                    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
                                    String formattedTotalRevenue = currencyFormatter.format(totalRevenue);
                                %>
                                <h3>Total orders: <%= listOrder.size() %></h3>
                                <h3>Total products sold: <%= totalProductsSold %></h3>
                                <h3>Total revenue: <%= formattedTotalRevenue %></h3>
                                <h3>Total customers: <%= totalCustomers %></h3>
                                <% } %>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">


                            </td>
                        </tr>
                    </tbody>
                </table>



            </main>
            <!-- MAIN -->
        </section>
        <!-- CONTENT -->

        <!-- JavaScript for Chart.js -->
        <script>
            // Prepare data for Chart.js
            const revenueMap = <% 
                Map<String, Integer> revenueMap = (Map<String, Integer>) request.getAttribute("revenueMap");
                if (revenueMap != null) {
                    out.print("{");
                    int i = 0;
                    for (Map.Entry<String, Integer> entry : revenueMap.entrySet()) {
                        if (i > 0) out.print(", ");
                        out.print("\"" + entry.getKey() + "\": " + entry.getValue());
                        i++;
                    }
                    out.print("}");
                } else {
                    out.print("{}");
                }
            %>;

            // Convert JavaScript object to arrays for labels and data
            const labels = Object.keys(revenueMap);
            const data = Object.values(revenueMap);

            // Thêm sự kiện onload để khởi tạo biểu đồ khi tài liệu được tải
            document.addEventListener('DOMContentLoaded', function () {
                // Lấy context của canvas biểu đồ
                var chart = document.getElementById('salerevenue').getContext('2d');

                // Tạo biểu đồ mới từ Chart.js
                var myChart = new Chart(chart, {
                    type: 'line',
                    data: {
                        labels: labels,
                        datasets: [{
                                label: 'Revenue (VND)',
                                backgroundColor: 'rgba(0, 123, 255, 0.5)',
                                borderColor: '#007bff',
                                data: data,
                                fill: false,
                                tension: 0.3,
                                pointRadius: 5,
                                pointHoverRadius: 7
                            }]
                    },
                    options: {
                        scales: {
                            yAxes: [{
                                    ticks: {
                                        beginAtZero: true,
                                        callback: function (value, index, values) {
                                            return value.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'});
                                        }
                                    },
                                    scaleLabel: {
                                        display: true,
                                        labelString: 'Revenue (VND)'
                                    }
                                }],
                            xAxes: [{
                                    scaleLabel: {
                                        display: true,
                                        labelString: 'Month'
                                    }
                                }]
                        },
                        elements: {
                            line: {
                                borderWidth: 2
                            }
                        },
                        // Sự kiện onClick để xử lý khi người dùng click vào biểu đồ
                        onClick: function (event, activeElements) {
                            if (activeElements && activeElements.length > 0) {
                                var clickedIndex = activeElements[0]._index;
                                var selectedMonth = labels[clickedIndex];
                                var selectedYear = document.getElementById('year').value;
                                window.location.href = 'revenueofmonth?month=' + selectedMonth + '&year=' + selectedYear;
                            }
                        }
                    }
                });
            });
        </script>
        <style>
            .error-message {
                color: #ffcccc;
                /*font-weight: bold;*/
            }
            .chart-container {
                width: 100%;
                max-width: 600px; /* Adjust as needed */
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }

            th, td {
                border: 1px solid #ccc;
                padding: 10px;
                text-align: left;
            }

            .form-container {
                padding: 20px;
            }
            table, th, td {
                border: none;
            }
            .table-cell-content {
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
                text-align: center; /* căn giữa nội dung theo chiều ngang */
                height: 100%; /* chiều cao 100% để nội dung căn giữa */
            }

        </style>
    </body>
</html>
