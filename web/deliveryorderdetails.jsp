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
        <p><strong>Product:</strong> ${productname}</p><br>
        <p><strong>Quantity:</strong> ${orderdetails.quantity}</p><br>
        <p><strong>Price:</strong> ${orderdetails.price}</p> <br>
        <p><strong>Ship Price:</strong> ${ship_price}</p><br>
        <p><strong>Status:</strong> ${order.status} </p>

        <iframe 
            src="${directionsURL}"
            width="330px"
            height="390px;" 
            frameborder="0" 
            style="border:1px; margin-top: -200px; margin-left: 340px;"  
            allowfullscreen>
        </iframe>

        <form action = "deliverystatus" method = "POST">
            <div class="order-details" style ="margin-top: -150px">    
                <c:choose>
                    <c:when test="${order.status == 'Đang giao'}">
                        <button type="submit" id="contactCustomer" class="button all" style="display: block;">Contact</button>
                    </c:when>
                    <c:when test="${order.status == 'Đã giao'}">
                        <button type="submit" id="viewHistory" class="button all" style="display: block;">History</button>
                    </c:when>
                    <c:when test="${order.status == 'Đã hủy'}">
                        <button type="submit" id="viewReason" class="button all" style="display: block;">Reason</button>
                    </c:when>
                    <c:when test="${order.status == 'Đang chờ'}">
                        <button type="submit" id="acceptOrder" class="button all" style="display: block;">Accept</button>
                        <button type="submit" id="rejectOrder" class="button all" style="display: block;">Reject</button>
                    </c:when>
                    <c:otherwise>
                        <button type="button" class="button all" style="display: block;">Un-active</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </form>
    </body>
</html>
