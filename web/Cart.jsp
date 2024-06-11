<%@ page import="java.util.List" %>
<%@ page import="model.CartItem" %>
<%@ page import="model.Product" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart</title>
        <link rel="icon" href="images/logo1.png"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
    </head>
    <body>
        <jsp:include page="Hearder.jsp"></jsp:include>
            <br>
            <br>
            <br>
            <br>
            <br>
            <div class="shopping-cart">
                <div class="px-4 px-lg-0">

                    <div class="pb-5">
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">

                                    <!-- Shopping cart table -->
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
                                            <c:set var="o" value="${sessionScope.cart}" />
                                            <c:set var="t" value="0" />
                                            <c:forEach items="${o.items}" var="i">
                                                <c:set var="t" value="${t + 1}" />
                                                <tr>
                                                    <th scope="row">
                                                        <div class="p-2">
                                                            <img src="images/${i.product.image}" alt="product" width="70">
                                                            <div class="ml-3 d-inline-block align-middle">
                                                                <h5 class="mb-0"> <a href="#" class="text-dark d-inline-block">${i.product.name}</a>
                                                                </h5><span class="text-muted font-weight-normal font-italic"></span>
                                                            </div>
                                                        </div>
                                                    </th>
                                                    <td class="align-middle"><strong>${i.product.price}</strong></td>
                                                    <td class="align-middle">
                                                    <td>
                                                        <button class="btnSub"><a href="process?num=-1&id=${i.product.id}">-</a></button>
                                                        <input type="text" readonly value="${i.quantity}"/>
                                                        <button class="btnSub"><a href="process?num=1&id=${i.product.id}">+</a></button>
                                                    </td>

                                                    <td class="align-middle"><a href="#" class="text-dark">
                                                            <form action="process" method="post">
                                                                <input type="hidden" name="id" value="${i.product.id}">
                                                                <input type="submit"  class="btn btn-danger" value="Delete">
                                                            </form>
                                                        </a>
                                                    </td>
                                                </tr> 
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <!-- End -->
                            </div>
                        </div>

                        <div class="row py-5 p-4 bg-white rounded shadow-sm">
                            <div class="col-lg-6">
                                <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Voucher</div>
                                <div class="p-4">
                                    <div class="input-group mb-4 border rounded-pill p-2">
                                        <input type="text" placeholder="Nhập Voucher" aria-describedby="button-addon3" class="form-control border-0">
                                        <div class="input-group-append border-0">
                                            <button id="button-addon3" type="button" class="btn btn-dark px-4 rounded-pill"><i class="fa fa-gift mr-2"></i>Sử dụng</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Thành tiền</div>
                                <div class="p-4">
                                    <ul class="list-unstyled mb-4">
                                        <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Tổng tiền hàng</strong><strong>${sessionScope.cart.getTotalMoney()}đ</strong></li>
                                        <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Phí vận chuyển</strong><strong>Free ship</strong></li>
                                        <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Tổng thanh toán</strong>
                                            <h5 class="font-weight-bold">${sessionScope.cart.getTotalMoney()}đ</h5>
                                        </li>
                                    </ul>
                                    <form action="checkout" method="POST">
                                        <input type="submit" onclick="purchasesuccess()" class="btn btn-dark rounded-pill py-2 btn-block"  value="Mua hàng">
                                    </form>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <%@ include file="Footer.jsp"%>
        <div class="modal fade" id="modal_box" role="dialog"></div>
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
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script type="text/javascript" src="js/clickevents.js"></script>
    </body>
</html>
