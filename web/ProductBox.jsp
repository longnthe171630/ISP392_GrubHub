<%-- 
    Document   : ProductBox
    Created on : Jul 18, 2024, 9:44:26 PM
    Author     : manh0
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
    <c:forEach items="${requestScope.sortedProducts}" var="product">
        <div class="box">
            <!-- Product details -->
            <h3>${product.name}</h3>
            <p>${product.description}</p>
            <p><span>${product.price} đ</span></p>
            <!-- Add to cart form -->
            <form action="addToCart" method="post">
                <input type="hidden" name="productId" value="${product.id}">
                <input type="hidden" name="num" value="1">
                <input type="submit" class="btn" value="Add to Cart">
            </form>
        </div>
    </c:forEach>
</body>
</html>
