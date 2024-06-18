<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Restaurant Details</title>
        <link rel="stylesheet" type="text/css" href="styles.css">
        <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
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

        <br><br><br><br><br><br><br>

        <div class="container">
            <div class="back-button">
                <button onclick="goBack()">Back</button>
            </div>
            <div class="row">
                <div class="col-sm-9">
                    <div class="box-container">
                        <div class="box">
                            <h1>Restaurant Details: ${restaurant.name}</h1>
                            <p>Address: ${restaurant.address.details}, ${restaurant.address.state}, ${restaurant.address.street}</p>
                            <p>Phone Number: ${restaurant.phonenumber}</p>
                            <p>Rating: 
                            <div class="stars">
                                <c:forEach var="i" begin="1" end="5">
                                    <c:choose>
                                        <c:when test="${i <= restaurant.restaurant_rating}">
                                            <i class="fas fa-star"></i>
                                        </c:when>
                                        <c:when test="${i > restaurant.restaurant_rating and i <= restaurant.restaurant_rating + 0.5}">
                                            <i class="fas fa-star-half-alt"></i>
                                        </c:when>
                                        <c:otherwise>
                                            <i class="far fa-star"></i>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </div>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <h1 class="heading"> Food menu </h1>
        <section class="dishes" id="dishes">
            <div class="container">
                <div class="row">
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
                                    <form action="show" method="post">
                                        <input type="hidden" name="id" value="${product.id}"/>
                                        <input type="hidden" name="num" value="1"/>
                                        <input type="button" onclick="buy('${product.id}')" class="btn" value="Add to cart" />
                                    </form>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </section>


        <jsp:include page="Footer.jsp"></jsp:include>

        <script type="text/javascript">
            function buy(id) {
                var m = document.f.num.value;
                document.f.action = "buy?id=" + id + "&num=" + m;
                document.f.submit();
            }
        </script>
        <script>
            function goBack() {
                window.history.back();
            }
        </script>
    </body>
</html>
