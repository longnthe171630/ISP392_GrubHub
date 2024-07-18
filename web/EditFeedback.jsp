<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href='https://unpkg.com/boxicons@2.1.1/css/boxicons.min.css' rel='stylesheet'>
        <link rel="stylesheet" href="css/style_3.css">
        <title>Edit Feedback</title>
    </head>
    <body>
        <div class="wrapper">
            <h3>Edit Feedback</h3>
            <c:if test="${not empty feedback}">
                <form action="saveupdatedfeedback" method="post">
                    <c:if test="${not empty Alert}">
                        <p style="color:red;">${Alert}</p>
                    </c:if>
                    <input type="hidden" name="orderId" value="${feedback.orderID}">
                    <input type="hidden" name="customerId" value="${customerId}">
                    <input type="hidden" name="productId" value="${productId}">

                    <div class="rating">
                        <input type="number" name="rate" hidden>
                        <i class='bx bx-star star' style="--i: 0;"></i>
                        <i class='bx bx-star star' style="--i: 1;"></i>
                        <i class='bx bx-star star' style="--i: 2;"></i>
                        <i class='bx bx-star star' style="--i: 3;"></i>
                        <i class='bx bx-star star' style="--i: 4;"></i>
                    </div>

                    <textarea name="description" cols="30" rows="5" placeholder="Your opinion...">${feedback.description}</textarea>

                    <div class="btn-group">
                        <button type="submit" class="btn submit">Update</button>
                        <button type="button" class="btn cancel" onclick="window.location.href = 'historyorder'">Cancel</button>
                    </div>
                </form>
            </c:if>
            <c:if test="${empty feedback}">
                <p>No feedback found for this order and product.</p>
            </c:if>
        </div>

        <script type="text/javascript">
            const allStar = document.querySelectorAll('.rating .star');
            const ratingValue = document.querySelector('.rating input');
            const currentRating = ${feedback.value};

            allStar.forEach((item, idx) => {
                if (idx < currentRating) {
                    item.classList.replace('bx-star', 'bxs-star');
                    item.classList.add('active');
                }
                item.addEventListener('click', function () {
                    ratingValue.value = idx + 1;
                    allStar.forEach((star, starIdx) => {
                        if (starIdx <= idx) {
                            star.classList.replace('bx-star', 'bxs-star');
                            star.classList.add('active');
                        } else {
                            star.classList.replace('bxs-star', 'bx-star');
                            star.classList.remove('active');
                        }
                    });
                });
            });
        </script>
    </body>
</html>
