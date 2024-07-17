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
            <div class="search-container">
                <select id="categoryFilter" onchange="filterProducts()">
                    <option value="">All Categories</option>
                    <c:forEach items="${requestScope.productList}" var="p">
                        <option value="${p.category}">${p.category.name}</option>
                    </c:forEach>
                </select>
                <input type="search" id="searchInput" placeholder="Search..." onkeyup="filterProducts()">
                <button type="button" class="search-btn" onclick="filterProducts()"><i class='bx bx-search'></i></button>
            </div>
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
                    <tr data-category="${p.category.id}">
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

    <script>
        function filterProducts() {
            var input, filter, categoryFilter, table, tr, td, i, txtValue;
            input = document.getElementById("searchInput");
            filter = input.value.toUpperCase();
            categoryFilter = document.getElementById("categoryFilter").value;
            table = document.getElementById("productTable");
            tr = table.getElementsByTagName("tr");

            for (i = 0; i < tr.length; i++) {
                td = tr[i].getElementsByTagName("td")[1]; // Get the name column
                var category = tr[i].getAttribute("data-category");

                if (td) {
                    txtValue = td.textContent || td.innerText;
                    if ((txtValue.toUpperCase().indexOf(filter) > -1 || filter === "") &&
                        (category === categoryFilter || categoryFilter === "")) {
                        tr[i].style.display = "";
                    } else {
                        tr[i].style.display = "none";
                    }
                }
            }
        }
    </script>
</body>
</html>
