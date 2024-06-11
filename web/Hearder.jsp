<%-- 
    Document   : Hearder
    Created on : Jun 7, 2024, 10:59:47 AM
    Author     : manh0
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel = "icon" 
              href="images/icon/logo.png" 
              type="image/x-icon">

        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>GrubHub</title>
        <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />
        <!-- font awesome cdn link  -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <!-- custom css file link  -->
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/jquery-nice-select/1.1.0/css/nice-select.min.css">
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
                    <a class="" href="" >Hello ${sessionScope.acc.userName}</a>
                </c:if>
            </nav>
            <c:set var="size" value="${sessionScope.size}"/>
            <div class="icons">
                <i class="fas fa-bars" id="menu-bars"></i>
                <i class="fas fa-search" id="search-icon"></i>
                <a href="cart" class="fas fa-shopping-cart">
                    <span id="cartCount">${sessionScope.cart.items.size()}</span>
                </a>

                <c:if test="${sessionScope.acc == null}">
                    <a href="cuslogin" class="login-btn">login</a>
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
    </body>
</html>
