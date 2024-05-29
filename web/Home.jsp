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
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>GrubHub</title>
        <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />
        <!-- font awesome cdn link  -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <!-- custom css file link  -->
        <link rel="stylesheet" href="css/style.css">
        <script src="https://maps.googleapis.com/maps/api/js?key=your_actual_api_key_here&callback=initMap" async defer></script>
    </head>
    <body>
        <!-- header section starts      -->
        <header>
            <a href="home" class="logo"><i class="fas fa-utensils"></i>GrubHub</a>

            <nav class="navbar">
                <a class="active" href="home">Home</a>
                <a href="About.jsp">About</a>
                <a href="Contact.jsp">Contact</a>
            </nav>

            <div class="icons">
                <i class="fas fa-bars" id="menu-bars"></i>
                <i class="fas fa-search" id="search-icon"></i>
                <a href="#" class="fas fa-shopping-cart"></a>
                <a href="login" class="login-btn">login</a>

            </div> 
        </header>
        <!-- header section ends-->

        <!-- search form  -->
        <form action="search" method="post" id="search-form">
            <input value="${txtS}" name="txt" type="search" placeholder="What do you want?" id="search-box">
            <label for="search-box" class="fas fa-search"></label>
            <i class="fas fa-times" id="close"></i>
        </form>

        <!-- home section starts  -->

        <section class="home" id="home">

            <div class="swiper-container home-slider">

                <div class="swiper-wrapper wrapper">

                    <div class="swiper-slide slide">
                        <div class="content">
                            <!-- <span>Gà rán KFC</span>-->
                            <h3>Gà rán KFC</h3>
                            <p>Ăn tối chưa KFC tận cửa</p>
                            <a href="#" class="btn">order now</a>
                        </div>
                        <div class="image">
                            <img src="images/home-img-4.jpg" alt="">
                        </div>
                    </div>

                    <div class="swiper-slide slide">
                        <div class="content">
                            <!--                    <span>our special dish</span>-->
                            <h3>Cơm thố Anh Nguyễn</h3>
                            <p>Những món ăn tại Cơm Thố ANH NGUYỄN phù hợp với tất cả mọi người </p>
                            <a href="#" class="btn">order now</a>
                        </div>
                        <div class="image">
                            <img src="images/home-img-5.jpg" alt="">
                        </div>
                    </div>

                    <div class="swiper-slide slide">
                        <div class="content">
                            <!--                    <span>our special dish</span>-->
                            <h3>PizzaHut</h3>
                            <p>the best ever pizza whose test is the best of the city </p>
                            <a href="#" class="btn">order now</a>
                        </div>
                        <div class="image">
                            <img src="images/home-img-3.png" alt="">>
                        </div>
                    </div>


                    <div class="swiper-slide slide">
                        <div class="content">
                            <!--                    <span>our special dish</span>-->
                            <h3>trà sữa</h3>
                            <p>the best ever milk whose test is the best of the city </p>
                            <a href="#" class="btn">order now</a>
                        </div>
                        <div class="image">
                            <img src="images/home-img-6.jpg" alt="">

                        </div>
                    </div>

                    <div class="swiper-slide slide">
                        <div class="content">
                            <!--                    <span>our special dish</span>-->
                            <h3>Chè</h3>
                            <p>the best ever milk whose test is the best of the city </p>
                            <a href="#" class="btn">order now</a>
                        </div>
                        <div class="image">
                            <img src="images/home-img-7.jpg" alt="">
                        </div>
                    </div>

                </div>



            </div>

            <div class="swiper-pagination"></div>

        </div>

    </section>



    <!-- home section ends -->

    <!-- dishes section starts  -->

    <section class="dishes" id="dishes">
        <h1 class="heading"> Categories </h1>
        <div class="container">
            <div class="row">
                <!-- Category Section -->
                <jsp:include page="Category.jsp"></jsp:include>
                    <!-- Product Section -->
                    <div class="col-sm-9">
                        <div class="box-container">
                        <c:forEach items="${requestScope.listP}" var="o">
                            <div class="box">
                                <a href="#" class="fas fa-heart"></a>
                                <a href="detail?id=${o.id}" class="fas fa-eye"></a>
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
                                <a href="#" class="btn">add to cart</a>
                            </div>
                        </c:forEach>
                    </div>
                </div>
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

            <div class="box">
                <div class="image">
                    <img src="images/menu-2.jpg" alt="">
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
                    <h3>Hamberger</h3>
                    <!--                    <p>quality of the food is best in the city</p>-->
                    <p><span class="price">50000đ</span></p>
                    <a href="#" class="btn">add to cart</a>
                </div>
            </div>

            <div class="box">
                <div class="image">
                    <img src="images/menu-3.jpg" alt="">
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
                    <h3>Crep</h3>
                    <!--                    <p>a delicous food with a very good test.</p>-->
                    <p><span class="price">35000đ</span></p>
                    <a href="#" class="btn">add to cart</a>
                </div>
            </div>

            <div class="box">
                <div class="image">
                    <img src="images/menu-4.jpg" alt="">
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
                    <h3>Crep</h3>
                    <!--                    <p>test of best mumma and our food</p>-->
                    <p><span class="price">35000đ</span></p>
                    <a href="#" class="btn">add to cart</a>
                </div>
            </div>

            <div class="box">
                <div class="image">
                    <img src="images/menu-5.jpg" alt="">
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
                    <h3>delicious food</h3>
                    <!--                    <p>test is best mumma and our restorent</p>-->
                    <p><span class="price">35000đ</span></p>
                    <a href="#" class="btn">add to cart</a>
                </div>
            </div>

            <div class="box">
                <div class="image">
                    <img src="images/menu-6.jpg" alt="">
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
                    <h3>delicious food</h3>
                    <!--                    <p>sweet testy and yummy food </p>-->
                    <p><span class="price">35000đ</span></p>
                    <a href="#" class="btn">add to cart</a>
                </div>
            </div>

            <div class="box">
                <div class="image">
                    <img src="images/menu-7.jpg" alt="">
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
                    <h3>delicious food</h3>
                    <!--                    <p>The food is  very good, with a great variety of everyone!</p>-->
                    <p><span class="price">35000đ</span></p>
                    <a href="#" class="btn">add to cart</a>
                </div>
            </div>

            <div class="box">
                <div class="image">
                    <img src="images/menu-8.jpg" alt="">
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
                    <h3>delicious food</h3>
                    <!--                    <p>dont be hurry just give your time to test our serviece</p>-->
                    <p><span class="price">100000đ</span></p>
                    <a href="#" class="btn">add to cart</a>
                </div>
            </div>

            <div class="box">
                <div class="image">
                    <img src="images/menu-9.jpg" alt="">
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
                    <h3>delicious food</h3>
                    <!--                    <p>we serve the bestever our dish which is the best </p>-->
                    <p><span class="price">50000đ</span></p>
                    <a href="#" class="btn">add to cart</a>
                </div>
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
    <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>

    <!-- custom js file link  -->
    <script src="js/script.js"></script>

</body>
</html>