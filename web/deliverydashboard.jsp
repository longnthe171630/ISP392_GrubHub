
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Delivery DashBoard</title>
        <!-- ======= Styles ====== -->
        <link rel="stylesheet" href="css/style_ship.css">

    </head>

    <body>
        <!-- =============== Navigation ================ -->
        <div class="container">
            <div class="navigation">
                <ul>
                    <li>
                        <a href="home">
                            <span class="logo">
                                <i class="icon"></i>
                                <span class="title">GrubHub</span>
                            </span>
                        </a>
                    </li>

                    <li>
                        <a href="deliverydashboard">
                            <span class="icon">
                                <ion-icon name="home-outline"></ion-icon>
                            </span>
                            <span class="title">Dashboard</span>
                        </a>
                    </li>

                    <li>
                        <a href="order">
                            <span class="icon">
                                <ion-icon name="cart-outline"></ion-icon>
                            </span>
                            <span class="title">Orders</span>
                        </a>
                    </li>

                    <li>
                        <a href="#">
                            <span class="icon">
                                <ion-icon name="time-outline"></ion-icon>
                            </span>
                            <span class="title">History</span>
                        </a>
                    </li>

                    <li>
                        <a href="#">
                            <span class="icon">
                                <ion-icon name="chatbubble-outline"></ion-icon>
                            </span>
                            <span class="title">Messages</span>
                        </a>
                    </li>

                    <li>
                        <a href="#">
                            <span class="icon">
                                <ion-icon name="help-outline"></ion-icon>
                            </span>
                            <span class="title">Help</span>
                        </a>
                    </li>
                </ul>
            </div>

            <!-- ========================= Main ==================== -->
            <div class="main">
                <div class="topbar">
                    <!--                <div class="toggle">
                                        <ion-icon name="menu-outline"></ion-icon>
                                    </div>-->

                    <div class="search">
                        <label>
                            <input type="text" placeholder="Search here">
                            <ion-icon name="search-outline"></ion-icon>
                        </label>
                    </div>

                    <div class="avatar-container">
                        <img src="images/icon/avatar1.jpg" alt="Avatar" class="avatar" onclick="toggleDropdown()">
                        <div id="dropdown" class="dropdown-content">
                            <a href="profile">Profile</a>
                            <a href="settings">Setting</a>
                            <a href="logout">Logout</a>
                        </div>
                    </div>
                </div>

                <!-- ======================= Cards ================== -->
                <div class="cardBox">
                    <div class="card" style = "color: green">
                        <div>
                            <div class="numbers">1</div>
                            <div class="cardName">Success</div>
                        </div>

                        <div class="iconBx">
                            <ion-icon name="checkmark-circle-outline"></ion-icon>
                        </div>
                    </div>

                    <div class="card" style = "color: blue">
                        <div>
                            <div class="numbers">1</div>
                            <div class="cardName">Delivering</div>
                        </div>

                        <div class="iconBx">
                            <ion-icon name="bicycle-outline"></ion-icon>
                        </div>
                    </div>

                    <div class="card" style = "color: red">
                        <div>
                            <div class="numbers">1</div>
                            <div class="cardName">Cancel</div>
                        </div>

                        <div class="iconBx" style = "red">
                            <ion-icon name="close-circle-outline"></ion-icon>
                        </div>
                    </div>

                    <div class="card">
                        <div>
                            <div class="numbers">100K</div>
                            <div class="cardName">Earning</div>
                        </div>

                        <div class="iconBx">
                            <ion-icon name="cash-outline"></ion-icon>
                        </div>
                    </div>
                </div>

                <!-- ================ Order Details List ================= -->
                <div class="details">
                    <div class="recentOrders">
                        <div class="cardHeader">
                            <h2>Recent Orders</h2>
                            <a href="order" class="btn" style ="color: black">View All</a>
                        </div>

                        <table>
                            <thead>
                                <tr>
                                    <th>ID</th>
<!--                                    <th>Address ID</th>-->
                                    <th>Delivery Person</th>
                                    <th>Ship Price</th>
                                    <th>Delivery Date</th>
                                    <th>Status</th>
                                    <th>Image</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="d" items="${list}">
                                    <tr>
                                        <td>${d.id}</td>
<!--                                        <td>${d.address_id}</td> -->
                                        <td>${d.delivery_person_id}</td>
                                        <td>${d.ship_price}</td>
                                        <td>${d.delivery_date}</td>
                                        <td>${d.status}</td>
                                        <td>${d.image}</td>   
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>

                    <!-- ================= New Customers ================ -->
                    <div class="recentCustomers">
                        <div class="cardHeader">
                            <h2>Recent Customers</h2>
                        </div>

                        <table>
                            <tr>
                                <td width="30px">
                                    <div class="imgBx"><img src="assets/imgs/customer02.jpg" alt=""></div>
                                </td>
                                <td>
                                    <h4>Admin<br> <span>VietNam</span></h4>
                                </td>
                            </tr>

                        </table>
                    </div>
                </div>
            </div>
        </div>

        <!-- =========== Scripts =========  -->
        <script src="js/main.js"></script>

        <!-- ====== ionicons ======= -->
        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
        <script>
                            function toggleDropdown() {
                                var dropdown = document.getElementById("dropdown");
                                if (dropdown.style.display === "block") {
                                    dropdown.style.display = "none";
                                } else {
                                    dropdown.style.display = "block";
                                }
                            }

                            // Đóng dropdown nếu người dùng nhấn ngoài nó
                            window.onclick = function (event) {
                                if (!event.target.matches('.avatar')) {
                                    var dropdowns = document.getElementsByClassName("dropdown-content");
                                    for (var i = 0; i < dropdowns.length; i++) {
                                        var openDropdown = dropdowns[i];
                                        if (openDropdown.style.display === "block") {
                                            openDropdown.style.display = "none";
                                        }
                                    }
                                }
                            }
        </script>
    </body>

</html>