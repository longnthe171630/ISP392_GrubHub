<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <title>Delivery History</title>
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
                    <a href="#">
                        <i class='bx bxs-message-dots' ></i>
                        <span class="text">Message</span>
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
                <a href="#" class="notification">
                    <i class='bx bxs-bell' ></i>
                    <span class="num">8</span>
                </a>
                <div class="dropdown-container">
                    <img src="images/icon/avatar1.jpg" alt="Avatar" class="avatar" onclick="toggleDropdown('dropdown1')">
                    <div id="dropdown1" class="dropdown-content">
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
                        <h1>Delivery Dashboard</h1>
                        <ul class="breadcrumb">
                            <li>
                                <a href="home">Home</a>
                            </li>
                            <li><i class='bx bx-chevron-right' ></i></li>
                            <li>
                                <a class="active" href="deliveryorder">History</a>
                            </li>
                        </ul>
                    </div>
                    <a href="#" class="btn-download">
                        <i class='bx bxs-cloud-download' ></i>
                        <span class="text">Download PDF</span>
                    </a>
                </div>
                <!-- ================ Order Details List ================= -->
                <div class="table-data">
                    <div class="order">
                        <div class="head">
                            <h3>History</h3>
                            <form action="deliveryhistory" method = "GET">
                                <div class="form-input">
                                    <input style ="border-radius: 5px; font-size: 100%;" type="search" name="search" placeholder="Search by code">
                                    <button type="submit" class="search-btn"><i class='bx bx-search' ></i></button>
                                </div>
                            </form>
                            <div class="dropdown-container">
                                <i class='bx bx-filter' onclick="toggleDropdown('dropdown2')"></i>
                                <div id="dropdown2" class="dropdown-content-1">
                                    <a href="#">Earliest</a>
                                    <a href="#">Latest</a>
                                </div>
                            </div>
                        </div>
                        <p style = "color: red">${err}</p>
                        <table>
                            <c:choose>
                                <c:when test="${fn:length(list) == 0}">
                                    <p class="empty-message">You haven't delivered any orders yet!</p>
                                </c:when>
                                <c:otherwise>
                                    <thead>
                                        <tr>
                                            <th>Code</th>
                                            <th>Ship Price</th>
                                            <th>Delivery Date</th>
                                            <th>Status</th>
                                            <th> </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="d" items="${list}">
                                            <tr>
                                                <td>${d.order_id}</td>
                                                <td>${d.ship_price}</td>
                                                <td>${d.delivery_date}</td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${d.status == 'Đã giao'}">
                                                            <span class="status completed">${d.status}</span>
                                                        </c:when>
                                                        <c:when test="${d.status == 'Không giao được'}">
                                                            <span class="status pending">${d.status}</span>
                                                        </c:when>
                                                    </c:choose>
                                                </td>
                                                <td>
                                                    <button  onclick="openModal(${d.order_id})" style="cursor: pointer;">View</button>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </c:otherwise>
                            </c:choose>
                        </table>
                        <!--                        Phân trang-->
                        <div class="pagination">
                            <c:choose>
                                <c:when test="${totalPages > 1}">
                                    <c:if test="${currentPage > 1}">
                                        <a href="?page=${currentPage - 1}">&laquo; Previous</a>
                                    </c:if>
                                    <c:forEach begin="1" end="${totalPages}" var="i">
                                        <c:choose>
                                            <c:when test="${i == currentPage}">
                                                <a href="#" class="active">${i}</a>
                                            </c:when>
                                            <c:otherwise>
                                                <c:choose>
                                                    <c:when test="${i == 1 || i == totalPages || (i >= currentPage - 1 && i <= currentPage + 1)}">
                                                        <a href="?page=${i}">${i}</a>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:if test="${i == 2 || i == totalPages - 1 || (i == currentPage - 2 || i == currentPage + 2)}">
                                                            <a href="#">...</a>
                                                        </c:if>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>

                                    <c:if test="${currentPage < totalPages}">
                                        <a href="?page=${currentPage + 1}">Next &raquo;</a>
                                    </c:if>
                                </c:when>
                            </c:choose>
                        </div>

                        <div id="myModal" class="modal">
                            <div class="modal-content">
                                <span class="close" onclick="closeModal();">&times;</span>
                                <div id="modalContent">
                                    <!-- Nội dung chi tiết đơn hàng sẽ được tải vào đây -->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
            <!-- MAIN -->
        </section>
        <!-- CONTENT -->

        <script src="js/delivery.js"></script>
        <!-- ====== ionicons ======= -->

        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
        <script>
                                    //Mở modal                
                                    function openModal(order_id) {
                                        fetch('deliveryhistory', {
                                            method: 'POST',
                                            headers: {
                                                'Content-Type': 'application/x-www-form-urlencoded',
                                            },
                                            body: 'order_id=' + order_id
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