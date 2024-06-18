<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="css/style_2.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <link rel="stylesheet" href="css/style.css">

        <style>
            .gallery-wrap .img-big-wrap img {
                max-height: 450px;
                width: auto;
                display: inline-block;
                cursor: zoom-in;
            }
            .gallery-wrap .img-small-wrap .item-gallery {
                width: 60px;
                height: 60px;
                border: 1px solid #ddd;
                margin: 7px 2px;
                display: inline-block;
                overflow: hidden;
            }
            .gallery-wrap .img-small-wrap {
                text-align: center;
            }
            .gallery-wrap .img-small-wrap img {
                max-width: 100%;
                max-height: 100%;
                object-fit: cover;
                border-radius: 4px;
                cursor: zoom-in;
            }
            .img-big-wrap img {
                width: 100% !important;
                height: auto !important;
            }
        </style>

    </head>
    <body>
        <jsp:include page="Hearder.jsp"></jsp:include>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <div class="back-button">
                <button onclick="goBack()">Back</button>
            </div>
            <div class="container">

                <div class="row">
                    <div class="col-sm-9">
                        <div class="container">
                            <div class="card">
                                <div class="row">
                                    <aside class="col-sm-5 border-right">
                                        <article class="gallery-wrap"> 
                                            <div class="img-big-wrap">
                                                <div>
                                                    <a href="#"><img src="images/Product/${detail.image}" alt="${detail.name}"></a>
                                            </div>
                                        </div>
                                        <div class="img-small-wrap">
                                        </div>
                                    </article>
                                </aside>
                                <aside class="col-sm-7">
                                    <article class="card-body p-5">
                                        <h3 class="title mb-3">${detail.name}</h3>
                                        <p class="price-detail-wrap"> 
                                            <span class="price h3 text-warning"> 
                                                <span class="currency"></span><span class="num">${detail.price}Đ</span>
                                            </span> 
                                        </p>
                                        <dl class="item-property">
                                            <dt>Description</dt>
                                            <dd><p>${detail.description}</p></dd>
                                            <div class="container">
                                                <div class="row">
                                                    <jsp:useBean id="db" class="dao.RestaurantDAO"/>
                                                    <c:set var="restaurant" value="${db.getRestaurantByPID(detail.id)}"/>

                                                    <div class="box col-sm-12">
                                                        <a href="restaurantDetails?id=${restaurant.id}">
                                                            <h3>${restaurant.name}</h3></a>
                                                        <div class="stars">
                                                            <c:forEach var="i" begin="1" end="5">
                                                                <c:choose>
                                                                    <c:when test="${i <= restaurant.restaurant_rating}">
                                                                        <i class="fas fa-star"></i>
                                                                    </c:when>
                                                                    <c:when test="${i > restaurant.restaurant_rating and i <= restaurant.restaurant_rating}">
                                                                        <i class="fas fa-star-half-alt"></i>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <i class="far fa-star"></i>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:forEach>
                                                        </div>
                                                    </div>


                                                </div>
                                            </div>
                                        </dl>
                                        <hr>
                                        <div class="row">
                                            <div class="col-sm-5">
                                                <dl class="param param-inline">
                                                    <dt>Quantity: </dt>
                                                    <dd>
                                                        <select class="form-control form-control-sm" style="width:70px;">
                                                            <option>1</option>
                                                            <option>2</option>
                                                            <option>3</option>
                                                        </select>
                                                    </dd>
                                                </dl>
                                            </div>
                                        </div>
                                        <hr>


                                        <form action="show" method="post">
                                            <input type="hidden" name="id" value="${detail.id}"/>
                                            <input type="hidden" name="num" value="1"/>
                                            <input type="button" onclick="buy('${detail.id}')" class="btn" value="Add to cart" />
                                        </form>
                                    </article>
                                </aside>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            function goBack() {
                window.history.back();
            }
        </script>
        <script type="text/javascript">
            function buy(id) {
                var m = document.f.num.value;
                document.f.action = "buy?id=" + id + "&num=" + m;
                document.f.submit();
            }
        </script>
        <jsp:include page="Footer.jsp"></jsp:include>
    </body>
</html>