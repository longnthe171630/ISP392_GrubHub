<%-- 
    Document   : restaurantorderdetail
    Created on : Jun 17, 2024, 8:43:32 AM
    Author     : phaml
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.*" %> 
<%@ page import="java.util.*" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order detail</title>
        <link rel="stylesheet" href="css/style_ship.css">
        <style>
            /* Reset CSS */
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }

            /* Body styles */
            body {
                font-family: Arial, sans-serif;
                line-height: 1.6;
                background-color: #f5f5f5;
                padding: 20px;
            }

            /* Container styles */
            .container {
                max-width: 800px;
                margin: 0 auto;
                background-color: #fff;
                padding: 20px;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            /* Header styles */
            h2 {
                font-size: 24px;
                margin-bottom: 20px;
                border-bottom: 2px solid #ccc;
                padding-bottom: 5px;
            }

            /* Customer details styles */
            .customer-details {
                margin-bottom: 20px;
            }

            .customer-details p {
                margin-bottom: 10px;
            }

            /* Table styles */
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }

            table th, table td {
                border: 1px solid #ccc;
                padding: 8px;
                text-align: left;
            }

            /* Table header styles */
            table th {
                background-color: #f2f2f2;
            }

            /* Table body styles */
            table td {
                background-color: #fff;
            }

            /* Responsive styles */
            @media (max-width: 600px) {
                .container {
                    padding: 10px;
                }

                table {
                    font-size: 14px;
                }
            }
        </style>
    </head>
    <body>
        <div class="container">
            <%-- Lấy các thuộc tính từ servlet --%>
            <%
                Map<Product, Integer> map = (Map<Product, Integer>) request.getAttribute("map");
                Order order = (Order) request.getAttribute("order");
                Customer customer = (Customer) request.getAttribute("customer");
                List<Notification> ln = (List<Notification>) request.getAttribute("listnotification");
            %>

            <h2>Chi tiết đơn hàng</h2>
            <div class="customer-details">
                <p><strong>Tên khách hàng:</strong> ${customer.name}</p>
                <p><strong>Địa chỉ:</strong> ${address.details}, ${address.state}, ${address.street}</p>
                <p><strong>Email:</strong> ${account.email}</p>
                <p><strong>Số điện thoại:</strong> ${account.phonenumber}</p>
                <p><strong>Trang thái đơn hàng:</strong> ${order.status}</p>
            </div>

            <h3>Đơn hàng:</h3>

            <table>
                <thead>
                    <tr>
                        <th>Sản phẩm</th>
                        <th>Số lượng</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="entry" items="${map}">
                        <tr>
                            <td>${entry.key.name}</td>
                            <td>${entry.value}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <c:choose>
                <c:when test="${order.status == 'Từ chối'}">
                    <h3>Nguyên nhân từ chối:</h3>
                    <ul>
                        ${notification}
                    </ul>
                </c:when>
            </c:choose>


        </div>
    </body>
</html>
