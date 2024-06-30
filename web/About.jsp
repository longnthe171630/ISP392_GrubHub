<%-- 
    Document   : About
    Created on : May 25, 2024, 9:57:11 PM
    Author     : manh0
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />

        <!-- font awesome cdn link  -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">

        <!-- custom css file link  -->
        <link rel="stylesheet" href="css/style.css">
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
                <a href="#" class="fas fa-heart"></a>
                <a href="#" class="fas fa-shopping-cart"></a>
                <a href="login" class="login-btn">login</a>

            </div> 

        </header>
        <br>

        <section class="about" id="about">

            <h3 class="sub-heading"> about us </h3>
            <h1 class="heading"> why choose us? </h1>

            <div class="row">

                <div class="image">
                    <img src="images/about-img.png" alt="">
                </div>

                <div class="content">
                    <h3>Vì sao bạn nên Order trên GrubHub?</h3>
                    <p>Nhanh nhất - GrubHub cung cấp dịch vụ giao đồ ăn nhanh nhất thị trường.
                    <p> Dễ dàng nhất - Giờ đây, bạn chỉ cần thực hiện vài cú nhấp chuột hoặc chạm nhẹ là đã có thể đặt đồ ăn. 
                        Hãy đặt đồ ăn trực tuyến trên website của GrubHub của chúng tôi để có trải nghiệm nhanh hơn và thú vị hơn.</p>
                    <p> Đáp ứng mọi nhu cầu - Từ món ăn đặc sản địa phương đến các nhà hàng được ưa thích, nhiều lựa chọn đa dạng chắc chắn sẽ luôn làm hài lòng khẩu vị của bạn.</p>
                    <p>Thanh toán dễ dàng - Giao và nhận đồ ăn thật dễ dàng.</p>
                    <p>We hope you will enjoy your visit with us!</p>                   
                    <div class="icons-container">
                        <div class="icons">
                            <i class="fas fa-shipping-fast"></i>
                            <span>Miễn phí vận chuyển</span>
                        </div>
                        <div class="icons">
                            <i class="fas fa-dollar-sign"></i>
                            <span>thanh toán dễ dàng</span>
                        </div>
                        <div class="icons">
                            <i class="fas fa-headset"></i>
                            <span>24/7 service</span>
                        </div>
                    </div>
                    <a href="#" class="btn">learn more</a>
                </div>

            </div>

        </section>
        <jsp:include page="Footer.jsp"></jsp:include>
    </body>
</html>
