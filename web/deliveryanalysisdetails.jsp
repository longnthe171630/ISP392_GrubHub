<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <h2>Analysis Details</h2><br>
         <div class="table-container">
            <table>
                <c:choose>
                    <c:when test="${fn:length(list) == 0}">
                        <p class="empty-message">No data!</p>
                    </c:when>
                    <c:otherwise>
                        <thead>
                            <tr>
                                <th>Code</th>
                                <th>Ship Price</th>
                                <th>Delivery Time</th>
                                <th>Start</th>
                                <th>End</th>
                                <th>Status</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="d" items="${list}">
                                <tr>
                                    <td>${d.order_id}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${d.status == 'Đã giao'}">
                                                <i class="fas fa-check-circle" style = "color: green;"></i> ${d.ship_price}
                                            </c:when>
                                            <c:otherwise>
                                                <!-- Sử dụng biểu tượng Font Awesome hoặc biểu tượng khác để thể hiện tiền ship không thể nhận -->
                                                <i class="fas fa-ban" style = "color: red;"></i> ${d.ship_price}
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>${d.deliveryTime} minutes</td>
                                    <td>${d.start_time}</td>
                                    <td>${d.end_time}</td>
                                    <td class="status ${d.status == 'Đã giao' ? 'Đã giao' : 'Không giao được'}">
                                        <c:choose>
                                            <c:when test="${d.status == 'Đã giao'}">
                                                <span class="status completed">${d.status}</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="status pending">${d.status}</span>
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
