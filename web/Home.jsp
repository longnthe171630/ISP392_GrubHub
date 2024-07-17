<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="icon" href="images/icon/logo.png" type="image/x-icon">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>GrubHub</title>
    <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
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
</head>
<body>
    <!-- header section starts -->
    <jsp:include page="Hearder.jsp"></jsp:include>
    
    <!-- home section starts -->
    <section class="home" id="home">
        <div class="swiper-container home-slider">
            <div class="swiper-wrapper wrapper">
                <c:forEach items="${requestScope.listPC}" var="v">
                    <div class="swiper-slide slide">
                        <div class="content">
                            <h3>${v.name}</h3>
                            <p>${v.description}</p>
                            <a href="#" class="btn">Order Now</a>
                        </div>
                        <div class="image">
                            <img src="images/Product/${v.image}" alt="${v.name}">
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="swiper-pagination"></div>
        </div>
    </section>
    <br><!-- comment -->
    <br><!-- comment -->
    <br><!-- comment -->
    <br><!-- comment -->
    <br><!-- comment -->
    <br><!-- comment -->
    <br><!-- comment -->
    <br><!-- comment -->
    <br><!-- comment -->
    <br><!-- comment -->
    <!-- home section ends -->

    <!-- dishes section starts -->
    <section class="dishes" id="dishes">
        <h1 class="heading">Categories</h1>
        <div class="container">
            <div class="row">
                <!-- Category Section -->
                <jsp:include page="Category.jsp"></jsp:include>

                <form name="f" action="" method="post">
                    <!-- Product Section -->
                    <div class="col-sm-9">
                        <div class="box-container">
                            <c:forEach items="${requestScope.listP}" var="o">
                                <div class="box">
                                    <a href="favorite?productId=${o.id}" class="fas fa-heart"></a>
                                    <a href="detail?product_id=${o.id}&restaurant_id=${o.restaurant.id}" class="fas fa-eye"></a>
                                    <img src="images/Product/${o.image}" alt="${o.name}">
                                    <h3>${o.name}</h3>
                                    <div class="stars">
                                        <c:forEach var="i" begin="1" end="5">
                                            <c:choose>
                                                <c:when test="${i <= o.rating}">
                                                    <i class="fas fa-star"></i>
                                                </c:when>
                                                <c:when test="${i > o.rating and i <= o_rating + 0.5}">
                                                    <i class="fas fa-star-half-alt"></i>
                                                </c:when>
                                                <c:otherwise>
                                                    <i class="far fa-star"></i>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </div>
                                    <p><span>${o.price} đ</span></p>
                                    <form action="show" method="post">
                                        <input type="hidden" name="id" value="${o.id}">
                                        <input type="hidden" name="restaurantId" value="${o.restaurant.id}">
                                        <input type="hidden" name="num" value="1">
                                        <input type="button" onclick="buy('${o.id}')" class="btn" value="Add to cart">
                                    </form>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </form>

                <!-- Pagination -->
                <div class="pagination-container col-md-12">
                    <ul class="pagination justify-content-center">
                        <c:forEach begin="1" end="${endP}" var="i">
                            <li class="page-item ${param.index == i ? 'active' : ''}">
                                <a class="page-link" href="home?index=${i}">${i}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </section>
    <!-- dishes section ends -->

    <!-- menu section starts -->
    <section class="dishes" id="dishes">
        <h3 class="sub-heading">Our Menu</h3>
        <h1 class="heading">Những món ăn được ưa thích</h1>
        <div class="box-container">
            <c:forEach items="${requestScope.listTop5}" var="t">
                <div class="box">
                    <div class="image">
                        <img src="images/Product/${t.image}" alt="${t.name}">
                    </div>
                    <div class="content">
                        <div class="stars">
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star"></i>
                            <i class="fas fa-star-half-alt"></i>
                        </div>
                        <h3>${t.name}</h3>
                        <p><span>${t.price} đ</span></p>
                        <form action="buy" method="post">
                            <input type="hidden" name="id" value="${t.id}">
                            <input type="hidden" name="num" value="1">
                            <input type="button" onclick="buy('${t.id}')" class="btn" value="Add to cart">
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
    </section>
    <!-- menu section ends -->

    <!-- map section -->
    <section id="map-section">
        <h1 class="heading">Our Location</h1>
        <div class="map-container">
            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3724.506341458941!2d105.52528919999999!3d21.012416699999996!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3135abc60e7d3f19%3A0x2be9d7d0b5abcbf4!2sFPT%20University!5e0!3m2!1sen!2s!4v1716697336875!5m2!1sen!2s"
                    width="800" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
        </div>
    </section>

    <!-- footer section starts -->
    <jsp:include page="Footer.jsp"></jsp:include>

    <!-- loader part -->
    <div class="loader-container">
        <img src="images/loader.gif" alt="">
    </div>

    <!-- custom js file link -->
    <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
    <script type="text/javascript">
        function buy(id) {
            document.f.action = "buy?id=" + id;
            document.f.submit();
        }
        var homeSlider = new Swiper('.home-slider', {
            // Optional parameters
            loop: true,
            autoplay: {
                delay: 5000,
            },
            pagination: {
                el: '.swiper-pagination',
                clickable: true,
            },
        });
    </script>
</body>
</html>
