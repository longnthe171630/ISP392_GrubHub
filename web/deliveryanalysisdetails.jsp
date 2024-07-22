<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="deliverychange.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- ======= Styles ====== -->
        <link rel="stylesheet" href="css/style_ship.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    </head>

    <body>
        <h2><fmt:message key="analysis_details" bundle="${lang}"/></h2><br>
         <div class="table-container">
            <table>
                <c:choose>
                    <c:when test="${fn:length(list) == 0}">
                        <p class="empty-message"><fmt:message key="no_any_data" bundle="${lang}"/></p>
                    </c:when>
                    <c:otherwise>
                        <thead>
                            <tr>
                                <th><fmt:message key="code" bundle="${lang}"/></th>
                                <th><fmt:message key="ship_price" bundle="${lang}"/></th>
                                <th><fmt:message key="delivery_time" bundle="${lang}"/> </th>
                                <th><fmt:message key="start" bundle="${lang}"/></th>
                                <th><fmt:message key="end" bundle="${lang}"/></th>
                                <th><fmt:message key="status" bundle="${lang}"/></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="d" items="${list}">
                                <tr>
                                    <td>${d.order_id}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${d.status == 'Success'}">
                                                <i class="fas fa-check-circle" style = "color: green;"></i> ${d.ship_price}
                                            </c:when>
                                            <c:otherwise>
                                                <!-- Sử dụng biểu tượng Font Awesome hoặc biểu tượng khác để thể hiện tiền ship không thể nhận -->
                                                <i class="fas fa-ban" style = "color: red;"></i> ${d.ship_price}
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>${d.deliveryTime} <fmt:message key="minutes" bundle="${lang}"/></td>
                                    <td>${d.start_time}</td>
                                    <td>${d.end_time}</td>
                                    <td class="status ${d.status == 'Success' ? 'Success' : 'Failure'}">
                                        <c:choose>
                                            <c:when test="${d.status == 'Success'}">
                                                <c:set var="status1" value="success" />
                                                <span class="status completed"><fmt:message key="${status1}" bundle="${lang}"/></span>
                                            </c:when>
                                            <c:otherwise>
                                                <c:set var="status1" value="failure" />
                                                <span class="status pending"><fmt:message key="${status1}" bundle="${lang}"/></span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </c:otherwise>
                </c:choose>
            </table>
        </div>
        <script src="js/delivery.js"></script>
    </body>
</html>
