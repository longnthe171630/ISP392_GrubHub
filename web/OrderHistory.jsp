<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Customer's Orders</title>
        <link rel="stylesheet" type="text/css" href="css/style_4.css">
    </head>

    <body>
        <main class="table" id="customers_table">
            <section class="table__header">
                <h1>Customer history ordered</h1>
                <div class="input-group">
                    <input type="search" placeholder="Search Data...">
                    <img src="css/images/search.png" alt="">
                </div>
                <div class="export__file">
                    <label for="export-file" class="export__file-btn" title="Export File"></label>
                    <input type="checkbox" id="export-file">
                    <div class="export__file-options">
                        <label>Export As &nbsp; &#10140;</label>
                        <label for="export-file" id="toPDF">PDF <img src="css/images/pdf.png" alt=""></label>
                        <label for="export-file" id="toJSON">JSON <img src="css/images/json.png" alt=""></label>
                        <label for="export-file" id="toCSV">CSV <img src="css/images/csv.png" alt=""></label>
                        <label for="export-file" id="toEXCEL">EXCEL <img src="css/images/excel.png" alt=""></label>
                    </div>
                </div>
            </section>
            <section class="table__body">
                <table>
                    <thead>
                        <tr>
                            <th>Order ID <span class="icon-arrow">&UpArrow;</span></th>
                            <th>Restaurant Name <span class="icon-arrow">&UpArrow;</span></th>
                            <th>Order Date <span class="icon-arrow">&UpArrow;</span></th>
                            <th>Status <span class="icon-arrow">&UpArrow;</span></th>
                            <th>Total Amount <span class="icon-arrow">&UpArrow;</span></th>
                            <th>Actions</th>

                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="order" items="${orderList}">
                            <tr>
                                <td>${order.id}</td>
                                <td>${restaurantNames[order.restaurant_id]}</td>
                                <td>${order.order_date}</td>
                                <td>${order.status}</td>
                                <td>${order.total_amount}$</td>
                                <td><a  href="historydetails?orderId=${order.id}&customerId=${customerId}">View</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <c:if test="${empty orderList}">
                    <p>No orders found.</p>
                </c:if>
            </section>
        </main>
        <script src="js/script_1.js"></script>
    </body>

</html>
