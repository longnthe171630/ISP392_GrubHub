package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cart;

/**
 * Servlet implementation class ApplyVoucherServlet
 */
public class ApplyVoucherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        String voucherCode = request.getParameter("voucherCode");

        if (cart != null && voucherCode != null && !voucherCode.isEmpty()) {
            // Example voucher validation logic
            if (voucherCode.equals("DISCOUNT10")) {
                cart.setDiscount(0.1); // 10% discount
                session.setAttribute("voucherMessage", "Voucher applied successfully!");
            } else {
                session.setAttribute("voucherMessage", "Invalid voucher code.");
            }
        } else {
            session.setAttribute("voucherMessage", "Please enter a voucher code.");
        }

        response.sendRedirect("Cart.jsp");
    }
}
