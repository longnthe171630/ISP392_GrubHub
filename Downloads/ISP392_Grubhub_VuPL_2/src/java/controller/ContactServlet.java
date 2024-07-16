package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Email;

public class ContactServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String name = request.getParameter("name");
        String phone = request.getParameter("number");
        String email = request.getParameter("email");
        String subject = request.getParameter("subject");
        String msg = request.getParameter("message");

        Email handleEmail = new Email();
        String message;
        String check;

        // Email admin
        String adminEmail = "manhndhe173383@fpt.edu.vn"; // Thay thế bằng email của admin
        String adminSubject = "New Contact Request from " + name;
        String adminMessage = "Name: " + name + "<br/>" +
                              "Phone: " + phone + "<br/>" +
                              "Email: " + email + "<br/>" +
                              "Subject: " + subject + "<br/>" +
                              "Message: " + msg;

        // Email customer
        String customerSubject = "Cảm ơn bạn đã liên hệ với chúng tôi";
        String customerMessage = "Xin chào " + name + ",<br/><br/>" +
                                 "Cảm ơn bạn đã liên hệ với chúng tôi. Chúng tôi sẽ kết nối với bạn trong thời gian sớm nhất.<br/><br/>" +
                                 "Trân trọng,<br/>Nhóm hỗ trợ";

        if (handleEmail.isValidEmail(email)) {
            message = "Cảm ơn bạn đã liên hệ với chúng tôi, chúng tôi sẽ kết nối với bạn trong thời gian sớm nhất";
            check = "success";
            handleEmail.sendEmail(adminSubject, adminMessage, adminEmail); // Gửi email tới admin
            handleEmail.sendEmail(customerSubject, customerMessage, email); // Gửi email tới customer
        } else {
            message = "Có vẻ như một số thông tin cung cấp của bạn không hợp lệ, vui lòng cung cấp lại thông tin";
            check = "fail";
        }

        request.setAttribute("message", message);
        request.setAttribute("check", check);
        request.setAttribute("fullName", name);
        request.setAttribute("email", email);
        request.setAttribute("phonenumber", phone);

        request.getRequestDispatcher("Contactsuccess.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Contact Servlet for handling contact form submissions";
    }
}
