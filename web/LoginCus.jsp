<%-- 
    Document   : Login
    Created on : May 23, 2024, 4:29:35 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
    <head>
        <title>Login</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap" rel="stylesheet">

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
                    <div class="col-md-6 text-center mb-5">
                        <h2 class="heading-section">Grubhub</h2>
                    </div>
                </div>
                <div class="row justify-content-center">
                    <div class="col-md-12 col-lg-10">
                        <div class="wrap d-md-flex">
                            <div class="img" style="background-image: url(images/Burger.jpg);">
                            </div>
                            <div class="login-wrap p-4 p-md-5">
                                <div class="d-flex">
                                    <div class="w-100">
                                        <h3 class="mb-4">Sign-in</h3>
                                    </div>
                                    <div class="w-100">
                                        </p>
                                    </div>
                                </div>

                                <form id="login-form" action="cuslogin" method="POST" class="signin-form">
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
                                        <button type="button" onclick="checkCaptcha()" class="form-control btn btn-primary rounded submit px-3">Sign In</button>
                                    </div>
                                    <div class="form-group d-md-flex">
                                        <div class="w-50 text-left">
                                            <label class="checkbox-wrap checkbox-primary mb-0">Remember Me
                                                <input type="checkbox" name="remember" value="1">
                                                <span class="checkmark"></span>
                                            </label>
                                        </div>
                                        <div class="w-50 text-md-right">
                                            <a href="forgotpassword.jsp">Forgot Password</a>
                                            
                                        </div>
                                    </div>
                                </form>
                                <p class="text-center">Don't have a account? <a href="registercustomer.jsp">Sign Up</a></p>
                                 <p class="text-center"> <a href="login">Others</a></p>
                                <div class="g-recaptcha" data-sitekey="6LclNuwpAAAAAJ15fzuD-_YURoq0-XxvEub_ZkDF"></div>
                                <div style="color: red" id="error"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <script src="js/jquery.min.js"></script>
        <script src="js/popper.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/main.js"></script>
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
        <jsp:include page="Footer.jsp"></jsp:include>
            <script type="text/javascript">
                                            function checkCaptcha() {
                                                var form = document.getElementById("login-form");
                                                var error = document.getElementById("error");
                                                const response = grecaptcha.getResponse();
                                                if (response) {
                                                    form.submit();
                                                } else {
                                                    error.textContent = "Please verify that you not a robot!";
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
    </body>
</html>


<!--<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login form</h1>
        <form action="login" method="post">
            <p class="text-danger">${mess}</p>
            username: <input type="text" name="username" value="${username}"/><br>
            password: <input type="text" name="password" value="${password}"/><br>
            remember <input type="checkbox" name="remember" value="1"> <a href=forgotpass.jsp">              forgot password?</a><br>
            <input type="submit" value="Login"/><br>
            <p> </p>
            
        </form>
    </body>
</html>-->
