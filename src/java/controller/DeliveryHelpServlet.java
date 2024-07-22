/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import utils.Mail;

/**
 *
 * @author Long1
 */
@WebServlet(name = "DeliveryHelpServlet", urlPatterns = {"/deliveryhelp"})
public class DeliveryHelpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Lấy data bằng session
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("acc");

        String userEmail = account.getEmail();

        request.setAttribute("userEmail", userEmail);
        request.getRequestDispatcher("deliveryhelp.jsp").forward(request, response);
    }

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        // Lấy thông tin từ form
        String userName = request.getParameter("name");
        String userEmail = request.getParameter("email");
        String reportContent = request.getParameter("message");

        // Cố gắng gửi email và chuyển hướng dựa trên kết quả
        try {
            Mail.sendEmailOnBehalf(userName, userEmail, reportContent);
            // Chuyển hướng đến trang thành công nếu email được gửi thành công
            response.sendRedirect("confirmation.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            // Chuyển hướng đến trang lỗi nếu có lỗi xảy ra trong quá trình gửi email
            response.sendRedirect("error.jsp");
        }
    }
}
