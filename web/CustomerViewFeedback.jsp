<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>List Feedback HTML</title>
        <link rel="shortcut icon" href="images/fav-icon.png"/>
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
        <script src="https://kit.fontawesome.com/c8e4d183c2.js" crossorigin="anonymous"></script>
        <style>
            * {
                margin: 0;
                padding: 0;
                font-family: poppins;
                box-sizing: border-box;
            }
            a {
                text-decoration: none;
            }
            #testimonials {
                display: flex;
                justify-content: center;
                align-items: center;
                flex-direction: column;
                width: 100%;
            }
            .testimonial-heading {
                letter-spacing: 1px;
                margin: 30px 0px;
                padding: 10px 20px;
                display: flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;
            }
            .testimonial-heading h1 {
                font-size: 2.2rem;
                font-weight: 500;
                background-color: #202020;
                color: #ffffff;
                padding: 10px 20px;
            }
            .testimonial-heading span {
                font-size: 1.3rem;
                color: #252525;
                margin-bottom: 10px;
                letter-spacing: 2px;
                text-transform: uppercase;
            }
            .testimonial-box-container {
                display: flex;
                justify-content: center;
                align-items: center;
                flex-wrap: wrap;
                width: 100%;
            }
            .testimonial-box {
                width: 500px;
                box-shadow: 2px 2px 30px rgba(0, 0, 0, 0.1);
                background-color: #ffffff;
                padding: 20px;
                margin: 15px;
                cursor: pointer;
            }
            .profile-img {
                width: 50px;
                height: 50px;
                border-radius: 50%;
                overflow: hidden;
                margin-right: 10px;
            }
            .profile-img img {
                width: 100%;
                height: 100%;
                object-fit: cover;
                object-position: center;
            }
            .profile {
                display: flex;
                align-items: center;
            }
            .name-user {
                display: flex;
                flex-direction: column;
            }
            .name-user strong {
                color: #3d3d3d;
                font-size: 1.1rem;
                letter-spacing: 0.5px;
            }
            .name-user span {
                color: #979797;
                font-size: 0.8rem;
            }
            .reviews {
                color: #f9d71c;
            }
            .box-top {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 20px;
            }
            .client-comment p {
                font-size: 0.9rem;
                color: #4b4b4b;
            }
            .testimonial-box:hover {
                transform: translateY(-10px);
                transition: all ease 0.3s;
            }
            @media (max-width: 1060px) {
                .testimonial-box {
                    width: 45%;
                    padding: 10px;
                }
            }
            @media (max-width: 790px) {
                .testimonial-box {
                    width: 100%;
                }
                .testimonial-heading h1 {
                    font-size: 1.4rem;
                }
            }
            @media (max-width: 340px) {
                .box-top {
                    flex-wrap: wrap;
                    margin-bottom: 10px;
                }
                .reviews {
                    margin-top: 10px;
                }
            }
            ::selection {
                color: #ffffff;
                background-color: #252525;
            }
            .feedback-actions{
                margin-top: 20px;
            }
        </style>
    </head>
    <body>
        <section id="testimonials">
            <div class="testimonial-heading">
                <span>Comments</span>
                <h1>Clients Says</h1>
            </div>
            <div class="testimonial-box-container">
                <c:if test="${feedback != null}">
                    <div class="testimonial-box">
                        <div class="box-top">
                            <div class="profile">
                                <div class="profile-img">
                                    <img src="${feedback.img}" alt="Profile Image"/>
                                </div>
                                <div class="name-user">
                                    <strong>${customerNames[feedback.customerID]}</strong>
                                    <span>Customer ID: ${feedback.customerID}</span>
                                </div>
                            </div>
                            <div class="reviews">
                                <c:forEach begin="1" end="${feedback.value}" varStatus="star">
                                    <i class="fas fa-star"></i>
                                </c:forEach>
                                <c:forEach begin="${feedback.value + 1}" end="5">
                                    <i class="far fa-star"></i>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="name-user">
                            <strong>Restaurant: ${restaurantNames[feedback.restaurantID]}</strong>
                            <span>Restaurant ID: ${feedback.restaurantID}</span>
                        </div>
                        <div class="client-comment">
                            <br>
                            <strong>Reviews: </strong>
                            <p>${feedback.description}</p>
                        </div>
                        <div class="feedback-actions">
                            <a href="updatefeedback?orderId=${feedback.orderID}&customerId=${customerId}&productId=${productId}">Update</a><br>
                            <a href="deletefeedback?orderId=${feedback.orderID}&productId=${productId}" onclick="return confirm('Are you sure you want to delete this feedback?')">Delete</a>
                        </div>
                    </div>
                </c:if>
                <c:if test="${feedback == null}">
                    <p>No feedback found for this order.</p>
                </c:if>
            </div>
        </section>
    </body>
</html>
