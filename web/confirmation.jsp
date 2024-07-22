<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="deliverychange.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Confirm</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            width: 100%;
            text-align: center;
        }
        h1 {
            color: #333;
            margin-bottom: 20px;
        }
        p {
            color: #666;
        }
        .countdown {
            margin-top: 20px;
            font-size: 18px;
            color: #e74c3c;
            font-weight: bold;
        }
    </style>
    <script>
        function redirectAfterDelay() {
            const delay = 5000; // 5 seconds
            let remainingTime = delay / 1000; // Convert milliseconds to seconds

            function updateCountdown() {
                const countdownElement = document.getElementById('countdown');
                if (countdownElement) {
                    countdownElement.innerText = `You are being redirected${remainingTime}...`;
                    remainingTime--;

                    if (remainingTime < 0) {
                        window.location.href = 'deliverydashboard'; // Thay đổi địa chỉ trang bạn muốn chuyển hướng tới
                    }
                }
            }

            // Update countdown immediately and then every second
            updateCountdown();
            setInterval(updateCountdown, 1000);
        }

        window.onload = redirectAfterDelay;
    </script>
</head>
<body>
    <div class="container">
        <h1>Thank you for submitting your report!</h1>
        <p>We have received your feedback and will respond as soon as possible.</p>
        <p id="countdown" class="countdown">You are being redirected...</p>
    </div>
</body>
</html>
