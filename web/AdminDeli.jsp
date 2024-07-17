<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <link rel = "icon" 
              href="images/icon/logo.png" 
              type="image/x-icon">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Boxicons -->
        <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
        <!-- My CSS -->
        <link rel="stylesheet" href="css/admin.css">

        <title>AdminHub</title>
    </head>
    <body>


        <!-- SIDEBAR -->
        <section id="sidebar">
            <a href="Admin.jsp" class="brand">
                <i class='bx bxs-smile'></i>
                <span class="text">AdminHub</span>
            </a>
            <ul class="side-menu top">
                <li>
                    <a href="admin?action=home"">
                        <i class='bx bxs-dashboard'></i>
                        <span class="text">Dashboard</span>
                    </a>
                </li>
                <li >
                    <a href="admin?action=cus">
                        <i class='bx bxs-user'></i>
                        <span class="text">Customer Account</span>
                    </a>
                </li>
                <li  >
                    <a href="admin?action=res">
                        <i class='bx bx-restaurant'></i>
                        <span class="text">Restaurant Account</span>
                    </a>
                </li>
                <li  class="active">
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
            </ul>
            <ul class="side-menu">
                <li>
                    <a href="#">
                        <i class='bx bxs-cog' ></i>
                        <span class="text">Settings</span>
                    </a>
                </li>
                <li>
                    <a href="Home.jsp" class="logout">
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
                <!--			<a href="#" class="nav-link">Categories</a>-->
                <form action="DashboardServlet">
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
                                <a class="active" href="#">Ban</a>
                            </li>
                        </ul>
                    </div>

                </div>
                <div class="table-data">
                    <div class="order">
                        <div class="head">
                            <h3>List User</h3>
                            <i class='bx bx-search' ></i>
                            <i class='bx bx-filter' ></i>
                        </div>
                        <table>
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Order_id</th>
                                    <th>Shipper_name</th>
                                    <th>Ship_price</th>
                                    <th>Deli_date</th>
                                    <th>Status</th>
                                </tr>
                            </thead>
                            <tbody>

                                <c:forEach items="${requestScope.listDeli}" var="o">
                                    <tr>
                                        <td>${o.id}</td>
                                        <td>${o.order_id}</td>
                                        <td>${o.deliPerson}</td>
                                        <td>${o.ship_price}</td>
                                        <td>${o.delivery_date}</td>
                                        <td><span class="status completed">${o.status}</span></td>
                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>
                    </div>

                </div>

            </main>
            <!-- MAIN -->
        </section>
        <!-- CONTENT -->


        <script src="js/admin.js"></script>
    </body>
</html>