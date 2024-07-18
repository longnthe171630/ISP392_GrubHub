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
        %>

        <h2>Chi tiết đơn hàng</h2>
        <div class="customer-details">
            <p><strong>Tên khách hàng:</strong> ${customer.name}</p>
            <p><strong>Địa chỉ:</strong> ${address.details}, ${address.state}, ${address.street}</p>
            <p><strong>Email:</strong> ${account.email}</p>
            <p><strong>Số điện thoại:</strong> ${account.phonenumber}</p>
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
                <%-- Iterate over the map of products and quantities --%>
                <c:forEach var="entry" items="${map}">
                    <tr>
                        <td>${entry.key.name}</td>
                        <td>${entry.value}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <%-- Form để xác nhận chấp nhận đơn hàng --%>
        <form id="acceptForm" action="restauranorderdetail" method="POST">
            <input type="hidden" name="order_id" value="${order.id}">
            <input type="hidden" id="actionInput" name="action" value="">
            <input type="hidden" id="reasonInput" name="reason" value="">
            <div>    
                <h3>Bạn có muốn nhận đơn này không?</h3>
                <button type="button" onclick="confirmAccept()" name="accept">Chấp nhận</button>
                <button type="button" onclick="confirmReject()" name="reject">Từ chối</button>
            </div>
        </form>
    </div>
</body>

<script>
    function confirmAccept() {
        var result = confirm("Bạn có muốn chấp nhận đơn hàng này không?");
        if (result) {
            document.getElementById("actionInput").value = "accept";
            document.getElementById("acceptForm").submit();
        }
    }

    function confirmReject() {
        var result = confirm("Bạn có chắc chắn muốn từ chối đơn hàng này không?");
        if (result) {
            var reason = prompt("Vui lòng nhập lý do từ chối:");
            if (reason != null && reason.trim() !== "") {
                document.getElementById("actionInput").value = "reject";
                document.getElementById("reasonInput").value = reason;
                document.getElementById("acceptForm").submit();
            } else {
                alert("Lý do từ chối không được để trống!");
            }
        }
    }
</script>
</html>
