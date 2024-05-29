<!DOCTYPE html>
<html>
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <!doctype html>
    <html lang="en">
        <head>
            <title>Forgot Password</title>
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
            <header>
                <a href="home" class="logo"><i class="fas fa-utensils"></i>GrubHub</a>

                <nav class="navbar">
                    <a class="active" href="home">home</a>
                    <a href="About.jsp">about</a>
                    <a href="Contact.jsp">contact</a>
                </nav>

                <div class="icons">
                    <i class="fas fa-bars" id="menu-bars"></i>
                    <i class="fas fa-search" id="search-icon"></i>
                    <a href="#" class="fas fa-shopping-cart"></a>
                    <a href="Login.jsp" class="login-btn">login</a>

                </div> 

            </header>
            <section class="ftco-section">
                <div class="container">
                    <!--                    <div class="row justify-content-center">
                                            <div class="col-md-6 text-center mb-5">
                                                <h2 class="heading-section">Grubhub</h2>
                                            </div>
                                        </div>-->
                    <div class="row justify-content-center">
                        <div class="col-md-12 col-lg-10">
                            <div class="wrap d-md-flex">
                                <div class="img" style="background-image: url(images/Burger.jpg);">
                                </div>
                                <div class="login-wrap p-4 p-md-5">
                                    <div class="d-flex">
                                        <div class="w-100">
                                            <h3 class="mb-4" style="font-size: 1.5em;">Forgot Password</h3>
                                        </div>
                                        <div class="w-100">
                                        </div>
                                    </div>
                                    <form action="/ISP392_Grubhub/forgot" method="POST" class="signin-form">
                                        <p class="text-danger">
                                            ${requestScope.alert}
                                        </p>
                                        <div class="form-group mb-3">
                                            <label class="label" for="name">Email:</label>
                                            <input type="email" name="email" value="${email}"class="form-control" placeholder="Email" required>
                                        </div>
                                        <div class="form-group mb-3">
                                            <label class="label" for="password">Enter Captcha</label>
                                            <div class="captcha-container">
                                                <input id="pass" type="text" name="captcha" value="${password}" class="form-control" placeholder="Captcha" required>
                                                <div class="captcha-elements" >
                                                    <span class="captcha-box" id="captcha-text" ></span>
                                                    <button class="captcha-refresh-btn" type="button" onclick="generateCaptcha()">
                                                        <img src="images/icon/ReCAPTCHA_icon.jpg" alt="Refresh" style="width: 40px; height: 40px;">
                                                    </button>

                                                </div>
                                            </div>
                                        </div>
                                        <br>
                                        <div class="form-group">
                                            <button type="submit"  class="form-control btn btn-primary rounded submit px-3">Reset Password</button>
                                        </div>
                                    </form>
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
            <script>
                                                        function generateCaptcha() {
                                                            var captcha = Math.random().toString(36).substring(2, 8);
                                                            document.getElementById("captcha-text").textContent = captcha;
                                                            document.cookie = "captcha=" + captcha + "; path=/";
                                                        }
                                                        generateCaptcha();
            </script>
            <script>
                function refreshCaptcha() {
                    var captchaImage = document.getElementById('images/icon/ReCAPTCHA_icon.jpg');
                    captchaImage.src = 'generate_captcha.php?' + Date.now(); // Adding a timestamp to the URL to avoid caching
                }
            </script>
            <jsp:include page="Footer.jsp"></jsp:include>
        </body>
    </html>