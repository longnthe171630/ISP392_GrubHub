<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Restaurant Page</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="css/style_2.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <link rel="stylesheet" href="css/style.css">
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
        <div class="container">
            <h2 class="my-4">Restaurants</h2>
            <div class="row">
                <c:forEach items="${requestScope.listR}" var="o">
                    <div class="box">
                        <a href="#" class="fas fa-heart"></a>
                        <a href="restaurantDetails?id=${o.id}" >
                            <h3>${o.name}</h3>

                        </a>
                        <h3>${o.phonenumber}</h3>
                        <div class="stars">
                            <c:forEach begin="1" end="5" var="i">
                                <i class="<c:choose>
                                       <c:when test="${i <= o.restaurant_rating}">fas fa-star</c:when>
                                       <c:when test="${i > o.restaurant_rating && i <= o.restaurant_rating + 0.5}">fas fa-star-half-alt</c:when>
                                       <c:otherwise>far fa-star</c:otherwise>
                                   </c:choose>"></i>
                            </c:forEach>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
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
