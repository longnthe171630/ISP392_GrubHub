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
        <div class="settings-container">
            <div class="content">
                <div class="header">
                    <h2>Settings</h2>
                </div>
                <div class="settings">
                    <div class="setting-item">
                        <span>Khu vực hoạt động</span>
                        <input id="areaInput" type="text" placeholder="Nhập khu vực của bạn" value = 'Hòa Lạc'>
                    </div>
                    <div class="setting-item">
                        <span>Loại phương tiện</span>
                        <select>
                            <option value="motorbike">Motorbike</option>
                            <option value="car">Car</option>
                            <option value="bicycle">Bicycle</option>
                            <option value="other">Other</option>
                        </select>
                    </div>
                    <div class="setting-item">
                        <span>Lịch sử đăng nhập</span>
                        <a class="manage-btn" href="historyactive.jsp">Xem hoạt động</a>
                    </div>
                    <div class="setting-item">
                        <span>Thông tin thanh toán</span>
                        <a class="save-all-btn" href="deliverypayment.jsp">Chi tiết </a>
                    </div>               
                    <div class="setting-item">
                        <span>Xác thực 2 yếu tố</span>
                        <label class="switch">
                            <input type="checkbox">
                            <span class="slider round"></span>
                        </label>
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
