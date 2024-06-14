<%-- 
    Document   : Home
    Created on : May 25, 2024, 8:45:28 PM
    Author     : manh0
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
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
        <style>
            .pagination-container {
                margin-top: 20px;
                display: flex;
                justify-content: center;
            }
            .pagination {
                display: flex;
                flex-wrap: nowrap;
                list-style: none;
                padding-left: 0;
            }
            .pagination .page-item {
                margin: 0 5px; /* Space between links */
            }
            .pagination .page-item .page-link {
                color: #28a745; /* Green color */
                background-color: #f8f9fa; /* Light grey background */
                border: 1px solid #dee2e6; /* Border color */
                padding: 10px 15px; /* Larger padding */
                font-size: 18px; /* Larger font size */
                border-radius: 5px; /* Rounded corners */
                transition: background-color 0.3s, color 0.3s; /* Smooth transition */
            }
            .pagination .page-item .page-link:hover {
                background-color: #28a745; /* Green background on hover */
                color: #fff; /* White text on hover */
                text-decoration: none; /* Remove underline */
            }
            .pagination .page-item.active .page-link {
                background-color: #28a745; /* Green background */
                border-color: #28a745; /* Border color */
                color: #fff; /* White text */
            }
        </style>

        <script>
            function addToCart(id) {
                $.ajax({
                    type: "POST",
                    url: "cart",
                    data: {
                        id id,
                        num: 1
                    },
                    success: function (response) {
                        $("#cartCount").text(response.cartSize);
                    }
                });
            }
        </script>
        <script src="https://maps.googleapis.com/maps/api/js?key=your_actual_api_key_here&callback=initMap" async defer></script>
    </head>
    <body>
        <!-- header section starts      -->
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
            <div class="icons">
                <i class="fas fa-bars" id="menu-bars"></i>
                <i class="fas fa-search" id="search-icon"></i>
                <a href="show" class="fas fa-shopping-cart">
                    ${requestScope.size} 
                </a>

                <c:if test="${sessionScope.customer == null && sessionScope.account == null}">
                    <a href="cuslogin" class="login-btn">login</a>
                </c:if>               
                <c:if test="${sessionScope.customer != null || sessionScope.account != null}">
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

        <section class="home" id="home">
            <div class="swiper-container home-slider">
                <div class="swiper-wrapper wrapper">
                    <c:forEach items="${requestScope.listPC}" var="v">
                        <div class="swiper-slide slide">
                            <div class="content">
                                <h3>${v.name}</h3>
                                <p>${v.description}</p>
                                <a href="#" class="btn">order now</a>
                            </div>
                            <div class="image">
                                <img src="images/Product/${v.image}" alt="${v.name}">
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div class="swiper-pagination"></div>
        </section>
        <br><!-- comment -->
        <br><!-- comment -->
        <br><!-- comment -->
        <br><!-- comment -->
        <br><!-- comment -->
        <br><!-- comment -->
        <br><!-- comment -->
        <br><!-- comment -->
        <section class="dishes" id="dishes">
            <div class="container">
                <div class="row">
                    <!-- Category Section -->
                    <jsp:include page="Category.jsp"></jsp:include>
                        <form name="f" action="" method="post">
                            <!-- Product Section -->
                            <div class="col-sm-9">
                                <div class="box-container">
                                <c:forEach items="${requestScope.ListPP}" var="o">
                                    <c:set var="id" value="${o.id}"/>
                                    <div class="box">
                                        <a href="#" class="fas fa-heart"></a>
                                        <a href="detail?product_id=${id}" class="fas fa-eye"></a>
                                        <img src="images/Product/${o.image}" alt="${o.name}">
                                        <h3>${o.name}</h3>
                                        <div class="stars">
                                            <i class="fas fa-star"></i>
                                            <i class="fas fa-star"></i>
                                            <i class="fas fa-star"></i>
                                            <i class="fas fa-star"></i>
                                            <i class="fas fa-star-half-alt"></i>
                                        </div>
                                        <p><span>${o.price} đ</span></p>
                                        <form action="cart" method="post">
                                            <input type="hidden" name="id" value="${id}"/>
                                            <input type="hidden" name="num" value="1"/>
                                            <input type="button" onclick="buy('${id}')" class="btn" value="Add to cart" />
                                        </form>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="pagination-container col-md-12">
                            <ul class="pagination justify-content-center">
                                <c:forEach begin="1" end="${endP}" var="i">
                                    <li class="page-item ${param.index == i ? 'active' : ''}">
                                        <a class="page-link" href="shop?index=${i}">${i}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </form>   
                </div>
            </div>
        </section>


        <script type="text/javascript">
            function buy(id) {
                var m = document.f.num.value;
                document.f.action = "buy?id=" + id + "&num=" + m;
                document.f.submit();
            }
        </script>
        <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>

        <!-- custom js file link  -->
        <script src="js/script.js"></script>

    </body>
</html>
