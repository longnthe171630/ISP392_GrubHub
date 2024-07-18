<!DOCTYPE html>
<html>
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <!doctype html>
    <head>
        <title>Reset Password</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap" rel="stylesheet">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

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
                                <div class="d-flex">
                                    <div class="w-100">
                                        <h3 class="mb-4">Reset Password</h3>
                                    </div>
                                    <div class="w-100">
                                        </p>
                                    </div>
                                </div>
                                <form action="/ISP392_Grubhub/resetpassword" method="POST" class="signin-form">
                                    <p class="text-danger">
                                        ${requestScope.alert}
                                    </p>
                                    <div class="form-group mb-3">
                                        <label class="label" for="name">New Password</label>
                                        <input type="password" name="newPassword" class="form-control" placeholder="New Password" required>
                                    </div>

                                    <div class="form-group mb-3">
                                        <label class="label" for="password">Confirm New Password</label>
                                        <input id="pass" type="password" name="confirmPassword" class="form-control" placeholder="Confirm New Password" required >
                                    </div>

                                    <input type="hidden" name="email" value="${requestScope.email}">
                                    <input type="hidden" name="token" value="${requestScope.token}">
                                    <div class="form-group">
                                        <button type="submit" class="form-control btn btn-primary rounded submit px-3">Reset Password</button>
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

</html>
