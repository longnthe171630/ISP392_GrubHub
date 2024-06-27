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
                    <a href="#">
                        <i class='bx bxs-bell' ></i>
                        <!--                        bxs-message-dots-->
                        <span class="text">Notification</span>
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
                <!--                <input type="checkbox" id="switch-mode" hidden>-->
                <div class="dropdown-container" >
                    <div class="notification" >
                        <i class='bx bxs-bell' onclick="toggleDropdown('dropdown3')"></i>
                        <span class="num">10</span>
                    </div>
                    <div id="dropdown3" class="dropdown-content-2">
                        <a href="#">Notification 1</a>
                        <a href="#">Notification 2</a>
                        <a href="#">Notification 3</a>
                    </div>
                </div>
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
                                <a class="active" href="deliverydashboard">Dashboard</a>
                            </li>
                        </ul>
                    </div>
                    <a href="#" class="btn-download">
                        <i class='bx bxs-cloud-download' ></i>
                        <span class="text">Download PDF</span>
                    </a>
                </div>

                <ul class="box-info">
                    <li>
                        <i class='bx bx-check' ></i>
                        <span class="text">
                            <h3>${totaldone}</h3>
                            <p>Success</p>
                        </span>
                    </li>
                    <li>
                        <i class='bx bxs-truck' ></i>
                        <span class="text">
                            <h3>${totaldelivery}</h3>
                            <p>Processing</p>
                        </span>
                    </li>
                    <li>
                        <i class='bx bx-x' ></i>
                        <span class="text">
                            <h3>${totalcancel}</h3>
                            <p>Canceled</p>
                        </span>
                    </li>
                    <li>
                        <i class='bx bxs-dollar-circle' ></i>
                        <span class="text">
                            <h3>${totalship}</h3>
                            <p>Earning</p>
                        </span>
                    </li>
                </ul>

                <div class="table-data">
                    <div class="order">
                        <div class="head">
                            <h3>Your Order</h3>
                            <form action="deliverydashboard" method = "GET">
                                <div class="form-input">
                                    <input style ="border-radius: 5px; font-size: 100%;" type="search" name="search" placeholder="Search by code">
                                    <button type="submit" class="search-btn"><i class='bx bx-search' ></i></button>
                                </div>
                            </form>
                            <div class="dropdown-container">
                                <i class='bx bx-filter' onclick="toggleDropdown('dropdown2')"></i>
                                <div id="dropdown2" class="dropdown-content-1">
                                    <a href="#">Newest</a>
                                    <a href="#">Oldest</a>
                                </div>
                            </div> 
                        </div>
                        <p style = "color: red">${err}</p>
                        <table>
                            <c:choose>
                                <c:when test="${fn:length(list) == 0}">
                                    <p class="empty-message">Let's start receiving your orders!</p>
                                </c:when>
                                <c:otherwise>
                                    <thead>
                                        <tr>
                                            <th>Code</th>
                                            <th>Ship Price</th>
                                            <th>Delivery Date</th>
                                            <th>Status</th>
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
                                                            <span class="status process">${d.status}</span>
                                                        </c:when>
                                                        <c:when test="${d.status == 'Đang lấy hàng'}">
                                                            <span class="status take">${d.status}</span>
                                                        </c:when>
                                                    </c:choose>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </c:otherwise>
                            </c:choose>
                        </table>
                        <div class="pagination">
                            <c:forEach var="i" begin="1" end="${totalPages}">
                                <a href="?page=${i}" class="${i == currentPage ? 'active' : ''}">${i}</a>
                            </c:forEach>
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
                    <div class="todo">
                        <div class="head">
                            <h3>Todos</h3>
                            <i class='bx bx-plus' ></i>
                            <i class='bx bx-filter' ></i>
                        </div>
                        <ul class="todo-list">
                            <li class="completed">
                                <p>Todo List</p>
                                <i class='bx bx-dots-vertical-rounded' ></i>
                            </li>
                            <li class="completed">
                                <p>Todo List</p>
                                <i class='bx bx-dots-vertical-rounded' ></i>
                            </li>
                            <li class="not-completed">
                                <p>Todo List</p>
                                <i class='bx bx-dots-vertical-rounded' ></i>
                            </li>
                            <li class="completed">
                                <p>Todo List</p>
                                <i class='bx bx-dots-vertical-rounded' ></i>
                            </li>
                            <li class="not-completed">
                                <p>Todo List</p>
                                <i class='bx bx-dots-vertical-rounded' ></i>
                            </li>
                        </ul>
                    </div>
                </div>
            </main>
            <!-- MAIN -->
        </section>
        <!-- CONTENT -->
        <script src="js/delivery.js"></script>
        <script>
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