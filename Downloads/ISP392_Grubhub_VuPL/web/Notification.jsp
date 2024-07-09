<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Notifications</title>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <h1>Notifications</h1>
        <c:if test="${not empty notifications}">
            <ul>
                <c:forEach var="notification" items="${notifications}">
                    <li>${notification.message}</li>
                </c:forEach>
            </ul>
        </c:if>
        <c:if test="${empty notifications}">
            <p>No notifications found.</p>
        </c:if>
    </body>
</html>
