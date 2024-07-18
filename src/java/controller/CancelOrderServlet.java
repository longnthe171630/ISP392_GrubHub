package controller;

import dao.OrderDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Order;

@WebServlet(name = "CancelOrderServlet", urlPatterns = {"/cancelorder"})
public class CancelOrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String orderIdStr = request.getParameter("orderId");

        if (orderIdStr != null && !orderIdStr.isEmpty()) {
            try {
                int orderId = Integer.parseInt(orderIdStr);
                request.setAttribute("orderId", orderId);
                request.getRequestDispatcher("CancelOrder.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                // Handle invalid orderId format
                response.sendRedirect("home");
            }
        } else {
            // Handle case where orderId parameter is missing or empty
            response.sendRedirect("OrderNotFound.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String orderIdStr = request.getParameter("orderId");
        String reason = request.getParameter("reason");

        if (orderIdStr != null && !orderIdStr.isEmpty()) {
            try {
                int orderId = Integer.parseInt(orderIdStr);

                // Perform cancellation logic
                OrderDAO orderDAO = new OrderDAO();
                Order order = orderDAO.getOrderById_VuPL(orderId); // Adjust this to your actual method

                if (order != null) {
                    // Check if order status is "Waiting restaurant"
                    if ("Waiting restaurant".equals(order.getStatus())) {
                        // Update order status to Cancelled and set cancellation reason
                        orderDAO.cancelOrder(orderId);

                        // Set confirmation message in session attribute
                        HttpSession session = request.getSession();
                        session.setAttribute("message", "Order successfully cancelled.");
                        session.setAttribute("orderCancelled", true);

                        // Redirect to Order Status page with orderId
                        response.sendRedirect("order?orderId=" + orderId);
                    } else {
                        // Set error message in session attribute
                        HttpSession session = request.getSession();
                        session.setAttribute("message", "Order cannot be cancelled as it is not in the 'Waiting restaurant' status.");
                        session.setAttribute("orderCancelled", false);

                        // Redirect to Order Status page with orderId
                        response.sendRedirect("order?orderId=" + orderId);
                    }
                } else {
                    // Handle case where order is not found
                    response.sendRedirect("home");
                }
            } catch (NumberFormatException e) {
                // Handle invalid orderId format
                response.sendRedirect("home");
            }
        } else {
            // Handle case where orderId parameter is missing or empty
            response.sendRedirect("OrderNotFound.jsp");
        }
    }
}
