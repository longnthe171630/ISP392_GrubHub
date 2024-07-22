<!DOCTYPE html>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="deliverychange.jsp" %>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Điều Khoản và Dịch Vụ cho Người Giao Hàng</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- Boxicons -->
        <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <!-- My CSS -->
        <link rel="stylesheet" href="css/style_ship.css">
        <!-- Thư viện SweetAlert -->
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://cdn.jsdelivr.net/gh/somanchiu/Keyless-Google-Maps-API@v6.6/mapsJavaScriptAPI.js" async defer></script>
        <title>More Information</title>
    </head>
    <body>
<!--        <div class="container">
            <button class="back-button" onclick="goBack()">
                <i class="fas fa-arrow-left"></i>
            </button>
            <h1>Điều Khoản và Dịch Vụ</h1>
            <h2>1. Giới Thiệu</h2>
            <p>Chào mừng bạn đến với dịch vụ giao hàng của Grubhub. Chúng tôi cung cấp nền tảng kết nối người dùng với các nhà hàng và dịch vụ giao hàng nhanh chóng, tiện lợi. Bằng việc đăng ký và sử dụng dịch vụ của chúng tôi, bạn đồng ý với các điều khoản và điều kiện dưới đây.</p>

            <h2>2. Đăng Ký và Sử Dụng Tài Khoản</h2>
            <ul>
                <li>Người giao hàng phải cung cấp thông tin cá nhân chính xác và đầy đủ khi đăng ký tài khoản.</li>
                <li>Tài khoản phải được bảo mật. Bạn chịu trách nhiệm về mọi hoạt động diễn ra dưới tài khoản của mình.</li>
            </ul>

            <h2>3. Trách Nhiệm của Người Giao Hàng</h2>
            <ul>
                <li>Nhận đơn hàng từ nhà hàng và giao đến khách hàng trong thời gian cam kết.</li>
                <li>Đảm bảo chất lượng món ăn và đồ uống trong suốt quá trình giao hàng.</li>
                <li>Thực hiện giao hàng một cách chuyên nghiệp, lịch sự và tuân thủ quy định giao thông.</li>
            </ul>

            <h2>4. Quyền Lợi của Người Giao Hàng</h2>
            <ul>
                <li>Nhận phí giao hàng và tiền thưởng theo quy định của Grubhub.</li>
                <li>Được hỗ trợ và giải quyết các vấn đề phát sinh trong quá trình giao hàng.</li>
                <li>Được cung cấp các khóa đào tạo và hướng dẫn cần thiết để nâng cao kỹ năng giao hàng.</li>
            </ul>

            <h2>5. Thanh Toán</h2>
            <ul>
                <li>Người giao hàng sẽ nhận tiền công từ Grubhub theo chu kỳ thanh toán.</li>
                <li>Các khoản thu nhập từ tiền công giao hàng và tiền thưởng sẽ được chuyển vào tài khoản ngân hàng mà bạn đã đăng ký.</li>
            </ul>

            <h2>6. Chấm Dứt và Hủy Bỏ Tài Khoản</h2>
            <ul>
                <li>Grubhub có quyền chấm dứt tài khoản của người giao hàng nếu phát hiện hành vi vi phạm điều khoản dịch vụ hoặc có hành vi gian lận.</li>
                <li>Người giao hàng có thể hủy bỏ tài khoản bất cứ lúc nào bằng cách thông báo cho Grubhub.</li>
            </ul>

            <h2>7. Quy Định Chung</h2>
            <ul>
                <li>Người giao hàng phải tuân thủ mọi quy định và hướng dẫn từ Grubhub.</li>
                <li>Mọi tranh chấp phát sinh sẽ được giải quyết theo luật pháp hiện hành của Việt Nam.</li>
            </ul>

            <h2>8. Thay Đổi Điều Khoản Dịch Vụ</h2>
            <p>Grubhub có quyền thay đổi và cập nhật các điều khoản dịch vụ bất cứ lúc nào. Mọi thay đổi sẽ được thông báo trước cho người giao hàng qua email hoặc thông báo trên ứng dụng.</p>

            <div class="contact-info">
                <h2>9. Liên Hệ</h2>
                <p>Nếu bạn có bất kỳ câu hỏi nào về điều khoản và dịch vụ, vui lòng liên hệ với chúng tôi qua:</p>
                <ul>
                    <li>Email: abc@gmail.com</li>
                    <li>Số điện thoại: 0901211234</li>
                </ul>
            </div>

            <p>Chúng tôi mong muốn hợp tác lâu dài và bền vững với các bạn để mang lại dịch vụ giao hàng tốt nhất cho khách hàng của Grubhub.</p>
            <p>Trân trọng,<br>[Ban Quản Trị - Grubhub]</p>
        </div>-->


        <div class="container">
            <button class="back-button" onclick="goBack()">
                <i class="fas fa-arrow-left"></i>
            </button>
            <h1>Terms and Services</h1>
            <h2>1. Introduction</h2>
            <p>Welcome to Grubhub's delivery service. We provide a platform that connects users with restaurants and offers fast and convenient delivery services. By registering and using our service, you agree to the terms and conditions below.</p>
            <h2>2. Account Registration and Usage</h2>
            <ul>
                <li>Delivery personnel must provide accurate and complete personal information when registering an account.</li>
                <li>Accounts must be kept secure. You are responsible for all activities that occur under your account.</li>
            </ul>
            <h2>3. Responsibilities of Delivery Personnel</h2>
            <ul>
                <li>Receive orders from restaurants and deliver them to customers within the committed time.</li>
                <li>Ensure the quality of food and beverages during the delivery process.</li>
                <li>Perform deliveries professionally, courteously, and in compliance with traffic regulations.</li>
            </ul>
            <h2>4. Rights of Delivery Personnel</h2>
            <ul>
                <li>Receive delivery fees and bonuses as per Grubhub's regulations.</li>
                <li>Receive support and resolution for any issues arising during the delivery process.</li>
                <li>Be provided with necessary training and guidance to improve delivery skills.</li>
            </ul>
            <h2>5. Payment</h2>
            <ul>
                <li>Delivery personnel will receive compensation from Grubhub according to the payment cycle.</li>
                <li>Income from delivery fees and bonuses will be transferred to the bank account you registered.</li>
            </ul>
            <h2>6. Termination and Account Cancellation</h2>
            <ul>
                <li>Grubhub reserves the right to terminate the account of any delivery personnel found to be in violation of the service terms or engaging in fraudulent activities.</li>
                <li>Delivery personnel can cancel their account at any time by notifying Grubhub.</li>
            </ul>
            <h2>7. General Regulations</h2>
            <ul>
                <li>Delivery personnel must comply with all regulations and guidelines from Grubhub.</li>
                <li>Any disputes arising will be resolved according to the current laws of Vietnam.</li>
            </ul>
            <h2>8. Changes to the Service Terms</h2>
            <p>Grubhub reserves the right to change and update the service terms at any time. All changes will be notified to delivery personnel via email or through app notifications.</p>
            <div class="contact-info">
                <h2>9. Contact</h2>
                <p>If you have any questions regarding the terms and services, please contact us at:</p>
                <ul>
                    <li>Email: abc@gmail.com</li>
                    <li>Phone: 0901211234</li>
                </ul>
            </div>
            <p>We look forward to a long and sustainable cooperation with you to provide the best delivery service for Grubhub's customers.</p>
            <p>Sincerely,<br>[Grubhub Management Team]</p>
        </div>
    </body>
    <style>
        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
            background-image: url('images/icon/avatar1.jpg'); /* Thay đổi đường dẫn đến ảnh của bạn */
            background-size: cover; /* Đảm bảo ảnh phủ kín toàn bộ */
            background-repeat: no-repeat; /* Không lặp ảnh */
            background-position: center; /* Căn giữa ảnh */
        }
        .container {
            max-width: 800px;
            margin: 50px auto;
            padding: 20px;
            background: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1, h2 {
            color: #333;
        }
        h1 {
            text-align: center;
            margin-bottom: 20px;
        }
        ul {
            padding-left: 20px;
        }
        p, li {
            margin-bottom: 10px;
        }
        .contact-info {
            margin-top: 20px;
            padding-top: 20px;
            border-top: 1px solid #ddd;
        }
    </style>
    <script>
        function goBack() {
            window.location.href = 'deliverydashboard';
        }
    </script>
</html>
