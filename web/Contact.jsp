<%-- 
    Document   : contact
    Created on : May 25, 2024, 9:30:59 PM
    Author     : manh0
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>GrubHub</title>

        <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />

        <!-- font awesome cdn link  -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">

        <!-- custom css file link  -->
        <link rel="stylesheet" href="css/style.css">

    </head>

    <body>

        <!-- header section starts      -->

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
                <a href="login" class="login-btn">login</a>

            </div> 

        </header>

        <!-- header section ends-->

        <!-- search form  -->

        <form action="" id="search-form">
            <input type="search" placeholder="search here..." name="" id="search-box">
            <label for="search-box" class="fas fa-search"></label>
            <i class="fas fa-times" id="close"></i>
        </form>

        <section class="order" id="contact">

            <!--        <h3 class="sub-heading"> order now </h3>-->
            <h1 class="heading"> Send your query </h1>

            <form action="">

                <div class="inputBox">
                    <div class="input">
                        <span>your name</span>
                        <input type="text" placeholder="enter your name">
                    </div>
                </div>
                <div class="inputBox">
                    <div class="input">
                        <span>your number</span>
                        <input type="text" placeholder="enter your number">
                    </div>
                </div>
                <div class="inputBox">
                    <div class="input">
                        <span>subject</span>
                        <textarea name="" placeholder="enter your subject" id="" cols="30" rows="10"></textarea>
                    </div>
                </div>
                <div class="inputBox">
                    <div class="input">
                        <span>your message</span>
                        <textarea name="" placeholder="enter your query/feedback" id="" cols="30" rows="10"></textarea>
                    </div>
                </div>

                <input type="submit" value="Submit" class="btn">

            </form>

        </section>
        <jsp:include page="Footer.jsp"></jsp:include>
        <div class="loader-container">
            <img src="images/loader.gif" alt="">
        </div>

        <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>

        <!-- custom js file link  -->
        <script src="js/script.js"></script>
    </body>
</html>
