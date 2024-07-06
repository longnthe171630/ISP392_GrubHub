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
        <link rel="stylesheet" href="css\restaurant_dashboard.css">

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
                <li >
                    <a href="restaurantdashboard">
                        <i class='bx bxs-dashboard' ></i>
                        <span class="text">Dashboard</span>
                    </a>
                </li>
                <li class="active">
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
                    <a href="#">
                        <i class='bx bxs-message-dots' ></i>
                        <span class="text">Message</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class='bx bxs-group' ></i>
                        <span class="text">Team</span>
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
                            <li><i class='bx bx-chevron-right' ></i></li>
                            <li>
                                <a class="active" href="#">Product</a>
                            </li>
                        </ul>
                    </div>
                    <a href="addnewproduct" class="btn-download">
                        <i class='bx bxs-cloud-download' ></i>
                        <span class="text">Add new product</span>
                    </a>
                </div>
                <div class="table-data">
                    <div class="order">
                        <div class="head">
                            <h3>Recent Orders</h3>
                            <i class='bx bx-search' ></i>
                            <i class='bx bx-filter' ></i>
                        </div>
                        <table>
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>Rating</th>
                                    <th>Action</th>

                                </tr>
                            </thead>
                            <tbody>
                                <%-- Lặp qua danh sách sản phẩm từ thuộc tính "listProduct" trong request --%>
                                <% 
                                    List<Product> productList = (List<Product>) request.getAttribute("listProduct");
                                    if (productList != null && !productList.isEmpty()) {
                                        for (Product product : productList) {
                                %>
                                <tr>
                                    <td><%= product.getName() %></td>
                                    <td><%= product.getPrice() %></td>
                                    <td><%= product.getQuantity() %></td>
                                    <td><%= product.getRating() %> </td>
                                    <td><a href="editproduct?id=<%= product.getId() %>"><span class="status completed">Edit</span></a></td>
                                </tr>
                                <% 
                                        }
                                    } else {
                                %>
                                <tr>
                                    <td colspan="5">No products available.</td>
                                </tr>
                                <% } %>
                            </tbody>
                        </table>
                    </div>
                </div>
                <%-- Đoạn mã JSP để hiển thị thông báo lỗi --%>
                <div id="error-message" style="color: red;">
                    <% 
                    String errorMessage = request.getParameter("error");
                    if (errorMessage != null) {
                        if (errorMessage.equals("empty_fields")) {
                            out.println("Vui lòng điền đầy đủ thông tin.");
                        } else if (errorMessage.equals("invalid_numbers")) {
                            out.println("Giá và số lượng phải là số.");
                        } else if (errorMessage.equals("invalid_input")) {
                            out.println("Dữ liệu không hợp lệ, vui lòng kiểm tra lại.");
                        }
                    }
                    %>
                </div>

            </main>
            <!-- MAIN -->
        </section>
        <!-- CONTENT -->


        <script src="js\restaurant_dashboard.js"></script>
    </body>
</html>
