<!DOCTYPE html>
<html>
    <head>
        <title>Forgot Password</title>
    </head>
    <body>
        <h2>Forgot Password</h2>
        <form action="/ISP392_Grubhub/forgot" method="POST">
            <h3 style = "color: red">${requestScope.alert}</h3>
            <label for="email">Enter your email:</label>
            <input type="email" id="email" name="email" required>

            <div class="captcha">
                <button type="button" onclick="generateCaptcha()">Refresh Captcha</button>
                <span id="captcha-text"></span>
            </div>
            <label for="captcha-input">Enter the captcha:</label>
            <input type="text" id="captcha-input" name="captcha" required>
            <br>
            <button type="submit">Reset Password</button>
<!--            <input type="hidden" class="hide" name="token" id="token" value="">-->
        </form>
        <script>
            function generateCaptcha() {
                var captcha = Math.random().toString(36).substring(2, 8);
                document.getElementById("captcha-text").textContent = captcha;
                document.cookie = "captcha=" + captcha + "; path=/";
            }
            generateCaptcha();
        </script>
    </body>
</html>
