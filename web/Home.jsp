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

        <jsp:include page="Hearder.jsp"></jsp:include>
            <!-- home section starts  -->

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

        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>


        <!-- home section ends -->

        <!-- dishes section starts  -->


        <section class="dishes" id="dishes">
    <h1 class="heading"> Categories </h1>
    <div class="container">
        <div class="row">
            <!-- Category Section -->
            <jsp:include page="Category.jsp"></jsp:include>
            <form name="f" action="" method="post">
                <!-- Product Section -->
                <div class="col-sm-9">
                    <div class="box-container">
                        <jsp:useBean id="db" class="dao.ProductDAO"/>
                        <c:forEach items="${requestScope.listPP}" var="o">
                            <div class="box">
                                <a href="#" class="fas fa-heart"></a>
                                <a href="detail?product_id=${o.id}" class="fas fa-eye"></a>
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
                                    <input type="hidden" name="id" value="${o.id}"/>
                                    <input type="hidden" name="num" value="1"/>
                                    <input type="button" onclick="buy('${o.id}')" class="btn" value="Add to cart" />
                                </form>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="pagination-container col-md-12">
                    <ul class="pagination justify-content-center">
                        <c:forEach begin="1" end="${endP}" var="i">
                            <li class="page-item ${param.index == i ? 'active' : ''}">
                                <a class="page-link" href="home?index=${i}">${i}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </form>   
        </div>
    </div>
</section>


        <!-- dishes section ends -->


        <!-- menu section starts  -->

        <section class="menu" id="menu">

            <h3 class="sub-heading"> our menu </h3>
            <h1 class="heading"> Những món ăn được ưa thích </h1>

            <div class="box-container">

                <div class="box">
                    <div class="image">
                        <img src="images/menu-1.jpg" alt="">
                        <a href="#" class="fas fa-heart"></a>
                    </div>
                    <div class="content">
                        <div class="stars">
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star-half-alt"></i>
                        </div>
                        <h3>Pizza</h3>
                        <!--                    <p>just come here and enjoy our food the test is so good.</p>-->
                        <p><span class="price">150000đ</span></p>
                        <a href="#" class="btn">add to cart</a>

                    </div>
                </div>

        </section>



        <section id="map-section">
            <h1 class="heading"> Our Location </h1>
            <div class="map-container">
                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3724.506341458941!2d105.52528919999999!3d21.012416699999996!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3135abc60e7d3f19%3A0x2be9d7d0b5abcbf4!2sFPT%20University!5e0!3m2!1sen!2s!4v1716697336875!5m2!1sen!2s" width="800" height="450" display: flex
                        align-items: center
                        justify-content: center style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
            </div>
        </section>

        <!-- menu section ends -->

        <!-- order section starts  -->

        <jsp:include page="Footer.jsp"></jsp:include>

        <!-- order section ends -->

        <!-- footer section starts  -->

        <!-- loader part  -->
        <div class="loader-container">
            <img src="images/loader.gif" alt="">
        </div>
        <script type="text/javascript">
            function buy(id) {
                document.f.action = "buy?id=" + id;
                document.f.submit();
            }
        </script>
        <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>

        <!-- custom js file link  -->
        <script src="js/script.js"></script>

    </body>
</html>
