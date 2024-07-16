<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Order Details</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            body {
                font-family: 'Roboto', sans-serif;
                background-color: #E1E8EE;
                margin: 0;
            }
            .shopping-cart {
                width: 750px;
                margin: 80px auto;
                background: #ffffff;
                box-shadow: 1px 2px 3px 0px rgba(0,0,0,0.10);
                border-radius: 6px;
                display: flex;
                flex-direction: column;
                padding: 20px;
            }
            .title {
                border-bottom: 1px solid #E1E8EE;
                padding-bottom: 10px;
                margin-bottom: 20px;
                color: #5E6977;
                font-size: 18px;
            }
            .item {
                display: flex;
                align-items: center;
                padding: 20px 0;
                border-bottom: 1px solid #E1E8EE;
            }
            .item:last-child {
                border-bottom: none;
            }
            .image {
                margin-right: 20px;
            }
            .description {
                flex-grow: 1;
                font-family: sans-serif;
                margin-left: 120px;
                font-size: 20px;
            }
            .description span {
                display: block;
                font-size: 17px;
                color: #43484D;
                margin-bottom: 5px;
            }
            .quantity {
                font-family: sans-serif;
                margin-right: 100px;
                color: #43484D;
                font-size: 17px;
            }
            .total-price {
                font-size: 17px;
                color: #43484D;
            }
            .feedback-link {
                font-size: 17px;
                margin-left: 20px;
            }
        </style>
    </head>
    <body>
        <div class="shopping-cart">
            <!-- Title -->
            <div class="title">
                Order Details
            </div>

            <!-- Products -->
            <c:forEach var="orderDetail" items="${listOD}">
                <div class="item">
                    <div class="image">
                        <img src="${pageContext.request.contextPath}/images/Product/${productImages[orderDetail.product_id]}" alt="Product Image" width="100" height="100"/>
                    </div>

                    <div class="description">
                        <span>${productNames[orderDetail.product_id]}</span>
                    </div>

                    <div class="quantity">
                        <span>Quantity: ${orderDetail.quantity}</span>
                    </div>

                    <div class="total-price">
                        $${orderDetail.price}
                    </div>
                    <div class="feedback-link">
                        <c:choose>
                            <c:when test="${feedbackStatuses[orderDetail.id]}">
                                <a href="cusfeedback?orderId=${orderDetail.order_id}&productId=${orderDetail.product_id}&customerId=${customerId}">View Feedback</a>
                            </c:when>
                            <c:otherwise>
                                <a href="AddFeedback.jsp?orderId=${orderDetail.order_id}&productId=${orderDetail.product_id}&customerId=${customerId}">Add Feedback</a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </c:forEach>
            <c:if test="${empty listOD}">
                <p>No order details found.</p>
            </c:if>
        </div>
    </body>
</html>
