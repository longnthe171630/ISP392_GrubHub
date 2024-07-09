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
        <form action = "deliverystatus" method = "POST">
            <h2>Chi tiết đơn hàng</h2><br>
            <p><strong>Mã đơn: </strong> ${order1.id}</p>
            <input type="hidden" name="id" value="${order1.id}">
            <p><strong>Tên sản phẩm:</strong> ${productname}</p>
            <p><strong>Số lượng:</strong> ${orderdetails.quantity}</p>
            <p><strong>Giá sản phẩm:</strong> ${orderdetails.price}</p>
            <p><strong>Cước vận chuyển:</strong> ${ship_price}</p><br>
            <p><strong>Trạng thái:</strong> ${order1.status}</p>

            <iframe 
                src="${directionsURL}"
                width="300px"
                height="350px;" 
                frameborder="0" 
                style="border:1px; margin-left: 360px; margin-top: -180px;" 
                allowfullscreen>
            </iframe>

            <div class="order-details" style = "margin-top: -20px;">    
                <h3>Do you want to receive this order?</h3>
                <button type = "submit" name = "action" value = "accept">Yes</button>
                <button type = "submit" name = "action" value = "reject">No</button>
            </div>
        </form>
    </body>
</html>
