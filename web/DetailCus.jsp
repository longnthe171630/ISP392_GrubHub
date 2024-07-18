<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Account Details</title>
        <style>
            /* Your custom styles can go here */
        </style>
        <!-- Your other headers -->
    </head>
    <body>
        <table style="height: calc(100% + 30px);"> <!-- Adding 30 pixels to the height -->
            <tbody>
                <tr>
                    <th>Avatar:</th>
                    <td>${accountDetail.img}</td> <!-- Using class image-cell to align right -->
                </tr>
                <tr>
                    <th>Name:</th>
                    <td>${accountDetail.username}</td>
                </tr>
                <tr>
                    <th>Email:</th>
                    <td>${accountDetail.email}</td>
                </tr>
                <tr>
                    <th>Phone Number:</th>
                    <td>${accountDetail.phonenumber}</td>
                </tr>
                <tr>
                    <th>Role:</th>
                    <td>
                        <c:choose>
                            <c:when test="${accountDetail.role == 2}">
                                Customer
                            </c:when>
                            <c:when test="${accountDetail.role == 3}">
                                Restaurant
                            </c:when>
                            <c:when test="${accountDetail.role == 4}">
                                Shipper
                            </c:when>
                            <c:otherwise>
                                ${accountDetail.role}
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <th>Status:</th>
                    <td>
                        <c:choose>
                            <c:when test="${accountDetail.active == 1}">
                                Active
                            </c:when>
                            <c:otherwise>
                                ${accountDetail.active}
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>        
            </tbody>
        </table>
    </body>
</html>
