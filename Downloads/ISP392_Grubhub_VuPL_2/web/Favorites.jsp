<%@ page import="java.util.List" %>
<%@ page import="model.Product" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Favorites</title>
        <link rel="icon" href="images/logo1.png"/>
        <link rel="stylesheet" href="css/style.css"> <!-- Assuming you have a custom CSS file -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
        <style>
            /* Custom CSS for shopping cart page */
            body {
                font-family: Arial, sans-serif;
                background-color: #f8f9fa;
            }
            .shopping-cart {
                background: #f8f9fa;
                padding: 30px 0;
            }
            .shopping-cart .table-responsive {
                min-height: 300px;
            }
            .shopping-cart table {
                width: 100%;
                background-color: #ffffff;
                border-collapse: collapse;
            }
            .shopping-cart table th, .shopping-cart table td {
                border: 1px solid #dee2e6;
                padding: 8px;
                vertical-align: middle;
                text-align: center; /* Center-align text in cells */
            }
            .shopping-cart table th {
                background-color: #f1f1f1;
            }
            .shopping-cart .quantity-input {
                width: 50px;
                text-align: center;
                margin: 0; /* Remove default margin */
            }
            .shopping-cart .btnSub {
                background-color: transparent;
                border: none;
                cursor: pointer;
            }
            .shopping-cart .btnSub a {
                color: #333;
                text-decoration: none;
                display: block;
                padding: 5px 10px;
            }
            .shopping-cart .btnSub a:hover {
                color: #fff;
                background-color: #007bff;
            }
            .shopping-cart .btn-danger {
                background-color: #dc3545;
                border-color: #dc3545;
            }
            .shopping-cart .btn-danger:hover {
                background-color: #c82333;
                border-color: #bd2130;
            }
            .shopping-cart .btn-dark {
                background-color: #343a40;
                border-color: #343a40;
            }
            .shopping-cart .btn-dark:hover {
                background-color: #23272b;
                border-color: #1d2124;
            }
            .shopping-cart .rounded-pill {
                border-radius: 50px!important;
            }
            .shopping-cart .btnSub {
                display: flex;
                justify-content: center;
                align-items: center;
                width: 30px; /* Điều chỉnh chiều rộng của button */
                height: 30px; /* Điều chỉnh chiều cao của button */
                margin: 0 5px; /* Khoảng cách giữa các nút */
            }

            .shopping-cart .btnSub a {
                color: #333;
                text-decoration: none;
            }

            .shopping-cart .btnSub a:hover {
                color: #fff;
                background-color: #007bff;
                border-radius: 50%;
            }
            .action-buttons {
                display: flex;
                gap: 10px;
                justify-content: center;
            }
        </style>
    </head>
    <body>
        <jsp:include page="Hearder.jsp"></jsp:include>
            <br><!-- comment -->
            <br><!-- comment -->
            <br><!-- comment -->
            <br><!-- comment -->
            <div class="shopping-cart">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="p-2 px-3 text-uppercase">Sản Phẩm</div>
                                            </th>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="py-2 text-uppercase">Đơn Giá</div>
                                            </th>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="py-2 text-uppercase">Đánh giá</div>
                                            </th>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="py-2 text-uppercase">Hành động</div>
                                            </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="product" items="${sessionScope.favorites}">
                                        <tr>
                                            <td>
                                                <div class="d-flex align-items-center">
                                                    <img src="images/Product/${product.image}" alt="product" width="70" class="mr-3">
                                                    <div>
                                                        <h5 class="mb-0"><a href="#" class="text-dark">${product.name}</a></h5>
                                                    </div>
                                                </div>
                                            </td>
                                            <td class="align-middle"><strong>${product.price}</strong></td>
                                            <td class="stars">
                                                <c:forEach var="i" begin="1" end="5">
                                                    <c:choose>
                                                        <c:when test="${i <= product.rating}">
                                                            <i class="fas fa-star"></i>
                                                        </c:when>
                                                        <c:when test="${i > product.rating and i <= product.rating + 0.5}">
                                                            <i class="fas fa-star-half-alt"></i>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <i class="far fa-star"></i>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </td>
                                            <td class="align-middle">
                                                <div class="action-buttons">
                                                    <form action="buy" method="post">
                                                        <input type="hidden" name="id" value="${product.id}">
                                                        <input type="hidden" name="num" value="1">
                                                        <button type="submit" class="btn btn-primary">Add to cart</button>
                                                    </form>
                                                    <form action="favorite" method="post">
                                                        <input type="hidden" name="action" value="delete">
                                                        <input type="hidden" name="productId" value="${product.id}">
                                                        <button type="submit" class="btn btn-danger">Delete</button>
                                                    </form>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    <c:if test="${empty sessionScope.favorites}">
                                        <tr>
                                            <td colspan="4">No favorites found.</td>
                                        </tr>
                                    </c:if>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="Footer.jsp"></jsp:include>
        <div class="loader-container">
            <img src="images/loader.gif" alt="">
        </div>
        <script>
            function confirmAction(action, productId) {
                var message = '';
                if (action === 'delete') {
                    message = 'Bạn có chắc chắn muốn xóa sản phẩm này?';
                }
                // Display confirmation dialog             
                return confirmResult;
            }
        </script>


    </body>
</html>
