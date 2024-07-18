/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CustomerDAO;
import dao.DeliveryDAO;
import dao.NotificationDAO;
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
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Account;
import model.Address;
import model.Notification;
import model.Order;
import model.OrderDetails;
import model.Product;

/**
 *
 * @author Long1
 */
@WebServlet(name = "DeliveryOrderServlet", urlPatterns = {"/deliveryorder"})
public class DeliveryOrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Lấy data bằng session
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("acc");
        //Lấy dữ liệu từ DAO
        NotificationDAO dao1 = new NotificationDAO();
        OrderDAO dao = new OrderDAO();
        DeliveryDAO dao2 = new DeliveryDAO();
       //Lấy info của account login vào
        int id = dao2.getDeliveryPersonIdByUsername(account.getUsername());
        List<Notification> notice = dao1.getListNotification(id);
        //Tìm kiếm đơn
        String search = request.getParameter("search");
        // Lấy giá trị của tham số sort từ request
        String sortParam = request.getParameter("sort");
        // Khởi tạo một biến Boolean để lưu trữ giá trị sort
        Boolean sort = null;
        // Kiểm tra nếu sortParam không null, thực hiện chuyển đổi sang Boolean
        if (sortParam != null) {
            sort = Boolean.parseBoolean(sortParam);
        }
        //Hiện list tùy điều kiện
        List<Order> orderList;
        if (search == null || search.trim().isEmpty()) {
            orderList = dao.getAddressRestaurant_CustomerWithId(sort);
        } else {
            orderList = dao.searchAddressRestaurant_CustomerWithId(search.trim());
            // Kiểm tra nếu không có kết quả tìm kiếm
            if (orderList.isEmpty()) {
                //request.setAttribute("err", "No matches found, please try again!");
            }
        }
        setupPagination(request, orderList);
        
        //Hiển thị lỗi
        String err = (String) session.getAttribute("err");
        // Xóa thông báo lỗi sau khi lấy ra để nó chỉ hiển thị một lần
        session.removeAttribute("err");
        // Thiết lập thuộc tính cho request để chuyển tiếp đến JSP
        if (err != null) {
            request.setAttribute("err", err);
        }
        request.setAttribute("notice", notice);
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
        //List<Order> order = orderDAO.getAddressRestaurant_CustomerWithId(sort);

        Order order1 = orderDAO.getOrderById(order_id);
        Address fromAddress = order1.getFromAddress();
        Address toAddress = order1.getToAddress();

        // Tạo URL của Google Maps Directions API với thông tin địa chỉ xuất phát và đích đến
        String directionsURL = "https://www.google.com/maps/embed/v1/directions?key=AIzaSyAOVYRIgupAurZup5y1PRh8Ismb1A3lLao&origin=" + fromAddress + "&destination=" + toAddress;

        request.setAttribute("directionsURL", directionsURL);
        //request.setAttribute("order", order);
        request.setAttribute("order1", order1);
        request.setAttribute("ship_price", ship_price);
        request.setAttribute("productname", product);
        request.setAttribute("orderdetails", orderdetails);
        request.getRequestDispatcher("deliveryorderdetails.jsp").forward(request, response);
    }

    private void setupPagination(HttpServletRequest request, List<Order> orderList) {
        int itemsPerPage = 5;
        int totalItems = orderList.size();
        int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);
        String pageParam = request.getParameter("page");
        int currentPage = pageParam != null ? Integer.parseInt(pageParam) : 1;
        int startIndex = (currentPage - 1) * itemsPerPage;
        int endIndex = Math.min(startIndex + itemsPerPage, totalItems);
        List<Order> paginatedList = orderList.subList(startIndex, endIndex);

        request.setAttribute("order", paginatedList);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", currentPage);
    }
}