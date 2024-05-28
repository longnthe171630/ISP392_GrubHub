<!DOCTYPE html>
<html>
    <head>
        <title>Reset Password</title>
    </head>
    <body>
        <h2>Reset Password</h2>
        <form action="/ISP392_Grubhub/resetpassword" method="POST">
            <h3 style = "color: red">${requestScope.alert}</h3>
            Enter new password:
            <input type="password" name="newPassword" required><br>
            Enter confirm password:
            <input type="password" name="confirmPassword" required><br>

            <input type="hidden" name="email" value="${requestScope.email}">
            <input type="hidden" name="token" value="${requestScope.token}">
            <button type="submit">Reset Password</button>
        </form>
    </body>
</html>
