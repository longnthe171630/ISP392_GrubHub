<%-- 
    Document   : registercustomer
    Created on : May 28, 2024, 3:06:32 PM
    Author     : phaml
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Responsive Registration Form</title>
        <link rel="stylesheet" href="css/registercustomer.css">
    </head>
    <body>
        <div class="container">
            <div class="title">Registration Account</div>
            <div class="content">
                <form action="registercustomer" method="post">
                    <div class="user-details">
                        <div class="input-box">
                            <span class="details">Full Name</span>
                            <input type="text" name="name" placeholder="Enter your name" required>
                        </div>
                        <div class="input-box">
                            <span class="details">Username</span>
                            <input type="text" name="username" placeholder="Enter your username" required>
                        </div>
                        <div class="input-box">
                            <span class="details">Email</span>
                            <input type="email" name="email" placeholder="Enter your email" required>
                        </div>
                        <div class="input-box">
                            <span class="details">Phone Number</span>
                            <input type="text" name="phonenumber" placeholder="Enter your number" required>
                        </div>
                        <div class="input-box">
                            <span class="details">Password</span>
                            <input type="password" name="password" placeholder="Enter your password" required>
                        </div>
                        <div class="input-box">
                            <span class="details">Confirm Password</span>
                            <input type="password" name="cpassword" placeholder="Confirm your password" required>
                        </div>
                        <div class="input-box">
                            <span class="details">Date of Birth</span>
                            <input type="date" name="dob" required>
                        </div>
                        <div class="input-box">
                            <span class="details">State</span>
                            <input type="text" name="state" placeholder="Enter your state" required>
                        </div>
                        <div class="input-box">
                            <span class="details">Street</span>
                            <input type="text" name="street" placeholder="Enter your street" required>
                        </div>
                        <div class="input-box">
                            <span class="details">Detail Address</span>
                            <input type="text" name="detailaddress" placeholder="Enter your detail address" required>
                        </div>
                    </div>
                    <div class="gender-details">
                        <input type="radio" name="gender" id="dot-1" value="male" required>
                        <input type="radio" name="gender" id="dot-2" value="female">
                        <span class="gender-title">Gender</span>
                        <div class="category">
                            <label for="dot-1">
                                <span class="dot one"></span>
                                <span class="gender">Male</span>
                            </label>
                            <label for="dot-2">
                                <span class="dot two"></span>
                                <span class="gender">Female</span>
                            </label>
                        </div>
                    </div>
                    <div class="button">
                        <input type="submit" value="Register">
                    </div>
                    <div>
                        <span style="color: red;" id="error-msg">
                            <!-- This will be populated with error messages if any -->
                            <% if (request.getAttribute("msg") != null) { %>
                            <%= request.getAttribute("msg") %>
                            <% } %>
                        </span>
                    </div>
                </form>
            </div>
        </div>
    </body>
    <script type="text/javascript">
        window.onload = function () {
            // Lấy thông điệp từ biến JavaScript
            var msg = "<%= request.getAttribute("msg") != null ? request.getAttribute("msg") : "" %>";
            if (msg) {
                // Hiển thị thông điệp bằng alert hoặc bạn có thể tùy chỉnh cách hiển thị
                alert(msg);
            }
        };
    </script>
</html>


