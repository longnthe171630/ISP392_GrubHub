/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

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

        // Kiểm tra xem orderIdStr có phải là null hoặc rỗng không
        if (orderIdStr == null || orderIdStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Order ID");
            return;
        }

        try {
            int orderId = Integer.parseInt(orderIdStr);

            // Giả sử bạn đã có mã để lấy Order và OrderDetails từ cơ sở dữ liệu
            Order order = orderDAO.getOrderById_VuPL(orderId);
            List<OrderDetails> orderDetailsList = orderDetailsDAO.getOrderDetailsByOrderId(orderId);

            // Kiểm tra xem order và orderDetailsList có phải là null không
            if (order == null || orderDetailsList == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Order not found");
                return;
            }

            // Thiết lập các thuộc tính cho JSP
            request.setAttribute("order", order);
            request.setAttribute("orderDetailsList", orderDetailsList);

            // Chuyển hướng tới JSP
            request.getRequestDispatcher("OrderStatus.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            // Xử lý lỗi nếu orderId không phải là số nguyên hợp lệ
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Order ID format");
        }
    }

}
