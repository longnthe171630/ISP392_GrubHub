<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="deliverychange.jsp" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Payment Settings</title>
        <link rel="stylesheet" href="css/payment.css">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    </head>

    <body>
        <div class="wrapper">
            <button class="back-button" onclick="goBack()">
                <i class="fas fa-arrow-left"></i>
            </button>
            <h2>Payment</h2>
            <form action="" method="post">
                <!--Account Information Start-->
                <h4>Account</h4>
                <div class="input_group">
                    <div class="input_box">
                        <input type="text" placeholder="Cardholder's Name" required class="name">
                        <i class="fa fa-user icon"></i>
                    </div>
                </div>
                <h4>Email</h4>
                <div class="input_group">
                    <div class="input_box">
                        <input type="email" placeholder="...@example.com" required class="name">
                        <i class="fa fa-envelope icon"></i>
                    </div>
                </div>
                <h4>Billing Address</h4>
                <div class="input_group">
                    <div class="input_box">
                        <input type="text" placeholder="Address" required class="name">
                        <i class="fa fa-map-marker icon" aria-hidden="true"></i>
                    </div>
                </div>
                <h4>Phone Number</h4>
                <div class="input_group">
                    <div class="input_box">
                        <input type="tel" placeholder="0123456789" required class="name">
                        <i class="fas fa-phone icon"></i>
                    </div>
                </div>
                <h4>Card Number</h4>
                <div class="input_group">
                    <div class="input_box">
                        <input type="tel" id="cardNumber" class="name" placeholder="1234 5678 2468 3579" required class="name">
                        <i class="fa fa-credit-card icon"></i>
                    </div>
                </div>
                <h4>Code</h4>
                <div class="input_group">
                    <div class="input_box">
                        <input type="tel" name="" class="name" placeholder="CVV" required class="name">
                        <i class="fas fa-code icon"></i>
                    </div>
                </div>
                <h4>Expiration Date</h4>
                <div class="input_group">
                    <div class="input_box">
                        <div class="input_box">
                            <input type="date" required class="name">
                            <i class="fa fa-calendar icon" aria-hidden="true"></i>
                        </div>
                    </div>
                </div>
                <!--Payment Details End-->
                <div class="input_group">
                    <div class="input_box">
                        <button type="submit" onclick="validateDate()">SAVE</button>
                    </div>
                </div>
            </form>
        </div>
        <script>
            document.getElementById('cardNumber').addEventListener('input', function (e) {
                let value = e.target.value.replace(/\D/g, ''); // Xóa ký tự không phải số
                value = value.replace(/(\d{4})(?=\d)/g, '$1 '); // Thêm khoảng trắng mỗi 4 số
                e.target.value = value.trim(); // Cập nhật giá trị input
            });
            function goBack() {
                window.history.back();
            }
        </script>
    </body>
</html>