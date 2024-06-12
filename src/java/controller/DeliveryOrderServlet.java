/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AddressDAO;
import dao.CustomerDAO;
import dao.DeliveryDAO;
import dao.OrderDAO;
import dao.OrderDetailsDAO;
import dao.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Address;
import model.Order;
import model.OrderDetails;

/**
 *
 * @author Long1
 */
@WebServlet(name = "OrderServlet", urlPatterns = {"/order"})
public class DeliveryOrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        OrderDAO dao = new OrderDAO();
        List<Order> order = dao.getAddressRestaurant_CustomerWithId();

        request.setAttribute("order", order);
        request.getRequestDispatcher("deliveryorders.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int order_id = Integer.parseInt(request.getParameter("id"));
        
        OrderDetailsDAO dao1 = new OrderDetailsDAO();
        OrderDetails orderdetails = dao1.getOrderDetailsByOrder(order_id);

        ProductDAO dao2 = new ProductDAO();
        String product = dao2.getNameProductById(orderdetails.getProduct_id());

        DeliveryDAO dao3 = new DeliveryDAO();
        float ship_price = dao3.getShipPricByOrderId(order_id);

        OrderDAO orderDAO = new OrderDAO();
        List<Order> order = orderDAO.getAddressRestaurant_CustomerWithId();
        
        Order order1 = orderDAO.getOrderById(order_id);
        Address fromAddress = order1.getFromAddress();
        Address toAddress = order1.getToAddress();

        // Tạo URL của Google Maps Directions API với thông tin địa chỉ xuất phát và đích đến
        String directionsURL = "https://www.google.com/maps/embed/v1/directions?key=AIzaSyAOVYRIgupAurZup5y1PRh8Ismb1A3lLao&origin=" + fromAddress + "&destination=" + toAddress;

        // Đặt directionsURL vào thuộc tính request để chuyển đến trang JSP
        request.setAttribute("directionsURL", directionsURL);
        request.setAttribute("order", order);
        request.setAttribute("order1", order1);
        request.setAttribute("ship_price", ship_price);
        request.setAttribute("productname", product);
        request.setAttribute("orderdetails", orderdetails);
        request.getRequestDispatcher("deliveryorderdetails.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
