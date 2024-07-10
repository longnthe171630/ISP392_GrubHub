<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- Boxicons -->
        <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
        <!-- My CSS -->
        <link rel="stylesheet" href="css/style_ship.css">
        <!-- Thư viện SweetAlert -->
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels"></script>

        <title>Delivery Dashboard</title>
    </head>
    <body>

        <!-- SIDEBAR -->
        <section id="sidebar">
            <a href="home" class="brand">
                <i class='bx bxs-food-menu'></i>
                <span class="text">Grubhub</span>
            </a>
            <ul class="side-menu top">
                <li class="active">
                    <a href="deliverydashboard">
                        <i class='bx bxs-dashboard'></i>
                        <span class="text">Dashboard</span>
                    </a>
                </li>
                <li>
                    <a href="deliveryorder">
                        <i class='bx bxs-shopping-bag-alt'></i>
                        <span class="text">Order</span>
                    </a>
                </li>
                <li>
                    <a href="deliveryhistory">
                        <i class='bx bxs-doughnut-chart' ></i>
                        <span class="text">History</span>
                    </a>
                </li>
                <li>
                    <a href="deliverynotice">
                        <i class='bx bxs-message-dots' ></i>
                        <span class="text">Message</span>
                    </a>
                </li>
                <li>
                    <a href="deliveryanalysis">
                        <i class="bx bxs-data"></i>
                        <span class="text">Analysis</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class='bx bxs-group' ></i>
                        <span class="text">Help</span>
                    </a>
                </li>
            </ul>
        </section>
        <!-- SIDEBAR -->


        <!-- CONTENT -->
        <section id="content">
            <!-- NAVBAR -->
            <nav>
                <i class='bx bx-menu' ></i>
                <a href="#" class="nav-link">Categories</a>
                <form action="#">
                    <div class="form-input">
                        <input type="search" placeholder="Search...">
                        <button type="submit" class="search-btn"><i class='bx bx-search' ></i></button>
                    </div>
                </form>
                <input type="checkbox" id="switch-mode" hidden>
                <div class="dropdown-container">
                    <div class="notification">
                        <i class='bx bxs-bell' onclick="toggleDropdown('dropdown3')"></i>
                        <span class="num">0</span>
                    </div>
                    <div id="dropdown3" class="dropdown-content">
                        <div class="notification-header">
                            <h4>Notifications</h4> <button id="markAllAsRead" >Mark all as read</button>
                        </div>
                        <div class="notification-body">
                            <c:choose>
                                <c:when test="${empty notice}">
                                    <div class="no-notifications">You don't have any notification!</div>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach var="n" items="${notice}">
                                        <div class="notification-item" data-id="${n.id}">
                                            <img src="${n.image}" alt="Ex" class="notification-avatar">
                                            <span class="notification-content">${n.descripsion}</span>
                                            <span class="notification-time" data-time="${n.notice_time}"></span>
                                        </div>
                                    </c:forEach>
                                    <div class="notification-footer">
                                        <a href="deliverynotice">See All</a>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>


                <div class="dropdown-container">
                    <img src="images/icon/avatar1.jpg" alt="Avatar" class="avatar" onclick="toggleDropdown('dropdown1')">
                    <div id="dropdown1" class="dropdown-content-1">
                        <a href="Showinfo.jsp">Profile</a>
                        <a href="settings">Setting</a>
                        <a id="logoutButton" href="logout">Logout</a>
                    </div>
                </div>
            </nav>
            <!-- NAVBAR -->

            <!-- MAIN -->
            <main>
                <div class="head-title">
                    <div class="left">
                        <h1>Delivery Analysis</h1>
                        <ul class="breadcrumb">
                            <li>
                                <a href="home">Home</a>
                            </li>
                            <li><i class='bx bx-chevron-right' ></i></li>
                            <li>
                                <a class="active" href="deliveryanalysis">Analysis</a>
                            </li>
                        </ul>
                    </div>
                    <a href="#" class="btn-download">
                        <i class='bx bxs-cloud-download' ></i>
                        <span class="text">Download PDF</span>
                    </a>
                </div>


                <!--Bên dưới là phần chính, hiển thị đơn hàng đang thực hiện-->
                <div class="table-data">
                    <div class="order">
                        <div class="head">
                            <h3>Analysis Board</h3>
                        </div>
                        <div id="notice" class="notice">${err}</div>
                        <table>
                            <thead>
                                <tr>
                                    <th>Status</th>
                                    <th>Quantity</th>
                                    <th>Total Time</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr onclick="openModal('success');">
                                    <td>Delivered</td>
                                    <td>${totaldone}</td>
                                    <td>${time1} minutes</td>
                                </tr>
                                <tr onclick="openModal('failure');">
                                    <td>Undelivered</td>
                                    <td>${totalcancel}</td>
                                    <td>${time2} minutes</td>
                                </tr>
                                <tr class="total-orders">
                                    <td>Total Orders</td>
                                    <td>${totalcancel + totaldone}</td>
                                    <td>${time1 + time2} minutes</td>
                                </tr>
                            </tbody>
                        </table>
                        <div id="myModal" class="modal">
                            <div class="modal-content">
                                <span class="close" onclick="closeModal();">&times;</span>
                                <div id="modalContent">
                                    <!-- Nội dung chi tiết đơn hàng sẽ được tải vào đây -->
                                </div>
                            </div>
                        </div>
                        <div class="summary-container">
                            <h4>Summary</h4>
                            <p><strong>Your average time for successful delivery is:  </strong>${avgSuccessfulDelivery} minutes</p>
                            <p><strong>Your average time for failure delivery is:  </strong>${avgFailureDelivery} minutes</p>
                            <p><strong>The average time of Total order: </strong>${avgTotalOrder} minutes</p>
                            <p><strong>Fastest delivery time: </strong> 1</p>
                            <p><strong>Slowest delivery time: </strong> 1</p>
                        </div>
                    </div>
                    <div class="todo">   
                        <div class="head">
                            <form action="deliveryanalysis" method = "GET">
                                <div class="head">
                                    <h3>Chart</h3>
                                    <div class="dropdown-container">
                                        <i class='bx bx-filter' onclick="toggleDropdown('dropdown2')"></i>
                                        <div id="dropdown2" class="dropdown-content-1" style = "margin-top: -25px; margin-right: -30px;">
                                            <a href="?sort=false">Delivery</a>
                                            <a href="?sort=true">Time</a>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="programming-stats">
                            <canvas class="my-chart"></canvas>
                            <div id="no-data-message" class="no-data-message">No any data!</div>
                            <div class="details">
                                <ul>

                                </ul>
                            </div>
                        </div>
                        <div class="chart-legend">
                            <ul>
                                <li>
                                    <span class="legend-color" style="background-color: #7CB342"></span>
                                    <span>Delivered</span>
                                </li>
                                <li>
                                    <span class="legend-color" style="background-color: #C62828"></span>
                                    <span>Undelivered</span>
                                </li>
                            </ul>
                        </div>
                    </div>

                </div>
            </main>
            <!-- MAIN -->
        </section>
        <!-- CONTENT -->
        <script src="js/delivery.js"></script>

        <script>
                                            document.addEventListener("DOMContentLoaded", function () {
                                                const chartData = {
                                                    labels: ["Delivered", "Undelivered"],
                                                    data: [${totaldone}, ${totalcancel}],
                                                };

                                                const myChart = document.querySelector(".my-chart");
                                                const ul = document.querySelector(".programming-stats .details ul");
                                                const noDataMessage = document.getElementById('no-data-message');

                                                if (myChart && ul) {
                                                    const hasData = chartData.data.some(value => value > 0);

                                                    if (hasData) {
                                                        new Chart(myChart, {
                                                            type: "doughnut",
                                                            data: {
                                                                labels: chartData.labels,
                                                                datasets: [
                                                                    {
                                                                        label: "Order Status",
                                                                        data: chartData.data,
                                                                        backgroundColor: [
                                                                            '#7CB342',
                                                                            '#C62828',
                                                                        ],
                                                                        borderColor: [
                                                                            '#7CB342',
                                                                            '#C62828',
                                                                        ],
                                                                        borderWidth: 1,
                                                                    },
                                                                ],
                                                            },
                                                            options: {
                                                                borderWidth: 10,
                                                                borderRadius: 2,
                                                                hoverBorderWidth: 0,
                                                                plugins: {
                                                                    legend: {
                                                                        display: false,
                                                                    },
                                                                    datalabels: {
                                                                        formatter: (value, context) => {
                                                                            const total = context.chart.data.datasets[0].data.reduce((a, b) => a + b, 0);
                                                                            const percentage = ((value / total) * 100).toFixed(1) + "%";
                                                                            return percentage;
                                                                        },
                                                                        color: 'black',
                                                                    },
                                                                },
                                                            },
                                                            plugins: [ChartDataLabels],
                                                        });

                                                        myChart.style.display = 'block';
                                                        noDataMessage.style.display = 'none';
                                                    } else {
                                                        myChart.style.display = 'none';
                                                        noDataMessage.style.display = 'block';
                                                    }
                                                } else {
                                                    console.error("Cannot find the chart or list elements.");
                                                }
                                            });

        </script>
        <script>
            // Hàm mở modal
            function openModal(status) {
                fetch('deliveryanalysis', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded',
                    },
                    body: 'status=' + encodeURIComponent(status)
                })
                        .then(response => response.text())
                        .then(html => {
                            document.getElementById('modalContent').innerHTML = html;
                            var modal = document.getElementById("myModal");
                            modal.style.display = "block";
                        })
                        .catch(error => console.error('Error:', error));
            }

            // Hàm đóng modal
            function closeModal() {
                const modal = document.getElementById("myModal");
                modal.style.display = "none";
            }
        </script>
    </body>
</html>