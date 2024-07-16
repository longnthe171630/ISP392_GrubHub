<%-- 
    Document   : Login
    Created on : May 23, 2024, 4:29:35 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
    <head>
        <title>Login</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="css/style_1.css">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />

        <!-- font awesome cdn link  -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    </head>
    <body>
        <!-- header section starts      -->
        <header>
            <a href="home" class="logo"><i class="fas fa-utensils"></i>GrubHub</a>

            <nav class="navbar">
                <a class="active" href="home">Home</a>
                <a href="About.jsp">About</a>
                <a href="Contact.jsp">Contact</a>
            </nav>

            <div class="icons">
                <i class="fas fa-bars" id="menu-bars"></i>
                <i class="fas fa-search" id="search-icon"></i>
                <a href="#" class="fas fa-shopping-cart"></a>
                <a href="Login.jsp" class="login-btn">Login</a>

            </div> 
        </header>
        <!-- header section ends-->
        <!-- search form  -->
        <form action="search" method="post" id="search-form">
            <input value="${txtS}" name="txt" type="search" placeholder="What do you want?" id="search-box">
            <label for="search-box" class="fas fa-search"></label>
            <i class="fas fa-times" id="close"></i>
        </form>

        <section class="ftco-section">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-md-12 col-lg-10">
                        <div class="wrap d-md-flex">
                            <div class="img" style="background-image: url(images/AccountLoginPic.jpg);">
                            </div>
                            <div class="login-wrap p-4 p-md-5">
                                <div class="d-flex">
                                    <div class="w-100">
                                        <h3 class="mb-4" style="font-size: 1.5em;">Sign In</h3>
                                    </div>
                                    <div class="w-100">
                                        </p>
                                    </div>
                                </div>

                                <form id="login-form" action="login" method="POST" class="signin-form">
                                    <p class="text-danger " >
                                        ${requestScope.alert}
                                    </p>
                                    <div class="form-group mb-3">
                                        <label class="label" for="name">Username</label>
                                        <input type="text" name="username" value="${username}"class="form-control" placeholder="Username" required>
                                    </div>
                                    <div class="form-group mb-3">
                                        <label class="label" for="password">Password</label>
                                        <input id="pass" type="password" name="password" value="${password}" class="form-control" placeholder="Password" required >
                                    </div>
                                    <div class="form-group">
                                        <button type="submit" onclick="checkCaptcha()" class="form-control btn btn-primary rounded submit px-3">Sign In</button>
                                    </div>
                                    <div class="form-group d-md-flex">
                                        <div class="w-50 text-left">
                                            <label class="checkbox-wrap checkbox-primary mb-0">Remember Me
                                                <input type="checkbox" name="remember" value="1">
                                                <span class="checkmark"></span>
                                            </label>
                                        </div>
                                        <div class="w-50 text-md-right">
                                            <a href="forgotpassword.jsp">Forgot Password?</a>
                                        </div>
                                    </div>
                                </form>
                                <p class="text-center">Don't have a account? <a href="registercustomer.jsp">Sign Up</a></p>
                                <p class="text-center">Sign up to join the system as a Restaurant or shipper <a href="RegisterAccount.jsp">Sign up to be one of us</a></p>
                                <div class="g-recaptcha" data-sitekey="6LclNuwpAAAAAJ15fzuD-_YURoq0-XxvEub_ZkDF"></div>
                                <div style="color: red" id="error"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
        <script src="js/jquery.min.js"></script>
        <script src="js/popper.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/main.js"></script>
        <script src="js/script.js"></script>
        <jsp:include page="Footer.jsp"></jsp:include>
        </body>
        <script type="text/javascript">
                                            function checkCaptcha() {
                                                var form = document.getElementById("login-form");
                                                var error = document.getElementById("error");
                                                const response = grecaptcha.getResponse();
//                                              console.log(response);
                                                if (response) {
                                                    form.submit();
                                                }else{
                                                    error.textContent ="Please verify that you not a robot!";
                                                }
                                            }
        </script>
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

</web-app>

</html>



