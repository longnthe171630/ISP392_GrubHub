<%@ page import="java.util.List" %>
<%@ page import="model.Order" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Orders</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"/>
</head>
<body>
    <div class="container">
        <h2>My Orders</h2>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Order ID</th>
                    <th>Order Date</th>
                    <th>Status</th>
                    <th>Total Amount</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="order" items="${orders}">
                    <tr>
                        <td><c:out value="${order.id}" /></td>
                        <td><c:out value="${order.order_date}" /></td>
                        <td><c:out value="${order.status}" /></td>
                        <td>$<c:out value="${order.total_amount}" /></td>
                        <td>
                            <form action="order" method="get">
                                <input type="hidden" name="orderId" value="<c:out value='${order.id}' />">
                                <input type="submit" value="View Order">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
