<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="deliverychange.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
        <script src="https://cdn.jsdelivr.net/gh/somanchiu/Keyless-Google-Maps-API@v6.6/mapsJavaScriptAPI.js" async defer></script>
        <title><fmt:message key="delivery_dashboard" bundle="${lang}"/></title>
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
                        <span class="text"><fmt:message key="dashboard" bundle="${lang}" /></span>
                    </a>
                </li>
                <li>
                    <a href="deliveryorder">
                        <i class='bx bxs-shopping-bag-alt'></i>
                        <span class="text"><fmt:message key="order" bundle="${lang}" /></span>
                    </a>
                </li>
                <li>
                    <a href="deliveryhistory">
                        <i class='bx bxs-doughnut-chart' ></i>
                        <span class="text"><fmt:message key="history" bundle="${lang}" /></span>
                    </a>
                </li>
                <li>
                    <a href="deliverynotice">
                        <i class='bx bxs-message-dots' ></i>
                        <span class="text"><fmt:message key="notification" bundle="${lang}" /></span>
                    </a>
                </li>
                <li>
                    <a href="deliveryanalysis">
                        <i class="bx bxs-data"></i>
                        <span class="text"><fmt:message key="analysis" bundle="${lang}" /></span>
                    </a>
                </li>
                <li>
                    <a href="deliveryhelp">
                        <i class='bx bxs-group' ></i>
                        <span class="text"><fmt:message key="help" bundle="${lang}" /></span>
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
                <a href="#" class="nav-link"></a>
                <form action="#">
                    <div class="form-input">
                        <!--                        <input type="search" placeholder="Search...">
                                                <button type="submit" class="search-btn"><i class='bx bx-search' ></i></button>-->
                    </div>
                </form>

                <!--                <input type="checkbox" id="switch-mode" hidden>
                                <label for="switch-mode" class="switch-mode"></label>-->

                <div class="dropdown-container">
                    <div class="notification">
                        <i class='bx bxs-bell' onclick="toggleDropdown('dropdown3')"></i>
                        <span class="num"></span>
                    </div>
                    <div id="dropdown3" class="dropdown-content">
                        <div class="notification-header">
                            <h4><fmt:message key="notification" bundle="${lang}"/></h4>
                        </div>
                        <div class="notification-body">
                            <c:choose>
                                <c:when test="${empty notice}">
                                    <div class="no-notifications"><fmt:message key="notice_notification" bundle="${lang}"/></div>
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
                                        <a href="deliverynotice"></i><fmt:message key="see_all" bundle="${lang}"/></a>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>


                <div class="dropdown-container">
                    <img src="images/icon/avatar1.jpg" alt="Avatar" class="avatar" onclick="toggleDropdown('dropdown1')">
                    <div id="dropdown1" class="dropdown-content-1">
                        <a href="loadingdeliveryprofile"><i class="fas fa-user"></i><fmt:message key="profile" bundle="${lang}"/></a>
                        <a href="#" role="button" onclick="openModalx();"><i class="fas fa-cog"></i><fmt:message key="settings" bundle="${lang}" /></a>
                        <a id="logoutButton" href="logout"><i class="fas fa-sign-out-alt"></i><fmt:message key="logout" bundle="${lang}" /></a>
                    </div>
                </div>
            </nav>
            <!-- NAVBAR -->

            <!-- MAIN -->
            <main>
                <div class="head-title">
                    <div class="left">
                        <h1><fmt:message key="delivery_dashboard" bundle="${lang}" /></h1>
                        <ul class="breadcrumb">
                            <li>
                                <a href="home"><fmt:message key="home" bundle="${lang}" /></a>
                            </li>
                            <li><i class='bx bx-chevron-right' ></i></li>
                            <li>
                                <a class="active" href="deliverydashboard"><fmt:message key="dashboard" bundle="${lang}" /></a>
                            </li>
                        </ul>
                    </div>
                    <!--                    <a href="#" class="btn-download">
                                            <i class='bx bxs-cloud-download' ></i>
                                            <span class="text">Download PDF</span>
                                        </a>-->
                </div>

                <ul class="box-info">
                    <li>
                        <i class='bx bx-check'></i>
                        <a href="deliveryanalysis">
                            <span class="text">
                                <h3>${totaldone}</h3>
                                <p><fmt:message key="success" bundle="${lang}" /></p>
                            </span>
                        </a>
                    </li>
                    <li>
                        <i class='bx bxs-truck'></i>
                        <a href="deliveryanalysis">
                            <span class="text">
                                <h3>${totaldelivery}</h3>
                                <p><fmt:message key="delivering" bundle="${lang}" /></p>
                            </span>
                        </a>
                    </li>
                    <li>
                        <i class='bx bx-x'></i>
                        <a href="deliveryanalysis">
                            <span class="text">
                                <h3>${totalcancel}</h3>
                                <p><fmt:message key="failure" bundle="${lang}" /></p>
                            </span>
                        </a>
                    </li>
                    <li>
                        <i class='bx bxs-dollar-circle'></i>
                        <a href="deliveryanalysis">
                            <span class="text">
                                <h3>${totalship}</h3>
                                <p><fmt:message key="earning" bundle="${lang}" /></p>
                            </span>
                        </a>
                    </li>
                </ul>

                <!--Bên dưới là phần chính, hiển thị đơn hàng đang thực hiện-->
                <div class="table-data">
                    <div class="order">
                        <form action="deliverydashboard" method = "GET">
                            <div class="head">
                                <h3><fmt:message key="your_order" bundle="${lang}" /></h3>
                                <div class="form-input">
                                    <input style ="border-radius: 5px; font-size: 100%;" type="search" name="search" placeholder="<fmt:message key="search_by_code" bundle="${lang}" />">
                                    <i type="submit" class="search-btn"><button class='bx bx-search' ></button></i>
                                </div>
                                <div class="dropdown-container">
                                    <i class='bx bx-filter' onclick="toggleDropdown('dropdown2')"></i>
                                    <div id="dropdown2" class="dropdown-content-1">
                                        <a href="?sort=false"><i class="fas fa-calendar-alt"></i><fmt:message key="newest" bundle="${lang}" /></a>
                                        <a href="?sort=true"><i class="fas fa-calendar-minus"></i><fmt:message key="oldest" bundle="${lang}" /></a>
                                    </div>
                                </div>
                            </div>
                        </form>
                        <!-- Thông báo-->

                        <c:if test="${not empty err}">
                            <c:set var="err1" value="" />
                            <c:choose>
                                <c:when test="${err == 'You have successfully received this order!'}">
                                    <c:set var="err1" value="note12" />
                                </c:when>
                                <c:when test="${err == 'The order was just received by someone else!'}">
                                    <c:set var="err1" value="note13" />
                                </c:when>
                                <c:when test="${err == 'Okay, lets look at the other orders!'}">
                                    <c:set var="err1" value="note14" />
                                </c:when>
                                <c:when test="${err == 'Lets complete this order!'}">
                                    <c:set var="err1" value="note15" />
                                </c:when>
                                <c:when test="${err == 'Okay, wait a minute!'}">
                                    <c:set var="err1" value="note16" />
                                </c:when>
                            </c:choose>
                            <div id="notice" class="notice"><fmt:message key="${err1}" bundle="${lang}"/></div>
                        </c:if>

                        <!--Thông báo-->
                        <table>
                            <c:choose>
                                <c:when test="${fn:length(list) == 0}">
                                    <p class="empty-message"><fmt:message key="notice_dashboard" bundle="${lang}" /></p>
                                </c:when>
                                <c:otherwise>
                                    <thead>
                                        <tr>
                                            <th><fmt:message key="code" bundle="${lang}" /></th>
                                            <th><fmt:message key="ship_price" bundle="${lang}" /></th>
                                            <th><fmt:message key="time" bundle="${lang}" /></th>
                                            <th><fmt:message key="status" bundle="${lang}" /></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="d" items="${list}">
                                            <tr onclick="openModal(${d.order_id})" style="cursor: pointer;">
                                                <td>${d.order_id}</td>
                                                <td>${d.ship_price}</td>
                                                <td class="notification-time" data-time="${d.delivery_date}"></td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${d.status == 'Delivering'}">
                                                            <c:set var="delivering" value="${d.status eq 'Delivering' ? 'delivering' : ''}" />
                                                            <span class="status process"><fmt:message key="${delivering}" bundle="${lang}"/></span>
                                                        </c:when>
                                                        <c:when test="${d.status == 'Picking Up'}">
                                                            <c:set var="pickup" value="${d.status eq 'Picking Up' ? 'pickup' : ''}" />
                                                            <span class="status take"><fmt:message key="${pickup}" bundle="${lang}"/></span>
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

                        <!--Bảng modal cho dashboard-->
                        <div id="myModal" class="modal">
                            <div class="modal-content">
                                <span class="close" onclick="closeModal();">&times;</span>
                                <div id="modalContent">
                                    <!-- Nội dung chi tiết đơn hàng sẽ được tải vào đây -->
                                </div>
                            </div>
                        </div>

                        <!--Bảng modal cho settings-->
                        <div id="myModalx" class="modal">
                            <div class="modal-content">
                                <span class="close" onclick="closeModalx();">&times;</span>
                                <div id="modalContentx">
                                    <!-- Nội dung chi tiết đơn hàng sẽ được tải vào đây -->
                                </div>
                            </div>
                        </div>
                    </div>

                    <!--                    Map bên phải-->
                    <div class="todo">
                        <div class="head">
                            <h3><fmt:message key="maps" bundle="${lang}"/></h3>
                            <div class="form-input">
                                <input style="border-radius: 5px; font-size: 100%;" 
                                       type="search" name="search" placeholder="<fmt:message key="search_location" bundle="${lang}" />" id="addressInput">
                                <button class="search-btn" onclick="searchAddress()">
                                    <i class="bx bx-search"></i>
                                </button>
                            </div>
                            <!--                            <i class='bx bx-filter' ></i>-->
                        </div>
                        <div id="map" style="width: 100%; height: 500px;"></div>
                        <div id="distance" style="border: 2px solid; text-align: center; font-style: italic;"></div>
                    </div>
                </div>
            </main>
            <!-- MAIN -->
        </section>
        <!-- CONTENT -->
        <script src="js/delivery.js"></script>
        <script src="js/address.js"></script>
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
                                    //Mở modal                
                                    function openModalx() {
                                        fetch('deliverysettings', {
                                            method: 'GET',
                                            headers: {
                                                'Content-Type': 'application/x-www-form-urlencoded',
                                            },
                                        })
                                                .then(response => response.text())
                                                .then(html => {
                                                    document.getElementById('modalContentx').innerHTML = html;
                                                    var modal = document.getElementById("myModalx");
                                                    if (modal) {
                                                        modal.style.display = "block";
                                                    } else {
                                                        console.error('Modal element not found');
                                                    }
                                                })
                                                .catch(error => console.error('Error:', error));
                                    }

                                    // Hàm đóng modal
                                    function closeModal() {
                                        const modal = document.getElementById("myModal");
                                        modal.style.display = "none";
                                    }

                                    function closeModalx() {
                                        const modal = document.getElementById("myModalx");
                                        modal.style.display = "none";
                                    }
        </script>
    </body>
</html>