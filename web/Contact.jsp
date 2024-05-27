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
    </body>
</html>
