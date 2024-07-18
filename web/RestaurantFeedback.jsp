<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Restaurant Feedback</title>
        <!--Stylesheet--------------------------->
        <link rel="shortcut icon" href="images/fav-icon.png"/>
        <!--poppins-font-family------------------->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900&display=swap" rel="stylesheet">
        <!--using-Font-Awesome-------------------->
        <script src="https://kit.fontawesome.com/c8e4d183c2.js" crossorigin="anonymous"></script>
        <style>
            * {
                margin: 0px;
                padding: 0px;
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
        </style>
    </head>
    <body>
        <section id="testimonials">
            <div class="testimonial-heading">
                <span>Comments</span>
                <h1>Clients Rating</h1>
            </div>
            <form action="restaurantfeedback" method="get">
                <div>
                    <label for="sort">Sort by value:</label>
                    <select name="sort" id="sort">
                        <option value="asc">Ascending</option>
                        <option value="desc">Descending</option>
                    </select>
                    <button type="submit">Sort</button>
                </div>
            </form>

            <div class="testimonial-box-container">
                <c:choose>
                    <c:when test="${not empty feedbackList}">
                        <c:forEach var="feedback" items="${feedbackList}">
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
                                        <c:forEach begin="${feedback.value + 1}" end="5" varStatus="emptyStar">
                                            <i class="far fa-star"></i>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div class="client-comment">
                                    <br>
                                    <strong>Reviews: </strong>
                                    <p>${feedback.description}</p><br>      
                                    <p>on product: ${feedback.productID}</p>
                                </div>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <p>No feedback available.</p>
                    </c:otherwise>
                </c:choose>
            </div>
        </section>
    </body>
</html>
