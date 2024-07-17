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
        <jsp:include page="Hearder.jsp"></jsp:include>

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
                <div id="map1" class="map" style="display: none;">
                    <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d7447.391251837471!2d105.7419431935791!3d21.044861400000002!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x313455a6ee568eb5%3A0xe7e52b19cdc8e5df!2zMjkgbmfDtSA5MSDEkcaw4budbmcgQ-G6p3UgRGnhu4Vu!5e0!3m2!1sen!2s!4v1721220382615!5m2!1sen!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
                </div>
                <div id="map2" class="map" style="display: none;">
                    <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3724.204720899922!2d105.84483180963136!3d21.0244930878466!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3135ab93f00cf045%3A0xb4218f3976b2f51!2zNDggUC4gTMO9IFRoxrDhu51uZyBLaeG7h3QsIFRy4bqnbiBIxrBuZyDEkOG6oW8sIEhvw6BuIEtp4bq_bSwgSMOgIE7hu5lpLCBWaWV0bmFt!5e0!3m2!1sen!2s!4v1721220573295!5m2!1sen!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
                </div>
                <div id="map3" class="map" style="display: none;">
                    <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d14896.368078993522!2d105.82603633403781!3d21.029003756147045!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3135abd583392acf%3A0x2e8fadd56e611f10!2sQua%CC%81n%20Ngon%20169!5e0!3m2!1sen!2s!4v1721220608802!5m2!1sen!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
                </div>
            </div>
        </section>

        <jsp:include page="Footer.jsp"></jsp:include>

            <script>
                function goBack() {
                    window.history.back();
                }
                function showMap(restaurantId) {
                    document.getElementById("map1").style.display = "none";
                    document.getElementById("map2").style.display = "none";
                    document.getElementById("map3").style.display = "none";

                    if (restaurantId === 1) {
                        document.getElementById("map1").style.display = "block";
                    } else if (restaurantId === 2) {
                        document.getElementById("map2").style.display = "block";
                    } else if (restaurantId === 3) {
                        document.getElementById("map3").style.display = "block";
                    }
                }

                // Show the map for the current restaurant
                var restaurantId = ${restaurant.id};
                showMap(restaurantId);
        </script>
    </body>
</html>
