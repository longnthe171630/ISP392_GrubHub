package controller;

import dao.OrderDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Order;

@WebServlet(name = "CancelOrderServlet", urlPatterns = {"/cancelorder"})
public class CancelOrderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        String reason = request.getParameter("reason");

        // Perform cancellation logic
        OrderDAO orderDAO = new OrderDAO();
        Order order = orderDAO.getOrderById_VuPL(orderId); // Adjust this to your actual method

        if (order != null) {
            // Update order status to Cancelled and set cancellation reason
            orderDAO.cancelOrder(orderId);

            // Set confirmation message in request attribute
            request.setAttribute("message", "Order successfully cancelled.");

            // Redirect to Order Status page with orderId
            response.sendRedirect("OrderStatus.jsp?orderId=" + orderId);
        } else {
            // Handle case where order is not found
            response.sendRedirect("OrderNotFound.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle GET requests if necessary (optional)
    }
}
