<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href='https://unpkg.com/boxicons@2.1.1/css/boxicons.min.css' rel='stylesheet'>
        <link rel="stylesheet" href="css/style_3.css">
        <title>Form Reviews</title>
    </head>
    <body>
        <div class="wrapper">
            <h3>Tell us your experience</h3>
            <form action="addfeedback" method="post">
                <input type="hidden" name="orderId" value="${param.orderId}">
                <input type="hidden" name="productId" value="${param.productId}">
                <input type="hidden" name="customerId" value="${param.customerId}">
                <div class="rating">
                    <input type="number" name="number" hidden>
                    <i class='bx bx-star star' style="--i: 0;"></i>
                    <i class='bx bx-star star' style="--i: 1;"></i>
                    <i class='bx bx-star star' style="--i: 2;"></i>
                    <i class='bx bx-star star' style="--i: 3;"></i>
                    <i class='bx bx-star star' style="--i: 4;"></i>
                </div>
                <textarea name="description" cols="30" rows="5" placeholder="Your opinion..."></textarea>
                <div class="btn-group">
                    <button type="submit" class="btn submit">Submit</button>
                    <button type="button" class="btn cancel" onclick="window.location.href ='historyorder'" >Cancel</button>
                </div>
            </form>
        </div>
        <script type="text/javascript">
            const allStar = document.querySelectorAll('.rating .star');
            const ratingValue = document.querySelector('.rating input');

            allStar.forEach((item, idx) => {
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
