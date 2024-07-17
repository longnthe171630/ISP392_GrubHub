<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.*, java.util.*" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Uploaded Products</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 20px;
            }
            h1 {
                color: #333;
                text-align: center;
            }
            .actions-container {
                margin-bottom: 20px;
                text-align: right;
            }
            .actions {
                text-align: center;
                margin-top: 10px;
            }
            .edit-btn, .delete-btn, .cancel-btn, .save-all-btn {
                text-decoration: none;
                padding: 10px 20px;
                color: #fff;
                border-radius: 5px;
                margin-right: 10px;
                display: inline-block; /* Để nút hiển thị trên cùng một dòng */
                text-align: center;
            }
            .edit-btn {
                background-color: #4CAF50;
            }
            .delete-btn {
                background-color: #f44336;
            }
            .cancel-btn {
                background-color: #999;
            }
            .save-all-btn {
                background-color: #008CBA;
            }
            .edit-btn:hover, .delete-btn:hover, .cancel-btn:hover, .save-all-btn:hover {
                opacity: 0.8; /* Giảm độ trong suốt khi di chuột */
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }
            table, th, td {
                border: 1px solid #ccc;
            }
            th, td {
                padding: 10px;
                text-align: center; /* Căn giữa nội dung */
            }
            th {
                background-color: #f4f4f4;
            }
            tr:nth-child(even) {
                background-color: #f9f9f9;
            }
        </style>
        <script>
            function confirmCancel() {
                return confirm('Are you sure you want to cancel?');
            }
            function confirmSaveAll() {
                return confirm('Are you sure you want to save all changes?');
            }
        </script>
    </head>
    <body>
        <div class="actions-container">
            <a href="productofrestaurant" class="cancel-btn" onclick="return confirmCancel();">Cancel</a>
            <a href="saveproductfromfile" class="save-all-btn" onclick="return confirmSaveAll();">Save All</a>
        </div>

        <h1>Uploaded Products</h1>

        <% 
           HttpSession sessionObj = request.getSession();
           List<Product> products = (List<Product>) sessionObj.getAttribute("uploadedProducts");
           List<Category> lc = (List<Category>) request.getAttribute("lc");
           int i = 0; // Initialize the counter variable for products
        %>

        <% if (products != null && !products.isEmpty()) { %>
        <table>
            <thead>
                <tr>
                    <th>id</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Description</th>
                    <th>Image</th>
                    <th>Category</th>
                    <th>Actions</th> <!-- Column for actions -->
                </tr>
            </thead>
            <tbody>
                <% for (Product product : products) { 
                    // Ensure lc is populated correctly in your servlet
                %>
                <tr>
                    <td><%= product.getId() %></td>
                    <td><%= product.getName() %></td>
                    <td><%= product.getPrice() %></td>
                    <td><%= product.getQuantity() %></td>
                    <td><%= product.getDescription() %></td>
                    <td><%= product.getImage() %></td>
                    <td>
                        <% 
                            for (Category category : lc) { 
                                if (product.getCategoryId() == category.getId()) { 
                        %>
                        <%= category.getName() %>
                        <% 
                                    break;
                                }
                            } 
                        %>
                    </td>
                    <td class="actions">
                        <a href="editproductfile?id=<%= product.getId() %>" class="edit-btn">Edit</a>
                        <a href="editproductfile?id=<%= product.getId() %>&action=delete" class="delete-btn" onclick="return confirm('Are you sure you want to delete this product?');">Delete</a>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
        <% } else { %>
        <p>No products uploaded.</p>
        <% } %>

    </body>
</html>
