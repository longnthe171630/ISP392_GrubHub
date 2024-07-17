<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel = "icon" href="images/icon/logo.png" type="image/x-icon">
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>GrubHub</title>
        <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />
        <!-- font awesome cdn link  -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <!-- custom css file link  -->
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-nice-select/1.1.0/css/nice-select.min.css">
        <style>
            li a:hover {
                color: #f6692a;
            }
            ul li a.active {
                color: #f6692a;
            }
            .li_header {
                color: white;
                margin-left: 50px;
                font-size: 18px;
            }
            .submit-price {
                font-size: 16px;
                background-color: black;
                color: white;
                font-weight: 600;
                padding: 5px 40px;
                border-radius: 20px;
                margin: 10px 0 20px
            }
            .submit-price:hover {
                background-color: rgba(0,0,0,0.3);
                color: black;
                font-weight: 600;
            }
            .notification-bell {
                position: relative;
                display: inline-block;
                cursor: pointer;
            }
            .notification-popup {
                display: none;
                position: absolute;
                top: 30px;
                right: 0;
                background-color: #fff;
                box-shadow: 0 0 10px rgba(0,0,0,0.1);
                border: 1px solid #ccc;
                padding: 10px;
                z-index: 1000;
                width: 300px; /* Adjust width as needed */
            }
            .notification-item {
                border-bottom: 1px solid #ccc;
                padding: 10px;
            }
            .notification-item:last-child {
                border-bottom: none;
            }
            .notification-item span {
                display: block;
                margin-bottom: 5px;
            }
            .notification-count {
                display: flex;
                background: linear-gradient(to right, #fff 0%, #f6f6f6 100%);
                color: #222;
                width: 20px;
                justify-content: center;
                align-items: center;
                height: 20px;
                border-radius: 50px;
                position: absolute;
                top: 0;
                right: 2px;
                z-index: 9;
                border: 2px solid #232323;
            }
        </style>
    </head>
    <body>
        <header>
            <a href="home" class="logo"><i class="fas fa-utensils"></i>GrubHub</a>
            <nav class="navbar">
                <a class="active" href="home">Home</a>
                <a href="restaurant">Restaurant</a>
                <a href="About.jsp">About</a>
                <a href="Contact.jsp">Contact</a>
                <c:if test="${sessionScope.acc != null}">
                    <a class="" href="load">Hello ${sessionScope.acc.username}</a>
                </c:if>
            </nav>
            <c:set var="size" value="${sessionScope.size}"/>
            <div class="icons">     
                <i class="fas fa-bars" id="menu-bars"></i>
                <i class="fas fa-search" id="search-icon"></i>
                <a href="show" class="fas fa-shopping-cart">
                    <span id="cartCount">${sessionScope.cart.items.size()}</span>
                </a>
                <a href="Favorites.jsp" method="post" class="fas fa-heart"></a>
                <div class="notification-bell">
                    <i class="fas fa-bell"></i>
                    <span class="notification-count" id="notification-count">${notifications != null ? notifications.size() : 0}</span>
                    <div class="notification-popup" id="notification-popup">
                        <c:choose>
                            <c:when test="${not empty notifications}">
                                <c:forEach var="n" items="${notifications}">
                                    <div class="notification-item" data-id="${n.id}">
                                        <span class="notification-content">${n.descripsion}</span>
                                    </div>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <p>No notifications</p>
                            </c:otherwise>
                        </c:choose>      
                    </div>
                </div>
                <c:if test="${sessionScope.acc == null}">
                    <a href="login" class="login-btn">login</a>
                </c:if>               
                <c:if test="${sessionScope.acc != null}">
                    <a href="logout" class="login-btn">logout</a>
                </c:if>              
            </div> 
        </header>
        <!-- header section ends-->
        <!-- search form  -->
        <form action="search" method="post" id="search-form">
            <input value="${txtS}" name="txt" type="search" placeholder="What do you want?" id="search-box">
            <label for="search-box" class="fas fa-search"></label>
            <i class="fas fa-times" id="close"></i>
        </form>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const bell = document.querySelector(".notification-bell");
                const popup = document.querySelector(".notification-popup");
                const count = document.getElementById("notification-count");

                bell.addEventListener("click", function (event) {
                    if (popup.style.display === "none" || popup.style.display === "") {
                        popup.style.display = "block";
                        count.style.display = "none"; // Hide the count when bell is clicked
                    } else {
                        popup.style.display = "none";
                    }
                    event.stopPropagation(); // Prevent propagation to parent elements
                });

                // Close popup when clicking outside
                document.addEventListener("click", function (event) {
                    if (!bell.contains(event.target) && !popup.contains(event.target)) {
                        popup.style.display = "none";
                    }
                });
            });
        </script>


        <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
        <!-- custom js file link  -->
        <script src="js/script.js"></script>
    </body>
</html>
