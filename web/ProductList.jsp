<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
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
            .head {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 10px;
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
                <div class="search-container">
                    <select id="categoryFilter" onclick="filterProducts()">
                        <option value="">All Categories</option>
                        <c:forEach items="${requestScope.productList}" var="p">
                            <option value="${p.category.name}">${p.category.name}</option>
                        </c:forEach>
                    </select>
                    <i class='bx bx-filter' onclick="sortPro()"></i>
                </div>
            </div>
            <table id="productTable">
                <thead>
                    <tr>
                        <th>Image</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Rating</th>
                        <th>Category</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.productList}" var="p">
                        <tr data-category="${p.category.name}">
                    <input type="hidden" id="productId" name="productId" value="${p.id}">
                    <td><img src="images/Product/${p.image}" alt="${p.name}"></td>
                    <td>${p.name}</td>
                    <td>${p.price}</td>
                    <td>${p.quantity}</td>
                    <td>${p.rating}</td>
                    <td>${p.category.name}</td>
                    <td> 
                        <a href="admin?action=banpro&productId=${p.id}" class="ban-btn">Ban</a>
                    </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <script>
            function filterProducts() {
                const categoryFilter = document.getElementById("categoryFilter").value.trim().toLowerCase();
                const rows = document.querySelectorAll("#productTable tbody tr");

                rows.forEach(row => {
                    const category = row.getAttribute("data-category").toLowerCase();
                    if (categoryFilter === "" || category.includes(categoryFilter)) {
                        row.style.display = "";
                    } else {
                        row.style.display = "none";
                    }
                });
            }

        </script>
    </body>
</html>
