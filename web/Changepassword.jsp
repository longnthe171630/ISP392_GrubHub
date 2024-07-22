<!DOCTYPE html>
<html>
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ include file="deliverychange.jsp" %>
    <!doctype html>
    <head>
        <title>Reset Password</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap" rel="stylesheet">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <link rel="stylesheet" href="css/style_1.css">
    </head>
    <body>
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
                                <button class="back-button" onclick="goBack()">
                                    <i class="fas fa-arrow-left"></i>
                                </button>
                                <div class="d-flex">
                                    <div class="w-100">
                                        <h3 class="mb-4"><fmt:message key="changepass" bundle="${lang}"/></h3>
                                    </div>
                                    <div class="w-100">
                                        </p>
                                    </div>
                                </div>
                                <form action="changepassword" method="POST" class="signin-form">
                                    <p class="text-danger">
                                        ${requestScope.alert}
                                    </p>
                                    <div class="form-group mb-3">
                                        <label class="label" for="name"><fmt:message key="currentpass" bundle="${lang}"/></label>
                                        <input type="password" name="currentPassword" class="form-control" placeholder="Current Password" required>
                                    </div>

                                    <div class="form-group mb-3">
                                        <label class="label" for="password"><fmt:message key="newpass" bundle="${lang}"/></label>
                                        <input id="pass" type="password" name="newPassword" class="form-control" placeholder="New Password" required >
                                    </div>

                                    <div class="form-group mb-3">
                                        <label class="label" for="password"><fmt:message key="confirmpass" bundle="${lang}"/></label>
                                        <input id="pass" type="password" name="confirmPassword" class="form-control" placeholder="Confirm New Password" required >
                                    </div>

                                    <div class="form-group">
                                        <button type="submit" class="form-control btn btn-primary rounded submit px-3"><fmt:message key="change" bundle="${lang}"/></button>
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
        <script src="js/script.js"></script>
    </body>
    <script>
                                    function goBack() {
                                        window.history.back();
                                    }

                                    window.onload = function () {
        <% if (request.getAttribute("passwordChanged") != null) { %>
                                        setTimeout(function () {
                                            window.location.href = 'home';  // Thay 'targetPage.jsp' bằng URL bạn muốn chuyển hướng tới
                                        }, 3000);  // Chuyển hướng sau 3 giây
        <% } %>
                                    }
    </script>
</html>
