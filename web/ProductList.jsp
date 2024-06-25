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
        <ul>
            <c:forEach items="${requestScope.list}" var="p">
                <tr>
                    <td>${p.id}</td>
                    <td>${p.name}</td>
                    <td>${p.price}</td>
                    <td>${p.quantity}</td>                    
                    <td>${p.rating}</td>  
                    <td>${p.cateName}</td>
                </tr>
            </c:forEach>
        </ul>
    </body>
</html>
