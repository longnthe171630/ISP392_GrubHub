<%-- 
Document   : restaurant_dashboard
Created on : Jun 10, 2024, 8:44:09 AM
Author     : phaml
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.*" %> 
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

        <title>Restaurant</title>
    </head>


    <body>


        <!-- SIDEBAR -->
        <section id="sidebar">
            <a href="#" class="brand">
                <i class='bx bxs-smile'></i>
                <span class="text">Restaurant</span>
            </a>
            <ul class="side-menu top">
                <li class="active">
                    <a href="#">
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
                <li>
                    <a href="historyrestaurant">
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
                    <a href="logout" class="logout">
                        <i class='bx bxs-log-out-circle' ></i>
                        <span class="text">Logout</span>
                    </a>
                </li>
            </ul>
        </section>
        <!-- SIDEBAR -->



        <!-- CONTENT -->
        <section id="content">
<!--             NAVBAR 
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
                        <h1>Restaurant</h1>
                        <ul class="breadcrumb">
                            <li>
                                <a href="#">Dashboard</a>
                            </li>
                            <li><i class='bx bx-chevron-right' ></i></li>
                            <li>
                                <a class="active" href="#">Home</a>
                            </li>
                        </ul>
                    </div>
                </div>

                <ul class="box-info">
                    <!-- Hiển thị số lượng đơn hàng mới -->
                    <li>
                        <i class='bx bxs-calendar-check'></i>
                        <span class="text">
                            <h3><%= request.getAttribute("countorder") != null ? request.getAttribute("countorder") : "" %></h3>
                            <p>New Order</p>
                        </span>
                    </li>

                    <!-- Hiển thị số lượng sản phẩm -->
                    <li>
                        <i class='bx bxs-group'></i>
                        <span class="text">
                            <h3><%= request.getAttribute("countproduct") != null ? request.getAttribute("countproduct") : "" %></h3>
                            <p>Product</p>
                        </span>
                    </li>

                </ul>


                <div class="table-data">
                    <div class="order">
                        <div class="head">
                            <h3>Order</h3>
                            <i class='bx bx-search' ></i>
                            <i class='bx bx-filter' ></i>
                        </div>
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
                                <% 
                                Map<Customer, Order> customerOrderMap = (Map<Customer, Order>) request.getAttribute("MapCusOrder");
                                Map<Customer, Account> accountCustomerMap = (Map<Customer, Account>) request.getAttribute("MapAccCus");
                                if (customerOrderMap != null && !customerOrderMap.isEmpty()) {
                                    for (Map.Entry<Customer, Order> entry : customerOrderMap.entrySet()) {
                                        Customer customer = entry.getKey();
                                        Order order = entry.getValue();
                                %>
                                <tr>
                                    <td onclick="redirectToDetail('<%= customer.getId() %>', '<%= order.getId() %>')"> 
                                        <%= customer.getName() %> 
                                    </td>
                                    <td onclick="redirectToDetail('<%= customer.getId() %>', '<%= order.getId() %>')">
                                        <%= order.getTotal_amount() %>
                                    </td>
                                    <td onclick="redirectToDetail('<%= customer.getId() %>', '<%= order.getId() %>')">
                                        <%= order.getOrder_date() %>
                                    </td>
                                    <td><span class="status completed">Chấp nhận</span>
                                        <span class="status pending">Từ chối</span>
                                    </td>
                                </tr>
                                <% 
                                    }
                                } else {
                                %>
                                <tr>
                                    <td colspan="4">Không có đơn hàng đang xử lí.</td>
                                </tr>
                                <% } %>
                            </tbody>
                        </table>
                    </div>

            </main>
            <!-- MAIN -->
        </section>
        <!-- CONTENT -->


        <script src="js\restaurant_dashboard.js"></script>
        <script>
                                        function redirectToDetail(customerId, orderId) {
                                            window.location.href = 'restauranorderdetail?customerId=' + customerId + '&orderId=' + orderId;
                                        }
        </script>
    </body>
</html>
