<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <style>
        .table-container {
            height: 400px;
            overflow-y: scroll;
        }
        table {
            width: 100%;
        }
        th, td {
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        img {
            max-width: 100px;
            height: auto;
        }
        .search-container {
            display: flex;
            align-items: center;
        }
        .search-container select, .search-container input {
            margin-right: 10px;
        }
    </style>
</head>
<body>
    <div class="table-container">
        <div class="head">
            <h3>List Product</h3>
            
            <i class='bx bx-filter' onclick="sortPro()"></i>
        </div>
        <table>
            <thead>
                <tr>
                    <th>Image</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Rating</th>
                </tr>
            </thead>
            <tbody id="productTable">
                <c:forEach items="${requestScope.productList}" var="p">
                    <tr data-category="${p.category.name}">
                        <td><img src="images/Product/${p.image}" alt="${p.name}"></td>
                        <td>${p.name}</td>
                        <td>${p.price}</td>
                        <td>${p.quantity}</td>
                        <td>${p.rating}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    
</body>
</html>
