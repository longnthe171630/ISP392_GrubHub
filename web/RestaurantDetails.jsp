<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Restaurant Details</title>
        <link rel="stylesheet" type="text/css" href="styles.css">
        <title>GrubHub</title>
        <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />
        <!-- font awesome cdn link  -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <!-- custom css file link  -->
        <link rel="stylesheet" href="css/style.css">
        <script src="https://maps.googleapis.com/maps/api/js?key=your_actual_api_key_here&callback=initMap" async defer></script>
    </head>
    <body>
        <header>
            <a href="home" class="logo"><i class="fas fa-utensils"></i>GrubHub</a>
            <nav class="navbar">
                <a class="active" href="home">Home</a>
                <a href="restaurant">Restaurant</a>
                <a href="About.jsp">About</a>
                <a href="Contact.jsp">Contact</a>
            </nav>
            <div class="icons">
                <i class="fas fa-bars" id="menu-bars"></i>
                <i class="fas fa-search" id="search-icon"></i>
                <a href="#" class="fas fa-heart"></a>
                <a href="#" class="fas fa-shopping-cart"></a>
                <a href="login" class="login-btn">login</a>
            </div> 
        </header>
        <br><!-- comment -->
        <br><!-- comment -->
        <br><!-- comment -->
        <br><!-- comment -->
        <br><!-- comment /-->
        <br><!-- comment -->
        <br><!-- comment -->


        <h1>Restaurant Details: ${restaurant.name}</h1>
        <div class="restaurant-info">
            <p>Address ID: ${restaurant.address_id}</p>
            <p>Phone Number: ${restaurant.phonenumber}</p>
            <p>Rating: ${restaurant.restaurant_rating}</p>
        </div>
        <h2>Food Menu</h2>
        <section class="dishes" id="dishes">
            <div class="container">
                <div class="row">
                    <!-- Category Section -->
                    <!-- Product Section -->
                    <div class="col-sm-9">
                        <div class="box-container">
                            <c:forEach items="${requestScope.productList}" var="product">
                                <div class="box">
                                    <a href="#" class="fas fa-heart"></a>
                                    <a href="detail?product_id=${product.id}" class="fas fa-eye"></a>
                                    <img src="images/Product/${product.image}" alt="${product.name}">
                                    <h3>${product.name}</h3>
                                    <div class="stars">
                                        <i class="fas fa-star"></i>
                                        <i class="fas fa-star"></i>
                                        <i class="fas fa-star"></i>
                                        <i class="fas fa-star"></i>
                                        <i class="fas fa-star-half-alt"></i>
                                    </div>
                                    <p><span>${product.price} đ</span></p>
                                    <a href="#" class="btn">add to cart</a>
                                </div>
                            </c:forEach>
                        </div>
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

        <jsp:include page="Footer.jsp"></jsp:include>
    </body>
</html>
