<%-- 
    Document   : productdetail
    Created on : Jun 13, 2024, 9:39:53 PM
    Author     : phaml
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.*" %> 
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- ======= Styles ====== -->
    <link rel="stylesheet" href="css/style_ship.css">
    <title>Chi tiết sản phẩm</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .product-details {
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 800px;
            width: 100%;
            box-sizing: border-box;
            display: flex;
            flex-direction: column;
            gap: 20px; /* Add gap between elements */
        }

        .product-details h2 {
            text-align: center;
            color: #333;
        }

        .product-image {
            display: block;
            margin: 0 auto;
            max-width: 50%;
            border-radius: 10px;
        }

        .product-info .form-group {
            margin: 10px 0;
        }

        .product-info .form-group label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }

        .product-info .form-group input, .product-info .form-group textarea, .product-info .form-group select {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
            border: none;
            background-color: #f9f9f9;
            border-radius: 5px;
            font-size: 16px;
        }

        .product-info .form-group input:focus, .product-info .form-group textarea:focus, .product-info .form-group select:focus {
            outline: none;
            background-color: #e6e6e6;
        }

        .product-info .form-group textarea {
            resize: none; /* Prevent resizing */
            height: 100px; /* Set a fixed height */
        }

        .product-info .form-group input[type="file"] {
            padding: 5px;
        }

        .buttons {
            display: flex;
            justify-content: flex-start; /* Align buttons to the left */
            gap: 10px; /* Add some space between the buttons */
        }

        .buttons button {
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .buttons .save {
            background-color: #4CAF50;
            color: white;
        }

        .buttons .delete {
            background-color: #f44336;
            color: white;
        }
    </style>
</head>

<%
    Product p = (Product) request.getAttribute("product");
    List<Category> lc = (List<Category>) request.getAttribute("lc");
    String msg = (String) request.getAttribute("msg");
%>

<body>
    <div class="product-details">
        <h2>Chi tiết sản phẩm</h2>
        <form id="editForm" action="editproductfile" method="post" enctype="multipart/form-data">
            <input type="hidden" id="productId" name="productId" value="<%= p.getId() %>">
            <img src="images/Product/<%= p.getImage() %>" alt="<%= p.getName() %>" class="product-image">
            <div class="product-info">
                <div class="form-group">
                    <label for="name">Tên sản phẩm:</label>
                    <input type="text" id="name" name="name" value="<%= p.getName() %>">
                </div>
                <div class="form-group">
                    <label for="category">Phân loại:</label>
                    <select id="category" name="category">
                        <% for (Category category : lc) { %>
                        <option value="<%= category.getId() %>" <%= p.getCategoryId() == category.getId() ? "selected" : "" %>>
                            <%= category.getName() %>
                        </option>
                        <% } %>
                    </select>
                </div>
                <div class="form-group">
                    <label for="price">Giá:</label>
                    <input type="text" id="price" name="price" value="<%= p.getPrice() %>" required="">
                </div>
                <div class="form-group">
                    <label for="quantity">Số lượng:</label>
                    <input type="text" id="quantity" name="quantity" value="<%= p.getQuantity() %>" required="">
                </div>
                <div class="form-group">
                    <label for="description">Mô tả:</label>
                    <textarea id="description" name="description" required=""><%= p.getDescription() %></textarea>
                </div>
                <div class="form-group">
                    <label for="img">Ảnh sản phẩm:</label>
                    <input type="file" id="img" name="img" accept=".jpg, .jpeg, .png, .gif">
                </div>
            </div>
            <div class="buttons">
                <button type="submit" name="action" value="save" class="save" onclick="return confirmAction('save');">Save</button>
                <button type="submit" name="action" value="delete" class="delete" onclick="return confirmAction('delete');">Delete</button>
            </div>
        </form>
    </div>

    <script>
        // Function to show confirmation dialog
        function confirmAction(action) {
            var message = '';
            if (action === 'save') {
                message = 'Bạn có chắc chắn muốn lưu thay đổi sản phẩm?';
            } else if (action === 'delete') {
                message = 'Bạn có chắc chắn muốn xóa sản phẩm này?';
            }

            // Hiển thị hộp thoại xác nhận
            var confirmResult = confirm(message);
            return confirmResult; // Trả về kết quả xác nhận
        }
    </script>

    <script type="text/javascript">
        window.onload = function () {
            // Lấy thông điệp từ biến JavaScript
            var msg = "<%= msg != null ? msg : "" %>";
            if (msg) {
                // Hiển thị thông điệp bằng alert hoặc bạn có thể tùy chỉnh cách hiển thị
                alert(msg);
            }
        };
    </script>
</body>
</html>
