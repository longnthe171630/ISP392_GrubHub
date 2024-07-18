package controller;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */


import dao.OrderDAO;
import dao.OrderDetailsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Order;
import model.OrderDetails;

/**
 *
 * @author manh0
 */
public class OrderServlet extends HttpServlet {

    private OrderDAO orderDAO = new OrderDAO();
    private OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderIdStr = request.getParameter("orderId");

        // Ki?m tra xem orderIdStr c� ph?i l� null ho?c r?ng kh�ng
        if (orderIdStr == null || orderIdStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Order ID");
            return;
        }

        try {
            int orderId = Integer.parseInt(orderIdStr);

            // Gi? s? b?n ?� c� m� ?? l?y Order v� OrderDetails t? c? s? d? li?u
            Order order = orderDAO.getOrderById_VuPL(orderId);
            List<OrderDetails> orderDetailsList = orderDetailsDAO.getOrderDetailsByOrderId(orderId);

            // Ki?m tra xem order v� orderDetailsList c� ph?i l� null kh�ng
            if (order == null || orderDetailsList == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Order not found");
                return;
            }

            // Thi?t l?p c�c thu?c t�nh cho JSP
            request.setAttribute("order", order);
            request.setAttribute("orderDetailsList", orderDetailsList);

            // Chuy?n h??ng t?i JSP
            request.getRequestDispatcher("OrderStatus.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            // X? l� l?i n?u orderId kh�ng ph?i l� s? nguy�n h?p l?
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Order ID format");
        }
    }

}
