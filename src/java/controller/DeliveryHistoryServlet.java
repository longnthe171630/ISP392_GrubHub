/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DeliveryDAO;
import dao.NotificationDAO;
import dao.OrderDAO;
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
import model.Delivery;
import model.Notification;
import model.Order;

/**
 *
 * @author Long1
 */
@WebServlet(name = "DeliveryHistoryServlet", urlPatterns = {"/deliveryhistory"})
public class DeliveryHistoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Lấy data bằng session
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("acc");

        //Lấy dữ liệu từ DAO
        NotificationDAO dao1 = new NotificationDAO();
        DeliveryDAO dao = new DeliveryDAO();
       //Lấy info của account login vào
        int id = dao.getDeliveryPersonIdByUsername(account.getUsername());
        List<Notification> notice = dao1.getListNotification(id);
        //Biến search
        String search = request.getParameter("search");
        // Lấy giá trị của tham số sort từ request
        String sortParam = request.getParameter("sort");
        // Khởi tạo một biến Boolean để lưu trữ giá trị sort
        Boolean sort = null;
        // Kiểm tra nếu sortParam không null, thực hiện chuyển đổi sang Boolean
        if (sortParam != null) {
            sort = Boolean.parseBoolean(sortParam);
        }
        List<Delivery> list;
        if (search == null || search.trim().isEmpty()) {
            list = dao.getDeliveryHistory(id, sort);
        } else {
            list = dao.searchDeliveryHistory(id, search);
            // Kiểm tra nếu không có kết quả tìm kiếm
            if (list.isEmpty()) {
                request.setAttribute("err", "No matches found, please try again!");
            }
        }

        setupPagination(request, list);
        
        //Hiển thị lỗi
        String err = (String) session.getAttribute("err");
        // Xóa thông báo lỗi sau khi lấy ra để nó chỉ hiển thị một lần
        session.removeAttribute("err");
        // Thiết lập thuộc tính cho request để chuyển tiếp đến JSP
        if (err != null) {
            request.setAttribute("err", err);
        }

        //Thiết lập yêu cầu để đẩy data sang jsp
        request.setAttribute("account", account);
        request.setAttribute("notice", notice);
        request.getRequestDispatcher("deliveryhistory.jsp").forward(request, response);
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
        int order_id = Integer.parseInt(request.getParameter("order_id"));

        DeliveryDAO deliveryDAO = new DeliveryDAO();
        OrderDAO orderDAO = new OrderDAO();
        NotificationDAO notice = new NotificationDAO();
        Delivery delivery1 = deliveryDAO.getDeliveryByOrderId(order_id);
        Order order1 = orderDAO.getRestaurant_Customer_ByOrderId(order_id);
        Order order2 = orderDAO.getOrderById(order_id);
        Notification des = notice.getNoticeByOrderId(order_id);
        
        request.setAttribute("des", des);
        request.setAttribute("delivery1", delivery1);
        request.setAttribute("order1", order1);
        request.setAttribute("order2", order2);
        request.getRequestDispatcher("deliverystatus.jsp").forward(request, response);
    }

    private void setupPagination(HttpServletRequest request, List<Delivery> list) {
        int itemsPerPage = 5;
        int totalItems = list.size();
        int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);
        String pageParam = request.getParameter("page");
        int currentPage = pageParam != null ? Integer.parseInt(pageParam) : 1;
        int startIndex = (currentPage - 1) * itemsPerPage;
        int endIndex = Math.min(startIndex + itemsPerPage, totalItems);
        List<Delivery> paginatedList = list.subList(startIndex, endIndex);

        request.setAttribute("list", paginatedList);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", currentPage);
    }
}