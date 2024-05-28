package controller;

import dao.AccountDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;
import utils.Validate;

@WebServlet(name = "ResetPassword", urlPatterns = {"/resetpassword"})
public class ResetPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String token = request.getParameter("token");

        // Set email và token vào request attribute để truyền sang doPost
        request.setAttribute("email", email);
        request.setAttribute("token", token);
        
        request.getRequestDispatcher("resetpassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String token = request.getParameter("token");

        String password = request.getParameter("newPassword");
        String retypePassword = request.getParameter("confirmPassword");

        Account account = new AccountDAO().getAccountByEmail(email);
        AccountDAO dao = new AccountDAO();
        
        String alert = "";
        String url = "";
        // Kiểm tra định dạng mật khẩu
        if (Validate.isValidPassword(password)) {
        // Kiểm tra mật khẩu và mật khẩu nhập lại có trùng khớp
        if (password.equals(retypePassword)) {
            if (account == null) {
                alert = "Email not found";
            } else {
                // Kiểm tra token
                if (dao.checkToken(email, token) != null) {
                    // Thay đổi mật khẩu trong cơ sở dữ liệu
                    new AccountDAO().changePassword(account.getUsername(), password);
                    // Đặt lại token
                    new AccountDAO().ResetToken(account.getId());
                    alert = "Reset password success";
                    url = "Login.jsp";
                } else {
                    alert = "Invalid token!";
                    url = "resetpassword.jsp";
                }
            }
        } else {
            alert = "Passwords don't match!";
            url = "resetpassword.jsp";
        }
    } else {
        alert = "Password must contain at least 8 characters, including lowercase letters, uppercase letters, numbers, and special characters";
        url = "resetpassword.jsp";
    }
        request.setAttribute("alert", alert);
        request.setAttribute("token", token);
        request.setAttribute("email", email);

        request.getRequestDispatcher(url).forward(request, response);
    }
}
