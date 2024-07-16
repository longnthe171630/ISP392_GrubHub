<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link href="css/style_2.css" rel="stylesheet" type="text/css"/>
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
    <br><br><br><br><br><br><br><br>
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
                                                <div class="box">
                                                    <a href="restaurantDetails?restaurant_id=${res.id}">
                                                        <h3>${res.name}</h3>
                                                    </a>
                                                    <p>Phone: ${res.phonenumber}</p>
                                                    <p>Address: ${res.address}</p>
                                                </div>
                                            </div>
                                        </div>
                                    </dl>
                                    <hr>
                                    <div class="row">
                                        <div class="col-sm-5">
                                            <dl class="param param-inline">
                                                <dt>Quantity:</dt>
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
                                    <form action="buy" method="post">
                                        <input type="hidden" name="product_id" value="${detail.id}">
                                        <input type="number" name="num" value="1" min="0" max="${o.quantity}" style="display:none">
                                        <input type="submit" class="btn btn-lg btn-outline-primary text-uppercase" value="Add to cart"/>
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
    <jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>
