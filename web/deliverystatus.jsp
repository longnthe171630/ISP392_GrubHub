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

        <p><strong>Code: </strong> ${order1.id}</p><br>
        
        <p><strong>Restaurant: </strong> ${order1.res_name}</p>
        <p><strong>Contact: </strong> ${order1.res_phone}</p>
        <p><strong>Address: </strong> ${order2.fromAddress}</p><br>

        <p><strong>Customer: </strong> ${order1.cus_name}</p>
        <p><strong>Contact: </strong> ${order1.cus_phone}</p>
        <p><strong>Address: </strong> ${order2.toAddress}</p>
        <br><p><strong>Status: </strong> ${delivery1.status}</p><br>

        <!--        Xử lí status đơn -->
        <form action="deliverystatus" method="post" enctype="multipart/form-data"">
            <input type="hidden" name="id" value="${order1.id}">
            <div class="order-details">    
                <c:choose>
                    <c:when test="${delivery1.status == 'Đang giao'}">
                        <!-- Hiển thị nút tải lên hình ảnh -->
                        <div class="upload-container" style = "margin-left: 350px; margin-top: -45px;">
                            <p><strong>Image: </strong></p>
                            <input type="file" name ="photo" placeholder="Upload">
                            <h5>Don't need image if order failure!</h5>
                        </div>
                        <!-- Hiển thị nút xác nhận đơn hàng -->
                        <h3 style = "margin-top: 20px;">You was successful to order?</h3>
                        <button type="submit" name="action" value="success">Yes</button>
                        <button type="button" onclick="openReasonModal(${order1.id})">No</button>
                    </c:when>
                    <c:when test="${delivery1.status == 'Đã giao'}">
                        <img src="${delivery1.image}" alt="Ảnh xác nhận" style="width: 270px; height: 370px; margin-left: 380px; margin-top: -310px;">
                        <p style = "color: red">The order has been delivered successfully!</p>
                    </c:when>
                    <c:when test="${delivery1.status == 'Không giao được'}">
                        <div style = "color: red">
                            <h3>Reason</h3>
                            <p>${des.descripsion}</p>
                        </div>
                    </c:when>
                    <c:when test="${delivery1.status == 'Đang lấy hàng'}">
                        <h3 style = "margin-top: 40px;">Are you ready?</h3>
                        <button type="submit" name="action" value="start">Start</button>
                        <button type="submit" name="action" value="wait">Wait</button>
                    </c:when>
                </c:choose>
            </div>
        </form>
        <script src="js/delivery.js"></script>
    </body>
</html>