<%-- 
    Document   : deliverysettings
    Created on : Jul 16, 2024, 7:55:24 AM
    Author     : Long1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style_ship.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <script src="js/delivery.js"></script>
        <title>Settings</title>
    </head>
    <body>
        <% 
    String lang = request.getParameter("lang");
    if (lang != null) {
        session.setAttribute("lang", lang);
    }
        %>

    <fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : 'en'}" />
    <fmt:setBundle basename="vn.aptech.lang.create_vi_VN" var="lang"/>

    <div class="settings-container">
        <div class="content">
            <div class="header">
                <h2><fmt:message bundle="${lang}" key="settings"/>Settings</h2>
            </div>
            <div class="settings">
                <div class="setting-item">
                    <span><fmt:message bundle="${lang}" key="changepassword"/>Đổi mật khẩu</span>
                    <a class="manage-btn" href="Changepassword.jsp">Thay đổi</a>
                </div>
                <div class="setting-item">
                    <span>Chuyển đổi ngôn ngữ</span>
                    <a class="manage-btn" href="?lang=en">Tiếng Anh</a>
                    <a class="manage-btn" href="?lang=vi">Tiếng Việt</a>
                </div>
                <div class="setting-item">
                    <span>Thông tin thanh toán</span>
                    <a class="manage-btn" href="deliverypayment.jsp">Cập nhật</a>
                </div>               
                <div class="setting-item">
                    <span>Điều khoản và dịch vụ</span>
                    <a class="manage-btn" href="deliveryterms.jsp">Chi tiết</a>
                </div>  
            </div>
        </div>
        <div id="myModal" class="modal">
            <div class="modal-content">
                <span class="close" onclick="closeModal();">&times;</span>
                <div id="modalContent"></div>
            </div>
        </div>
    </div>
</body>
</html>
