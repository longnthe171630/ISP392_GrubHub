<%-- 
    Document   : addnewproduct
    Created on : Jun 16, 2024, 3:40:15 PM
    Author     : phaml
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.*" %> 
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Add New Product</title>

    </head>
    <%
       
            List<Category> lc = (List<Category>) request.getAttribute("lc");
    %>

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 20px;
        }
        .container {
            max-width: 500px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
            color: #333;
        }
        label {
            display: block;
            margin-top: 10px;
            color: #555;
        }
        input[type="text"], input[type="file"], select {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px 20px;
            margin-top: 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
    <body>

        <div class="container">
            <h2>Add New Product</h2>

            <form action="addnewproduct" method="POST" enctype="multipart/form-data">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" required>

                <label for="price">Price:</label>
                <input type="number" id="price" name="price" required>

                <label for="quantity">Quantity:</label>
                <input type="number" id="quantity" name="quantity" required>

                <label for="description">Description:</label>
                <input type="text" id="description" name="description" required>

                <label for="img">Image (.jpg, .jpeg, .png, .gif):</label>
                <input type="file" id="img" name="img" accept=".jpg, .jpeg, .png, .gif" required>

                <label for="category">Category:</label>
                <select id="category" name="category">
                    <% for (Category category : lc) { %>
                    <option value="<%= category.getId() %>">
                        <%= category.getName() %>
                    </option>
                    <% } %>
                </select>

                <input type="submit" value="Add Product">
            </form>
        </div>

    </body>
    <script type="text/javascript">
        window.onload = function () {
            // Lấy thông điệp từ biến JavaScript
            var msg = "<%= request.getAttribute("msg") != null ? request.getAttribute("msg") : "" %>";
            if (msg) {
                // Hiển thị thông điệp bằng alert hoặc bạn có thể tùy chỉnh cách hiển thị
                alert(msg);
            }
        };
    </script>
</html>
