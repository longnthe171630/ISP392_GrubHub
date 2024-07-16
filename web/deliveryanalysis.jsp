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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <!-- My CSS -->
        <link rel="stylesheet" href="css/style_ship.css">
        <!-- Thư viện SweetAlert -->
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels"></script>
        <script src="https://cdn.jsdelivr.net/npm/chartjs-adapter-date-fns"></script>
        <script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-labels"></script>

        <title>Analysis</title>
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
                        <span class="text">Notification</span>
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
<!--                <a href="#" class="nav-link">Categories</a>-->
                <form action="#">
                    <div class="form-input">
<!--                        <input type="search" placeholder="Search...">
                        <button type="submit" class="search-btn"><i class='bx bx-search' ></i></button>-->
                    </div>
                </form>
                <input type="checkbox" id="switch-mode" hidden>
                <div class="dropdown-container">
                    <div class="notification">
                        <i class='bx bxs-bell' onclick="toggleDropdown('dropdown3')"></i>
                        <span class="num"></span>
                    </div>
                    <div id="dropdown3" class="dropdown-content">
                        <div class="notification-header">
                            <h4>Notifications</h4>
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
                        <a href="Showinfo.jsp"><i class="fas fa-user"></i> Profile</a>
                        <a href="settings"><i class="fas fa-cog"></i> Setting</a>
                        <a id="logoutButton" href="logout"><i class="fas fa-sign-out-alt"></i> Logout</a>
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
<!--                    <a href="#" class="btn-download">
                        <i class='bx bxs-cloud-download' ></i>
                        <span class="text">Download PDF</span>
                    </a>-->
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
                                    <th>Quantity <br>(Order)</th>
                                    <th>Total Time<br> (Minutes)</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr onclick="openModal('success');">
                                    <td>Success</td>
                                    <td>${totaldone}</td>
                                    <td>${time1}</td>
                                </tr>
                                <tr onclick="openModal('failure');">
                                    <td>Failure</td>
                                    <td>${totalcancel}</td>
                                    <td>${time2}</td>
                                </tr>
                                <tr class="total-orders">
                                    <td>Total Orders</td>
                                    <td>${totaldone+totalcancel}</td>
                                    <td>${time1+time2}</td>

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
                        <div class="highlight-box">
                            <span class="highlight-label">The average time for successful delivery:</span>
                            <span class="highlight-value">${avgTimeSuccess} minutes</span>
                        </div>   
                        <div class="highlight-box">
                            <span class="highlight-label">The average time for failure delivery:</span>
                            <span class="highlight-value">${avgTimeFailure} minutes</span>
                        </div>
                        <div class="highlight-box">
                            <span class="highlight-label">The average time to deliver an order:</span>
                            <span class="highlight-value">${avgTimeAll} minutes</span>
                        </div>
                        <div class="highlight-box">
                            <span class="highlight-label">Fastest delivery time:</span>
                            <span class="highlight-value">${maxDeliveryTime} minutes</span>
                        </div>
                        <div class="highlight-box">
                            <span class="highlight-label">Slowest delivery time is:</span>
                            <span class="highlight-value">${minDeliveryTime} minutes</span>
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
                                            <span onclick="showChart('delivery')"><i class="fas fa-truck"></i> Details</span>
                                            <span onclick="showChart('order')"><i class="fas fa-star"></i> Rate</span>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>

                        <div class="date-picker-container">
                            <p class="note1">*The time is set as default, you can change it here!</p>
                            <label for="start-date">From:</label>
                            <input type="date" id="start-date" name="start-date" value = '2024-01-01'>
                            <label for="end-date">To:</label>
                            <input type="date" id="end-date" name="end-date" value = '2024-12-31'>
                            <button id="submit-btn">Search</button>
                        </div>

                        <div class="programming-stats">
                            <canvas class="my-chart"></canvas>
                            <div id="no-data-message" class="no-data-message">No any data!</div>
                            <div class="status-container" id="status-container" style="display: none;">
                                <div class="status success">
                                    <p>Success: ${formattedSuccessRate}%</p>
                                </div>
                                <div class="status failure">
                                    <p>Failure: ${formattedFailureRate}%</p>
                                </div>
                                <p class="note">*Order status summary chart</p>
                            </div>

                        </div>
                        <p class="note" id="delivery" style="display: block;">*Detailed chart of orders received over time</p>
                    </div>
                </div>
            </main>
            <!-- MAIN -->
        </section>
        <!-- CONTENT -->
        <script src="js/delivery.js"></script>

        <script>
                                                document.addEventListener("DOMContentLoaded", function () {
                                                    const myChartElement = document.querySelector(".my-chart");
                                                    const noDataMessage = document.getElementById('no-data-message');
                                                    let myChart;
                                                    let currentChartType = 'line'; // Biến lưu loại biểu đồ hiện tại, mặc định là line

                                                    // Đặt kích thước của canvas
                                                    myChartElement.width = 400; // Thay đổi chiều rộng
                                                    myChartElement.height = 400; // Thay đổi chiều cao

                                                    const chartData1 = {
                                                        labels: ${labels1},
                                                        data: ${data1},
                                                    };
                                                    const chartData2 = {
                                                        labels: ${labels2},
                                                        data: ${data2},
                                                    };

                                                    const chartData3 = {
                                                        labels: ["Delivered", "Undelivered"],
                                                        data: [${totaldone}, ${totalcancel}],
                                                    };

                                                    const filterDataByDate = (startDate, endDate, labels, data) => {
                                                        const start = new Date(startDate);
                                                        const end = new Date(endDate);

                                                        const filteredLabels = [];
                                                        const filteredData = [];

                                                        for (let i = 0; i < labels.length; i++) {
                                                            const currentDate = new Date(labels[i]);
                                                            if (currentDate >= start && currentDate <= end) {
                                                                filteredLabels.push(labels[i]);
                                                                filteredData.push(data[i]);
                                                            }
                                                        }

                                                        return {labels: filteredLabels, data: filteredData};
                                                    };

                                                    const updateChart = (chartType, startDate, endDate) => {
                                                        let data, options;

                                                        switch (chartType) {
                                                            case 'line':
                                                                const filteredData1 = filterDataByDate(startDate, endDate, chartData1.labels, chartData1.data);
                                                                const filteredData2 = filterDataByDate(startDate, endDate, chartData2.labels, chartData2.data);

                                                                if (filteredData1.data.length === 0 && filteredData2.data.length === 0) {
                                                                    myChartElement.style.display = 'none';
                                                                    noDataMessage.style.display = 'block';
                                                                    return;
                                                                }

                                                                const allLabels = Array.from(new Set([...filteredData1.labels, ...filteredData2.labels])).sort((a, b) => new Date(a) - new Date(b));
                                                                const dataset1Data = allLabels.map(label => {
                                                                    const index = filteredData1.labels.indexOf(label);
                                                                    return index !== -1 ? filteredData1.data[index] : null;
                                                                });
                                                                const dataset2Data = allLabels.map(label => {
                                                                    const index = filteredData2.labels.indexOf(label);
                                                                    return index !== -1 ? filteredData2.data[index] : null;
                                                                });

                                                                data = {
                                                                    labels: allLabels,
                                                                    datasets: [{
                                                                            label: 'Success',
                                                                            data: dataset1Data,
                                                                            backgroundColor: 'rgba(75, 192, 192, 0.2)',
                                                                            borderColor: 'rgba(75, 192, 192, 1)',
                                                                            borderWidth: 2,
                                                                            fill: false,
                                                                            tension: 0.1,
                                                                            spanGaps: true // Thêm thuộc tính này để nối các điểm dữ liệu
                                                                        }, {
                                                                            label: 'Failed',
                                                                            data: dataset2Data,
                                                                            backgroundColor: 'rgba(255, 99, 132, 0.2)',
                                                                            borderColor: 'rgba(255, 99, 132, 1)',
                                                                            borderWidth: 2,
                                                                            fill: false,
                                                                            borderDash: [5, 5],
                                                                            tension: 0.1,
                                                                            spanGaps: true // Thêm thuộc tính này để nối các điểm dữ liệu
                                                                        }]
                                                                };
                                                                options = {
                                                                    scales: {
                                                                        x: {
                                                                            type: 'time',
                                                                            time: {
                                                                                unit: 'day'
                                                                            },
                                                                            title: {
                                                                                display: true,
                                                                                text: 'Time (Day)'
                                                                            }
                                                                        },
                                                                        y: {
                                                                            title: {
                                                                                display: true,
                                                                                text: 'Order (Quantity)'
                                                                            },
                                                                            beginAtZero: true
                                                                        }
                                                                    },
                                                                    plugins: {
                                                                        legend: {
                                                                            display: true,
                                                                        },
                                                                        datalabels: {
                                                                            display: true,
                                                                            formatter: (value) => {
                                                                                return value;
                                                                            }
                                                                        }
                                                                    }
                                                                };
                                                                break;
                                                            case 'doughnut':
                                                                if (chartData3.data.reduce((a, b) => a + b, 0) === 0) {
                                                                    myChartElement.style.display = 'none';
                                                                    noDataMessage.style.display = 'block';
                                                                    return;
                                                                }
                                                                data = {
                                                                    labels: chartData3.labels,
                                                                    datasets: [{
                                                                            label: 'Order Status',
                                                                            data: chartData3.data,
                                                                            backgroundColor: ['#7CB342', '#C62828'],
                                                                            borderColor: ['#7CB342', '#C62828'],
                                                                            borderWidth: 1,
                                                                        }],
                                                                };
                                                                options = {
                                                                    plugins: {
                                                                        legend: {
                                                                            display: false,
                                                                        },
                                                                        datalabels: {
                                                                            color: 'black',
                                                                            formatter: (value, context) => {
                                                                                const total = context.dataset.data.reduce((a, b) => a + b, 0);
                                                                                const percentage = ((value / total) * 100).toFixed(1) + '%';
                                                                                return percentage; // Hiển thị phần trăm
                                                                            },
                                                                            font: {
                                                                                weight: 'bold',
                                                                                size: function (context) {
                                                                                    const width = context.chart.width;
                                                                                    const size = Math.round(width / 15);
                                                                                    return size > 20 ? 20 : size;
                                                                                }
                                                                            },
                                                                            textStrokeWidth: 2,
                                                                            textStrokeColor: 'rgba(255, 255, 255, 0.5)',
                                                                        }
                                                                    }
                                                                };
                                                                break;
                                                            default:
                                                                console.error('Unknown chart type');
                                                                return;
                                                        }

                                                        if (myChart) {
                                                            myChart.destroy();
                                                        }
                                                        const ctx = myChartElement.getContext('2d');

                                                        myChart = new Chart(ctx, {
                                                            type: chartType === 'line' ? 'line' : 'doughnut',
                                                            data: data,
                                                            options: options
                                                        });

                                                        myChartElement.style.display = 'block';
                                                        noDataMessage.style.display = 'none';
                                                    };

                                                    document.getElementById('submit-btn').addEventListener('click', function () {
                                                        const startDate = document.getElementById('start-date').value;
                                                        const endDate = document.getElementById('end-date').value;

                                                        if (startDate && endDate) {
                                                            // Lưu các giá trị ngày tháng vào URL
                                                            const url = new URL(window.location.href);
                                                            url.searchParams.set('start-date', startDate);
                                                            url.searchParams.set('end-date', endDate);
                                                            window.location.href = url.toString(); // Tải lại trang với các tham số mới
                                                        } else {
                                                            alert('Please select both start and end dates.');
                                                        }
                                                    });

                                                    // Hàm hiển thị biểu đồ tương ứng khi click vào các liên kết Delivery hoặc Order
                                                    window.showChart = function (chartType) {
                                                        currentChartType = chartType; // Cập nhật loại biểu đồ hiện tại
                                                        const statusContainer = document.getElementById('status-container');

                                                        switch (chartType) {
                                                            case 'delivery':
                                                                const startDate = document.getElementById('start-date').value;
                                                                const endDate = document.getElementById('end-date').value;
                                                                updateChart('line', startDate, endDate); // Hiển thị biểu đồ dạng đường
                                                                statusContainer.style.display = 'none'; // Ẩn phần trạng thái
                                                                document.getElementById('delivery').style.display = 'block'; // Hiển thị ghi chú
                                                                document.querySelector('.date-picker-container').style.display = 'block'; // Luôn hiển thị phần chọn ngày
                                                                break;
                                                            case 'order':
                                                                updateChart('doughnut'); // Hiển thị biểu đồ doughnut
                                                                statusContainer.style.display = 'block'; // Hiển thị phần trạng thái
                                                                document.getElementById('delivery').style.display = 'none'; // Ẩn ghi chú
                                                                document.querySelector('.date-picker-container').style.display = 'none';
                                                                break;
                                                            default:
                                                                console.error('Unknown chart type');
                                                        }
                                                    };

                                                    // Khởi tạo biểu đồ ban đầu với toàn bộ dữ liệu
                                                    updateChart('line', chartData1.labels[0], chartData1.labels[chartData1.labels.length - 1]);

                                                    // Kiểm tra và khôi phục các giá trị ngày tháng từ URL nếu có
                                                    const urlParams = new URLSearchParams(window.location.search);
                                                    const startDateParam = urlParams.get('start-date');
                                                    const endDateParam = urlParams.get('end-date');

                                                    if (startDateParam && endDateParam) {
                                                        document.getElementById('start-date').value = startDateParam;
                                                        document.getElementById('end-date').value = endDateParam;
                                                        updateChart(currentChartType, startDateParam, endDateParam); // Cập nhật biểu đồ với các giá trị ngày tháng từ URL
                                                    }
                                                });
        </script>
    </body>
</html>