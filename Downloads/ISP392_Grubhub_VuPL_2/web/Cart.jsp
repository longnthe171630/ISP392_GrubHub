<%@ page import="java.util.List" %>
<%@ page import="model.CartItem" %>
<%@ page import="model.Product" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart</title>
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

        </style>
    </head>
    <body>
        <jsp:include page="Hearder.jsp"></jsp:include>
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
                                                <div class="py-2 text-uppercase">Số Lượng</div>
                                            </th>
                                            <th scope="col" class="border-0 bg-light">
                                                <div class="py-2 text-uppercase">Xóa</div>
                                            </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="item" items="${sessionScope.cart.items}">
                                        <tr>
                                            <td>
                                                <div class="d-flex align-items-center">
                                                    <img src="images/Product/${item.product.image}" alt="product" width="70" class="mr-3">
                                                    <div>
                                                        <h5 class="mb-0"><a href="#" class="text-dark">${item.product.name}</a></h5>
                                                    </div>
                                                </div>
                                            </td>
                                            <td class="align-middle"><strong>${item.product.price}</strong></td>
                                            <td class="align-middle">
                                                <form action="process" method="post" class="d-flex align-items-center">
                                                    <button class="btnSub"><a href="process?num=-1&id=${item.product.id}">-</a></button>
                                                    <input type="text" readonly value="${item.quantity}" class="quantity-input">
                                                    <button class="btnSub"><a href="process?num=1&id=${item.product.id}">+</a></button>
                                                </form>
                                            </td>

                                            <td class="align-middle">
                                                <form action="process" method="post">
                                                    <input type="hidden" name="action" value="delete"/>
                                                    <input type="hidden" name="id" value="${item.product.id}"/>
                                                    <button type="submit" class="btn btn-danger">Delete</button>
                                                </form>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="row py-4 px-3 bg-white rounded shadow-sm">
                    <div class="col-lg-6">
                        <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Voucher</div>
                        <div class="p-4">
                            <form action="applyvoucher" method="POST">
                                <div class="input-group mb-4 border rounded-pill p-2">
                                    <input type="text" name="voucherCode" placeholder="Nhập Voucher" aria-describedby="button-addon3" class="form-control border-0">
                                    <div class="input-group-append border-0">
                                        <button id="button-addon3" type="submit" class="btn btn-dark px-4 rounded-pill">
                                            <i class="fa fa-gift mr-2"></i>Sử dụng
                                        </button>
                                    </div>
                                </div>
                            </form>
                            <c:if test="${not empty sessionScope.voucherMessage}">
                                <div class="alert alert-info">${sessionScope.voucherMessage}</div>
                            </c:if>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Thành tiền</div>
                        <div class="p-4">
                            <ul class="list-unstyled mb-4">
                                <li class="d-flex justify-content-between py-3 border-bottom">
                                    <strong class="text-muted">Tổng tiền hàng</strong>
                                    <strong>${sessionScope.cart.getTotalMoney()}đ</strong>
                                </li>
                                <li class="d-flex justify-content-between py-3 border-bottom">
                                    <strong class="text-muted">Phí vận chuyển</strong>
                                    <strong>Free ship</strong>
                                </li>
                                <c:if test="${sessionScope.cart.discount > 0}">
                                    <li class="d-flex justify-content-between py-3 border-bottom">
                                        <strong class="text-muted">Giảm giá</strong>
                                        <strong>-${sessionScope.cart.getDiscountAmount()}đ</strong>
                                    </li>
                                </c:if>

                                <li class="d-flex justify-content-between py-3 border-bottom">
                                    <strong class="text-muted">Tổng thanh toán</strong>
                                    <h5 class="font-weight-bold">${sessionScope.cart.getDiscountedTotalMoney()}đ</h5>
                                </li>
                            </ul>
                            <form action="checkout" method="POST">
                                <input type="submit" onclick="purchasesuccess()" class="btn btn-dark rounded-pill py-2 btn-block" value="Mua hàng">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="Footer.jsp"></jsp:include>
            <script>
                function purchasesuccess() {
                    // Assume you have a variable indicating the success of the purchase
                    var purchaseSuccess = <%= request.getAttribute("purchaseSuccess") %>;

                    if (purchaseSuccess) {
                        // Display a success message
                        alert("Purchase failed. Please try again.");
                        // Redirect to the cart or another page if needed
                        window.location = "cart";
                    } else {
                        // Handle the case where the purchase was not successful (optional)
                        alert("Purchase successful! Thank you for shopping with us.");
                    }
                }
        </script>
        <script>
            // Hàm để hiển thị thông báo khi số lượng sản phẩm đã hết
            function showOutOfStockMessage() {
                alert("Sản phẩm này đã hết hàng hoặc không đủ số lượng.");
            }
            if ("<%= request.getAttribute("outofstock") %>" !== "") {
                showOutOfStockMessage(); // Hiển thị thông báo
            }
        </script>
    </body>
</html>
