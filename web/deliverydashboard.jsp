
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
                            <span class="title">Bảng điều khiển</span>
                        </a>
                    </li>

                    <li>
                        <a href="order">
                            <span class="icon">
                                <ion-icon name="cart-outline"></ion-icon>
                            </span>
                            <span class="title">Đơn hàng</span>
                        </a>
                    </li>

                    <li>
                        <a href="#">
                            <span class="icon">
                                <ion-icon name="time-outline"></ion-icon>
                            </span>
                            <span class="title">Lịch sử</span>
                        </a>
                    </li>

                    <li>
                        <a href="#">
                            <span class="icon">
                                <ion-icon name="chatbubble-outline"></ion-icon>
                            </span>
                            <span class="title">Tin nhắn</span>
                        </a>
                    </li>

                    <li>
                        <a href="#">
                            <span class="icon">
                                <ion-icon name="help-outline"></ion-icon>
                            </span>
                            <span class="title">Trợ giúp</span>
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
                            <input type="text" placeholder="Tìm kiếm">
                            <ion-icon name="search-outline"></ion-icon>
                        </label>
                    </div>

                    <div class="avatar-container">
                        <img src="images/icon/avatar1.jpg" alt="Avatar" class="avatar" onclick="toggleDropdown()">
                        <div id="dropdown" class="dropdown-content">
                            <a href="Showinfo.jsp">Hồ sơ</a>
                            <a href="settings">Cài đặt</a>
                            <a href="logout">Đăng xuất</a>

                        </div>
                    </div>
                </div>

                <!-- ======================= Cards ================== -->
                <div class="cardBox">
                    <div class="card" style = "color: green">
                        <div>
                            <div class="numbers">1</div>
                            <div class="cardName">Đã giao</div>
                        </div>

                        <div class="iconBx">
                            <ion-icon name="checkmark-circle-outline"></ion-icon>
                        </div>
                    </div>

                    <div class="card" style = "color: blue">
                        <div>
                            <div class="numbers">1</div>
                            <div class="cardName">Đang giao</div>
                        </div>

                        <div class="iconBx">
                            <ion-icon name="bicycle-outline"></ion-icon>
                        </div>
                    </div>

                    <div class="card" style = "color: red">
                        <div>
                            <div class="numbers">1</div>
                            <div class="cardName">Không giao được</div>
                        </div>

                        <div class="iconBx" style = "red">
                            <ion-icon name="close-circle-outline"></ion-icon>
                        </div>
                    </div>

                    <div class="card">
                        <div>
                            <div class="numbers">100K</div>
                            <div class="cardName">Lợi nhuận</div>
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
                            <h2>Đơn hàng gần đây</h2>
                            <a href="order" class="btn" style ="color: black">Xem tất cả</a>
                        </div>

                        <table>
                            <thead>
                                <tr>
                                    <th>Mã đơn hàng</th>
                                    <th>Cước vận chuyển</th>
                                    <th>Ngày vận chuyển</th>
                                    <th>Trạng thái</th>
                                    <th>Ảnh xác nhận</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="d" items="${list}">
                                    <tr onclick="openModal(${d.order_id})" style="cursor: pointer;">
                                        <td>${d.order_id}</td>
                                        <td>${d.ship_price}</td>
                                        <td>${d.delivery_date}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${d.status == 'Đang giao'}">
                                                    <span class="status-inProgress">${d.status}</span>
                                                </c:when>
                                                <c:when test="${d.status == 'Đã giao'}">
                                                    <span class="status-delivered">${d.status}</span>
                                                </c:when>
                                                <c:when test="${d.status == 'Không giao được'}">
                                                    <span class="status-return">${d.status}</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <!-- Không hiển thị gì nếu không phù hợp -->
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <img src="${d.image}" alt="Xem" style="max-width: 100px; height: auto;">
                                        </td>
                                    </tr>
                                </c:forEach>
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
                    </div>

                    <!-- ================= New Customers ================ -->
                    <div class="recentCustomers">
                        <div class="cardHeader">
                            <h2>Khách hàng gần đây</h2>
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
        <script type="text/javascript">
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
                                    function openModal(order_id) {
                                        fetch('deliverydashboard', {
                                            method: 'POST',
                                            headers: {
                                                'Content-Type': 'application/x-www-form-urlencoded',
                                            },
                                            body: 'order_id=' + order_id
                                        })
                                                .then(response => response.text())
                                                .then(html => {
                                                    // Cập nhật nội dung của modalContent với HTML nhận được từ server
                                                    document.getElementById('modalContent').innerHTML = html;

                                                    // Hiển thị modal sau khi cập nhật nội dung
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