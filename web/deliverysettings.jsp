<%-- 
    Document   : deliverysettings
    Created on : Jul 16, 2024, 7:55:24 AM
    Author     : Long1
--%>
<%@ include file="deliverychange.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style_ship.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <script src="js/delivery.js"></script>
        <title><fmt:message key="settings" bundle="${lang}"/></title>
    </head>
    <body>
        <div class="settings-container">
            <div class="content">
                <div class="header">
                    <h2><fmt:message bundle="${lang}" key="settings"/></h2>
                </div>
                <div class="settings">
                    <div class="setting-item">
                        <span><fmt:message bundle="${lang}" key="changepass"/></span>
                        <a class="manage-btn" href="Changepassword.jsp"><fmt:message key="change" bundle="${lang}"/></a>
                    </div>
                    <div class="setting-item">
                        <span><fmt:message key="language" bundle="${lang}"/></span>
                            <a class="manage-btn" href="?lang=en"><fmt:message key="en" bundle="${lang}"/></a>
                            <a class="manage-btn" href="?lang=vi"><fmt:message key="vi" bundle="${lang}"/></a>
                    </div>
<!--                    <div class="setting-item">
                        <span><fmt:message key="payment" bundle="${lang}"/></span>
                        <a class="manage-btn" href="deliverypayment.jsp"><fmt:message key="update" bundle="${lang}"/></a>
                    </div>               -->
                    <div class="setting-item">
                        <span><fmt:message key="term" bundle="${lang}"/></span>
                        <a class="manage-btn" href="deliveryterms.jsp"><fmt:message key="details" bundle="${lang}"/></a>
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
        <script>
            function changeLanguage(lang) {
                // Lưu ngôn ngữ vào localStorage
                localStorage.setItem('selectedLanguage', lang);
                // Chuyển đến URL ngôn ngữ được chọn
                window.location.href = lang;
            }

            // Kiểm tra và đặt ngôn ngữ đã lưu khi tải trang
            document.addEventListener('DOMContentLoaded', (event) => {
                const savedLanguage = localStorage.getItem('selectedLanguage');
                if (savedLanguage) {
                    document.getElementById('language-select').value = savedLanguage;
                }
            });
        </script>
    </body>
</html>
