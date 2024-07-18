/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import utils.Validate;

/**
 *
 * @author Long1
 */
@WebServlet(name = "ChangePasswordServlet", urlPatterns = {"/changepassword"})
public class ChangePasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String currentpass = request.getParameter("currentPassword");
        String newpass = request.getParameter("newPassword");
        String confirmpass = request.getParameter("confirmPassword");

        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("acc");

        AccountDAO dao = new AccountDAO();

        String alert = "";

        if (!account.getPassword().equals(currentpass)) {
            alert = "Current password is incorrect!";

        } else {
            if (newpass.equals(currentpass)) {
                alert = "New password must not match the current password";

            } else if (!newpass.equals(confirmpass)) {
                alert = "Confirm password must match the new password";

            } else if (!Validate.isValidPassword(newpass)) {
                alert = "Password must contain at least 8 characters, including lowercase letters, uppercase letters, numbers, and special characters";

            } else {
                dao.changePassword(account.getUsername(), newpass);
                alert = "Change password successful!";
                request.setAttribute("passwordChanged", true);  // Đặt thuộc tính nếu thành công
            }
        }

        request.setAttribute("alert", alert);
        request.getRequestDispatcher("Changepassword.jsp").forward(request, response);
    }
}
