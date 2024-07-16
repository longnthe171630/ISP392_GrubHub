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

        <jsp:include page="Hearder.jsp"></jsp:include>

        <!-- header section ends-->

        <!-- search form  -->

        <section class="order" id="contact">

            <!--        <h3 class="sub-heading"> order now </h3>-->
            <h1 class="heading"> Send your query </h1>

            <form action="contact" method="post">
                <div class="inputBox">
                    <div class="input">
                        <span>your name</span>
                        <input type="text" name="name" placeholder="enter your name" required>
                    </div>
                </div>
                <div class="inputBox">
                    <div class="input">
                        <span>your number</span>
                        <input type="text" name="number" placeholder="enter your number" required>
                    </div>
                </div>
                <div class="inputBox">
                    <div class="input">
                        <span>your email</span>
                        <input type="email" name="email" placeholder="enter your email" required>
                    </div>
                </div>
                <div class="inputBox">
                    <div class="input">
                        <span>subject</span>
                        <textarea name="subject" placeholder="enter your subject" cols="30" rows="10" required></textarea>
                    </div>
                </div>
                <div class="inputBox">
                    <div class="input">
                        <span>your message</span>
                        <textarea name="message" placeholder="enter your query/feedback" cols="30" rows="10" required></textarea>
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
