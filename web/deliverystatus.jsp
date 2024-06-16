<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- ======= Styles ====== -->
        <link rel="stylesheet" href="css/style_ship.css">

    </head>

    <body>
        <h2>Chi tiết đơn hàng</h2><br>
        <p><strong>Mã đơn: </strong> ${order1.id}</p><br>
        <br><p><strong>Nhà hàng: </strong> ${order1.res_name}</p><br>
        <p><strong>Liên hệ: </strong> ${order1.res_phone}</p><br>
        <p><strong>Địa chỉ: </strong> ${order2.fromAddress}</p><br>
        <div style="margin-left: 400px; margin-top: -116px;">
            <p><strong>Khách hàng: </strong> ${order1.cus_name}</p><br>
            <p><strong>Liên hệ: </strong> ${order1.cus_phone}</p><br>
            <p><strong>Địa chỉ: </strong> ${order2.toAddress}</p><br>
        </div>
        <input type="hidden" name="id" value="${order1.id}">
        <br><p><strong>Trạng thái:</strong> ${delivery1.status}</p><br>


        <div class="order-details">    
            <c:choose>
                <c:when test="${delivery1.status == 'Đang giao'}">
                    <form action="deliverystatus" method="POST">
                    <!-- Hiển thị nút xác nhận đơn hàng -->
                    <h3 style = "margin-top: 100px;">Bạn đã hoàn thành đơn hàng này?</h3>
                    <button type="submit" name="action" value="success">Thành công</button>
                    <button type="submit" name="action" value="failure">Thất bại</button>
                    </form>
                </c:when>
                <c:when test="${delivery1.status == 'Đã giao'}">
                    <h3 style = "margin-top: 100px;">Xem lịch sử đơn hàng</h3>
                    <button type="button" onclick="viewHistory()">Xem lịch sử</button>
                </c:when>
                <c:when test="${delivery1.status == 'Không giao được'}">
                    <form action="deliverystatus" method="POST">
                        <h3 style = "margin-top: 50px;">Lý do</h3>
                        <input type="hidden" name="order_id" value="${delivery1.order_id}">
                        <textarea name="reason" rows="4" cols="50" placeholder="Có vấn đề gì vậy?"></textarea><br>
                        <button type="submit" name="action" value="submitReason">Gửi lý do</button>
                    </form>
                </c:when>
                <c:otherwise>
                    <!-- Trường hợp khác (nếu có) -->
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>