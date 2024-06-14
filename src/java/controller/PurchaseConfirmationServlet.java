package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.*;
import model.*;

public class PurchaseConfirmationServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        // Lấy thông tin đơn hàng và khách hàng từ session
        Cart cart = (Cart) session.getAttribute("cart");
        Customer customer = (Customer) session.getAttribute("acc");

        // Kiểm tra xem đơn hàng và thông tin khách hàng có tồn tại không
        if (cart == null || customer == null) {
            // Nếu không, chuyển hướng người dùng đến trang lỗi
            response.sendRedirect("error.jsp");
            return;
        }

        // Gửi email xác nhận cho khách hàng
        sendConfirmationEmail(customer, cart);

        // Xóa đơn hàng khỏi session sau khi gửi email thành công
        session.removeAttribute("cart");
        session.removeAttribute("acc");

        // Chuyển hướng người dùng đến trang xác nhận đơn hàng thành công
        response.sendRedirect("confirmation.jsp");
    }

    // Hàm để gửi email xác nhận đơn hàng
    private void sendConfirmationEmail(Customer customer, Cart cart) {
        Email email = new Email();
        String to = customer.getEmail();
        String subject = "Xác nhận đơn hàng từ cửa hàng của chúng tôi";
        String message = "Xin chào " + customer.getName() + ",\n\n"
                + "Cảm ơn bạn đã mua hàng từ cửa hàng của chúng tôi. Dưới đây là chi tiết đơn hàng của bạn:\n\n"
                + cart.toString() + "\n\n"
                + "Chúng tôi sẽ liên hệ với bạn sớm nhất có thể để xác nhận đơn hàng. Xin cảm ơn đã mua hàng từ chúng tôi!\n\n"
                + "Trân trọng,\n"
                + "Đội ngũ cửa hàng của chúng tôi";
        
        // Gửi email
        email.sendEmail(subject, message, to);
    }
}
