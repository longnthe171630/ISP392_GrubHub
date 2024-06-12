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
        <h2>Order Details</h2><br>
        <p><strong>Code:</strong> ${order1.id}</p><br>
        <p><strong>Product:</strong> ${productname}</p><br>
        <p><strong>Quantity:</strong> ${orderdetails.quantity}</p><br>
        <p><strong>Price:</strong> ${orderdetails.price}</p> <br>
        <p><strong>Ship Price:</strong> ${ship_price}</p><br>
        <p><strong>Status:</strong> ${order1.status}</p><br>

        <iframe 
            src="${directionsURL}"
            width="330px"
            height="390px;" 
            frameborder="0" 
            style="border:1px; margin-left: 340px; margin-top: -250px;" 
            allowfullscreen>
        </iframe>

        <form action = "deliverystatus" method = "POST">
            <div class="order-details" style ="margin-top: -130px">    
                <button type = "submit" name = "accept">Nhận đơn</button>
                <button type = "submit" name = "reject">Bỏ qua</button>
            </div>
        </form>
    </body>
</html>
