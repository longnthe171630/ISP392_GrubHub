package controller;

import dao.AccountDAO;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;
import javax.mail.MessagingException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.System.Logger.Level;
import model.Account;
import utils.Token;
import utils.Mail;

public class ForgotPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String token = request.getParameter("token");

        if (token == null) {
            request.getRequestDispatcher("client/forgotpassword.jsp").forward(request, response);
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            String userCaptcha = request.getParameter("captcha");

            // Lấy captcha từ cookie
            String cookieCaptcha = null;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("captcha".equals(cookie.getName())) {
                        cookieCaptcha = cookie.getValue();
                        break;
                    }
                }
            }
            AccountDAO accountDAO = new AccountDAO();
            Account account = accountDAO.getAccountByEmail(email);
            String alert = "";
            String url1 = "";

            //Check captcha
            if (cookieCaptcha != null && cookieCaptcha.equals(userCaptcha)) {
                // Check if the username or email already exists
                if (account != null) {
                    String token = new Token().generateRandomToken(18);
                    String url = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/resetpassword?email=" + email + "&token=" + token;
                    accountDAO.insertToken(account.getId(), token);
                    new Mail().sendEmail(email, "Reset Password", "Click here to reset password: " + url);
                    url1 = "/client/login.jsp";
                    alert = "An email was sent. Please check your email!";

                } else {
                    url1 = "/client/forgotpassword.jsp";
                    alert = "Email is invalid";
                }
            } else {
                url1 = "/client/forgotpassword.jsp";
                alert = "Captcha don't matches!";
            }

            request.setAttribute("alert", alert);

            request.getRequestDispatcher(url1).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
