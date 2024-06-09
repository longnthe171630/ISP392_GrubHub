<%@ page import="java.util.List" %>
<%@ page import="model.CartItem" %>
<%@ page import="model.Product" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Cart</title>
        <link rel="stylesheet" type="text/css" href="css/cart.css">
    </head>
    <body>
        <h1>Giỏ hàng của bạn</h1>
        <c:if test="${not empty cartItems}">
            <table>
                <thead>
                    <tr>
                        <th>Sản phẩm</th>
                        <th>Giá</th>
                        <th>Số lượng</th>
                        <th>Thành tiền</th>
                        <th>Hành động</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${cartItems}">
                        <tr>
                            <td>${item.getProduct().getName()}</td>
                            <td>${item.getProduct().getPrice()} đ</td>
                            <td>
                                <form action="cart" method="post">
                                    <input type="hidden" name="action" value="update">
                                    <input type="hidden" name="productId" value="${item.getProduct().getId()}">
                                    <input type="number" name="quantity" value="${item.getQuantity()}" min="1">
                                    <button type="submit">Cập nhật</button>
                                </form>
                            </td>
                            <td>${item.getProduct().getPrice() * item.getQuantity()} đ</td>
                            <td>
                                <form action="cart" method="post">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="productId" value="${item.getProduct().getId()}">
                                    <button type="submit">Xóa</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
                <tfoot>
                    <tr>
                        <td colspan="3">Tổng cộng</td>
                        <td colspan="2">${total} đ</td>
                    </tr>
                </tfoot>
            </table>
            <a href="checkout.jsp">Thanh toán</a>
        </c:if>
        <c:if test="${empty cartItems}">
            <p>Giỏ hàng của bạn trống.</p>
        </c:if>
    </body>
</html>
