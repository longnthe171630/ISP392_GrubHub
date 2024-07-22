<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="deliverychange.jsp" %>
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
            <h2><fmt:message key="order_details" bundle="${lang}"/></h2><br>
            <p><strong><fmt:message key="code" bundle="${lang}"/>: </strong> ${order1.id}</p>
            <input type="hidden" name="id" value="${order1.id}">
            <p><strong><fmt:message key="product" bundle="${lang}"/>:</strong> ${productname}</p>
            <p><strong><fmt:message key="quantity" bundle="${lang}"/>:</strong> ${orderdetails.quantity}</p>
            <p><strong><fmt:message key="price" bundle="${lang}"/>:</strong> ${orderdetails.price}</p>
            <p><strong><fmt:message key="ship_price" bundle="${lang}"/>:</strong> ${ship_price}</p><br>

            <c:set var="waitingdelevery" value="${order1.status eq 'Waiting delivery' ? 'waitingdelivery' : ''}" />
            <p><strong><fmt:message key="status" bundle="${lang}"/>: </strong><fmt:message key="${waitingdelevery}" bundle="${lang}"/></p>

            <iframe 
                src="${directionsURL}"
                width="300px"
                height="350px;" 
                frameborder="0" 
                style="border:1px; margin-left: 360px; margin-top: -180px;" 
                allowfullscreen>
            </iframe>

            <div class="order-details" style = "margin-top: -20px;">    
                <h3><fmt:message key="note7" bundle="${lang}"/></h3>
                <button type = "submit" name = "action" value = "accept"><fmt:message key="yes" bundle="${lang}"/></button>
                <button type = "submit" name = "action" value = "reject"><fmt:message key="no" bundle="${lang}"/></button>
            </div>
        </form>
    </body>
</html>
