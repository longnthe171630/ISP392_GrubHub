<%-- 
    Document   : restaurant_dashboard
    Created on : Jun 10, 2024, 8:44:09 AM
    Author     : phaml
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %> 
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
        <script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.17.3/xlsx.full.min.js"></script>

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
                <li class="active">
                    <a href="#">
                        <i class='bx bxs-doughnut-chart'></i>
                        <span class="text">History</span>
                    </a>
                </li>
                <li>
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
            <nav>
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
            </nav>
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

                <!-- Form to select month range and year -->
                <form action="revenue" method="get" class="search-container">
                    <label for="startMonth">Start Month:</label>
                    <select id="startMonth" name="startMonth">
                        <% for (int i = 1; i <= 12; i++) { %>
                        <option value="<%= i %>"><%= i %></option>
                        <% } %>
                    </select>

                    <label for="endMonth">End Month:</label>
                    <select id="endMonth" name="endMonth">
                        <% for (int i = 1; i <= 12; i++) { %>
                        <option value="<%= i %>"><%= i %></option>
                        <% } %>
                    </select>

                    <label for="year">Year:</label>
                    <select id="year" name="year">
                        <% int currentYear = Calendar.getInstance().get(Calendar.YEAR); %>
                        <% for (int i = currentYear; i >= currentYear - 10; i--) { %>
                        <option value="<%= i %>"><%= i %></option>
                        <% } %>
                    </select>

                    <button type="submit">Get Revenue</button>
                </form>
                <button onclick="exportToExcel()">Export to Excel</button>

                <!-- Display selected period -->
                <div id="selected-period">
                    <% String startMonth = request.getParameter("startMonth");
                       String endMonth = request.getParameter("endMonth");
                       String year = request.getParameter("year");
                       if (startMonth != null && endMonth != null && year != null) { %>
                    <p>Selected Period: <%= startMonth %> - <%= endMonth %> <%= year %></p>
                    <% } else { %>
                    <p>Default Period: Last 6 Months</p>
                    <% } %>
                </div>

                <!-- Chart.js canvas for revenue -->
                <canvas id="salerevenue"></canvas>
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

            // Create chart
            new Chart(document.getElementById("salerevenue"), {
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
                    }
                }
            });
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
        <script>
            function exportToExcel() {
                const revenueMap = <%= request.getAttribute("revenueMap") %>;

                const data = Object.keys(revenueMap).map(month => ({
                        Month: month,
                        Revenue: revenueMap[month]
                    }));

                const ws = XLSX.utils.json_to_sheet(data);
                const wb = XLSX.utils.book_new();
                XLSX.utils.book_append_sheet(wb, ws, "Revenue Data");

                const wbout = XLSX.write(wb, {bookType: 'xlsx', type: 'array'});

                const fileName = 'revenue_data.xlsx';
                const blob = new Blob([wbout], {type: 'application/octet-stream'});

                if (navigator.msSaveBlob) { // For IE 10+
                    navigator.msSaveBlob(blob, fileName);
                } else {
                    const link = document.createElement('a');
                    link.href = URL.createObjectURL(blob);
                    link.setAttribute('download', fileName);
                    document.body.appendChild(link);
                    link.click();
                    document.body.removeChild(link);
                }
            }
        </script>

    </body>
</html>
