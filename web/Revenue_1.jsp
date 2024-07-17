<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
        <link rel="stylesheet" href="css/admin.css">
        <title>Monthly Revenue</title>
        <style>
            body {
                margin: 0;
                padding: 0;
                background-color: #fbfbfb;
            }
            @media (min-width: 991.98px) {
                main {
                    padding-left: 240px;
                }
            }
            .sidebar {
                position: fixed;
                top: 0;
                bottom: 0;
                left: 0;
                padding: 58px 0 0;
                box-shadow: 0 2px 5px 0 rgb(0 0 0 / 5%), 0 2px 10px 0 rgb(0 0 0 / 5%);
                width: 240px;
                z-index: 600;
            }
            @media (max-width: 991.98px) {
                .sidebar {
                    width: 100%;
                }
            }
            .sidebar .active {
                border-radius: 5px;
                box-shadow: 0 2px 5px 0 rgb(0 0 0 / 16%), 0 2px 10px 0 rgb(0 0 0 / 12%);
            }
            .sidebar-sticky {
                position: relative;
                top: 0;
                height: calc(100vh - 48px);
                padding-top: 0.5rem;
                overflow-x: hidden;
                overflow-y: auto;
            }
        </style>
    </head>
    <body>
         <section id="sidebar">
            <a href="#" class="brand">
                <i class='bx bxs-smile'></i>
                <span class="text">AdminHub</span>
            </a>
            <ul class="side-menu top">
                <li class="active">
                    <a href="admin?action=home">
                        <i class='bx bxs-dashboard' ></i>
                        <span class="text">Dashboard</span>
                    </a>
                </li>
                <li>
                    <a href="admin?action=cus">
                        <i class='bx bxs-user'></i>
                        <span class="text">Customer Account </span>
                    </a>
                </li>
                <li>
                    <a href="admin?action=res">
                        <i class='bx bx-restaurant'></i>
                        <span class="text">Restaurant Account</span>
                    </a>
                </li>
                <li>
                    <a href="admin?action=deli">
                        <i class='bx bxs-message-dots' ></i>
                        <span class="text">Delivery</span>
                    </a>
                </li>
                <li>
                    <a href="admin?action=feed">
                        <i class='bx bxs-message-dots' ></i>
                        <span class="text">Feedback</span>
                    </a>
                </li>
                <li>
                    <a href="admin?action=ban">
                        <i class='bx bxs-no-entry'></i>
                        <span class="text">Ban</span>
                    </a>
                </li>
                <li>
                    <a href="monthlyrevenue">
                        <i class='bx bxs-dollar-circle' ></i>
                        <span class="text">Monthly Revenue</span>
                    </a>
                </li>

            </ul>
            <ul class="side-menu">
                <li>
                    <a href="#">
                        <i class='bx bxs-cog' ></i>
                        <span class="text">Settings</span>
                    </a>
                </li>
                <li>
                    <a href="home" class="logout">
                        <i class='bx bxs-log-out-circle' ></i>
                        <span class="text">Logout</span>
                    </a>
                </li>
            </ul>
        </section>
        <!-- SIDEBAR -->

        <section id="content">
            <nav>
                <i class='bx bx-menu'></i>
                <form action="DashboardServlet">
                    <div class="form-input">
                        <input type="search" id="search" name="search" placeholder="Search...">
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
                    <img src="">
                </a>
            </nav>
            <main>
                <div class="container pt-4">
                    <section class="mb-4" id="doanhThuThang">
                        <div class="card">
                            <div class="card-header py-3">
                                <h3 class="mb-0 text-center">
                                    <strong>Revenue by month</strong>
                                    <form id="f1" method="get" action="monthlyrevenue">
                                        <select name="year" class="form-control" id="dropdownYear" style="width: 120px;" onchange="getYear(this)">
                                            <option selected value="2024">2024</option>
                                            <option value="2023">2023</option>
                                        </select>
                                    </form>
                                    <button onclick="exportToExcel()">Export to Excel</button>
                                </h3>
                            </div>
                            <div class="card-body">
                                <canvas id="horizontalBar"></canvas>
                            </div>
                        </div>
                    </section>
                </div>
            </main>
        </section>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.3.1/jspdf.umd.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.18.0/xlsx.full.min.js"></script>

        <script>
                                        // Define totalMoneyMonths in the global scope
                                        var totalMoneyMonths = [
            ${requestScope.totalMoneyMonth12},
            ${requestScope.totalMoneyMonth11},
            ${requestScope.totalMoneyMonth10},
            ${requestScope.totalMoneyMonth9},
            ${requestScope.totalMoneyMonth8},
            ${requestScope.totalMoneyMonth7},
            ${requestScope.totalMoneyMonth6},
            ${requestScope.totalMoneyMonth5},
            ${requestScope.totalMoneyMonth4},
            ${requestScope.totalMoneyMonth3},
            ${requestScope.totalMoneyMonth2},
            ${requestScope.totalMoneyMonth1}
                                        ];

                                        $(document).ready(function () {
                                            var ctx = document.getElementById('horizontalBar').getContext('2d');

                                            new Chart(ctx, {
                                                type: 'horizontalBar',
                                                data: {
                                                    labels: ['December', 'November', 'October', 'September', 'August', 'July', 'June', 'May', 'April', 'March', 'February', 'January'],
                                                    datasets: [{
                                                            label: 'Revenue vnđ',
                                                            data: totalMoneyMonths,
                                                            fill: false,
                                                            backgroundColor: [
                                                                'rgba(255, 99, 132, 0.2)', 'rgba(255, 159, 64, 0.2)',
                                                                'rgba(255, 205, 86, 0.2)', 'rgba(75, 192, 192, 0.2)',
                                                                'rgba(54, 162, 235, 0.2)', 'rgba(153, 102, 255, 0.2)',
                                                                'rgba(201, 203, 207, 0.2)', '#99FF99', '#FFFF99',
                                                                '#FFC1C1', '#FFB5C5', '#DDC488'
                                                            ],
                                                            borderColor: [
                                                                'rgb(255, 99, 132)', 'rgb(255, 159, 64)', 'rgb(255, 205, 86)',
                                                                'rgb(75, 192, 192)', 'rgb(54, 162, 235)', 'rgb(153, 102, 255)',
                                                                'rgb(201, 203, 207)', '#66FF99', '#FFFF66',
                                                                '#EEB4B4', '#EEA9B8', '#ECAB53'
                                                            ],
                                                            borderWidth: 1
                                                        }]
                                                },
                                                options: {
                                                    scales: {
                                                        xAxes: [{
                                                                ticks: {
                                                                    beginAtZero: true
                                                                }
                                                            }]
                                                    }
                                                }
                                            });
                                        });

                                        function getYear(obj) {
                                            document.getElementById('f1').submit();
                                        }

                                        function exportToExcel() {
                                            const data = [["Month", "Revenue"]];
                                            totalMoneyMonths.forEach((money, index) => {
                                                const monthName = getMonthName(index);
                                                data.push([monthName, money]);
                                            });
                                            const wb = XLSX.utils.book_new();
                                            const ws = XLSX.utils.aoa_to_sheet(data);
                                            XLSX.utils.book_append_sheet(wb, ws, "Monthly Revenue");
                                            XLSX.writeFile(wb, "monthly_revenue.xlsx");
                                        }

                                        function getMonthName(index) {
                                            const monthNames = [
                                                'January', 'February', 'March', 'April', 'May', 'June',
                                                'July', 'August', 'September', 'October', 'November', 'December'
                                            ];
                                            return monthNames[index];
                                        }
        </script>

        <script src="js/admin.js"></script>
    </body>
</html>
