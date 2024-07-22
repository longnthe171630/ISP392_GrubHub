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
        <h2><fmt:message key="order_details" bundle="${lang}"/></h2><br>

        <div class="info-container">
            <div class="order-info">
                <p><strong><fmt:message key="code" bundle="${lang}"/>: </strong> ${order1.id}</p><br>

                <p><strong><fmt:message key="res" bundle="${lang}"/>: </strong> ${order1.res_name}</p>
                <p><strong><fmt:message key="contact" bundle="${lang}"/>: </strong> ${order1.res_phone}</p>
                <p><strong><fmt:message key="address" bundle="${lang}"/>: </strong> ${order2.fromAddress}</p><br>

                <p><strong><fmt:message key="cus" bundle="${lang}"/>: </strong> ${order1.cus_name}</p>
                <p><strong><fmt:message key="contact" bundle="${lang}"/>: </strong> ${order1.cus_phone}</p>
                <p><strong><fmt:message key="address" bundle="${lang}"/>: </strong> ${order2.toAddress}</p><br>
                    <c:set var="status1" value="" />
                <c:choose>
                    <c:when test="${delivery1.status == 'Delivering'}">
                    <c:set var="status1" value="delivering" />
                    </c:when>
                    <c:when test="${delivery1.status == 'Success'}">
                    <c:set var="status1" value="success" />
                    </c:when>
                    <c:when test="${delivery1.status == 'Failure'}">
                    <c:set var="status1" value="failure" />
                    </c:when>
                    <c:when test="${delivery1.status == 'Picking Up'}">
                    <c:set var="status1" value="pickup" />
                    </c:when>
                </c:choose>
                <p><strong><fmt:message key="status" bundle="${lang}"/> </strong> :<fmt:message key="${status1}" bundle="${lang}"/></p>
                
            </div>

            <div class="image-container">
                <form action="deliverystatus" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="id" value="${order1.id}">
                    <c:choose>
                        <c:when test="${delivery1.status == 'Delivering'}">
                            <div class="upload-container">
                                <p><strong><fmt:message key="img" bundle="${lang}"/></strong></p>
                                <input type="file" id="fileInput" name="photo" placeholder="<fmt:message key="upload" bundle="${lang}"/>">
                            </div>
                            <h3><fmt:message key="note9" bundle="${lang}"/></h3>
                            <button type="submit" name="action" value="success"><fmt:message key="yes" bundle="${lang}"/></button>
                            <button type="button" onclick="openReasonModal(${order1.id})"><fmt:message key="no" bundle="${lang}"/></button>
                        </c:when>
                        <c:when test="${delivery1.status == 'Success'}">
                            <img src="${delivery1.image}" alt="<fmt:message key="img" bundle="${lang}"/>">
                            <p class="status-message-success"><fmt:message key="note10" bundle="${lang}"/></p>
                        </c:when>
                        <c:when test="${delivery1.status == 'Failure'}">
                            <img src="${delivery1.image}" alt="<fmt:message key="img" bundle="${lang}"/>">
                            <div class="reason">
                                <p>${des.descripsion}</p>
                            </div>
                        </c:when>
                        <c:when test="${delivery1.status == 'Picking Up'}">
                            <h3><fmt:message key="note11" bundle="${lang}"/></h3>
                            <button type="submit" name="action" value="start"><fmt:message key="start" bundle="${lang}"/></button>
                            <button type="submit" name="action" value="wait"><fmt:message key="wait" bundle="${lang}"/></button>
                        </c:when>
                    </c:choose>
                </form>
            </div>
        </div>
        <script src="js/delivery.js"></script>
    </body>
</html>