
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
                <div class = detailsOrders>
                    <div class="details">
                        <div class="recentOrders">
                            <div class="cardHeader">
                                <h2>All Orders</h2>
                            </div>
                            <table>
                                <thead>
                                    <tr>
                                        <td>ID</td>
                                        <td>Restaurant ID</td>
<!--                                        <td>Delivery ID</td>-->
                                        <td>Customer ID</td>
                                        <td>Total Amount</td>
                                        <td>Status</td>
                                        <td>Order Date</td>
                                    </tr>
                                </thead>

                                <tbody>  
                                    <c:forEach var="d" items="${order}">
                                        <tr onclick="openModal(${d.id})" style="cursor: pointer;">
                                            <td>${d.id}</td>
                                            <td>${d.restaurant_id}</td>
<!--                                            <td>${d.delivery_id}</td>-->
                                            <td>${d.customer_id}</td>
                                            <td>${d.total_amount}</td>
                                            <td>${d.status}</td>
                                            <td>${d.order_date}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>

                            <form action = "#" method = "POST">
                                <div id="myModal" class="modal">
                                    <div class="modal-content">
                                        <span class="close" onclick="closeModal();">&times;</span>
                                        <div id="modalContent">
                                            <!-- Nội dung chi tiết đơn hàng sẽ được tải vào đây -->
                                        </div>
                                        <div class="modal-footer" style = "margin-top: 200px;">
                                            <button type="submit" id="acceptBtn" name = "accept">Nhận Đơn</button>
                                            <button type="submit" id="declineBtn" name = "reject">Bỏ qua</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
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
                                            //Mở modal                
                                            function openModal(id) {
                                                fetch('order', {
                                                    method: 'POST',
                                                    headers: {
                                                        'Content-Type': 'application/x-www-form-urlencoded',
                                                    },
                                                    body: 'id=' + id
                                                })
                                                        .then(response => response.text())
                                                        .then(html => {
                                                            document.getElementById('modalContent').innerHTML = html;
                                                            var modal = document.getElementById("myModal");
                                                            modal.style.display = "block";
                                                        })
                                                        .catch(error => console.error('Error:', error));
                                            }

                                            // Đóng modal
                                            function closeModal() {
                                                var modal = document.getElementById("myModal");
                                                modal.style.display = "none";
                                            }
        </script>
    </body>

</html>