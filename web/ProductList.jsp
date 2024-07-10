<%-- 
    Document   : ProductList
    Created on : Jun 18, 2024, 11:18:10 PM
    Author     : Dell
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
   <body>
    <table>
    <thead>
        <tr>
            <th>Image</th> <!-- Đã thay đổi từ ID sang Image -->
            <th>Name</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Rating</th>
          
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${requestScope.productList}" var="p">
            <tr>
                <!-- Sử dụng thẻ <img> để hiển thị hình ảnh -->
                <td><img src="images/Product/${p.image}"  ></td>
                <td>${p.name}</td>
                <td>${p.price}</td>
                <td>${p.quantity}</td>
                <td>${p.rating}</td>
                
            </tr>
        </c:forEach>
    </tbody>
</table>
</body>
</html>
