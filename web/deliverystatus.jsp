<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- ======= Styles ====== -->
        <link rel="stylesheet" href="css/style_ship.css">
    </head>

    <body>
        <h2>Order details</h2><br>

        <div class="info-container">
            <div class="order-info">
                <p><strong>Code: </strong> ${order1.id}</p><br>

                <p><strong>Restaurant: </strong> ${order1.res_name}</p>
                <p><strong>Contact: </strong> ${order1.res_phone}</p>
                <p><strong>Address: </strong> ${order2.fromAddress}</p><br>

                <p><strong>Customer: </strong> ${order1.cus_name}</p>
                <p><strong>Contact: </strong> ${order1.cus_phone}</p>
                <p><strong>Address: </strong> ${order2.toAddress}</p><br>

                <p><strong>Status: </strong> ${delivery1.status}</p>
            </div>

            <div class="image-container">
                <form action="deliverystatus" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="id" value="${order1.id}">
                    <c:choose>
                        <c:when test="${delivery1.status == 'Đang giao'}">
                            <div class="upload-container">
                                <p><strong>Image: </strong></p>
                                <input type="file" id="fileInput" name="photo" placeholder="Upload">
                            </div>
                            <h3>You were successful in ordering?</h3>
                            <button type="submit" name="action" value="success">Yes</button>
                            <button type="button" onclick="openReasonModal(${order1.id})">No</button>
                        </c:when>
                        <c:when test="${delivery1.status == 'Đã giao'}">
                            <img src="${delivery1.image}" alt="Confirmation Image">
                            <p class="status-message-success">The order has been delivered successfully!</p>
                        </c:when>
                        <c:when test="${delivery1.status == 'Không giao được'}">
                            <img src="${delivery1.image}" alt="Confirmation Image">
                            <div class="reason">
                                <p>${des.descripsion}</p>
                            </div>
                        </c:when>
                        <c:when test="${delivery1.status == 'Đang lấy hàng'}">
                            <h3>Are you ready?</h3>
                            <button type="submit" name="action" value="start">Start</button>
                            <button type="submit" name="action" value="wait">Wait</button>
                        </c:when>
                    </c:choose>
                </form>
            </div>
        </div>
        <script src="js/delivery.js"></script>
    </body>
</html>