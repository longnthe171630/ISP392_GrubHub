/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CustomerDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Customer;
import model.Order;
import model.Product;

/**
 *
 * @author phaml
 */
@WebServlet(name = "HistoryRestaurantServlet", urlPatterns = {"/historyrestaurant"})
public class HistoryRestaurantServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int resID = 1;
        int countOrder = 0, countProduct = 0;
        String status1 = "Đã giao";
        String status2 = "Đang chờ";
        String status3 = "Từ chối";

        // Tính toán số lượng đơn hàng mới
        OrderDAO cd = new OrderDAO();
        CustomerDAO c = new CustomerDAO();
        Map<Customer, Order> map = new HashMap<>();
        List<Order> lo = cd.getOrder();
        List<Order> lod = new ArrayList<>();
        for (Order order : lo) {
            if (order.getRestaurant_id() == resID) {
                if (order.getStatus().equals(status1)
                        || order.getStatus().equals(status2)
                        || order.getStatus().equals(status3)) {
                    countOrder++;
                    Customer cus = c.getCustomerByID_VuPL(order.getCustomer_id());
                    map.put(cus, order);
                }

            }
        }

        // Tính toán số lượng sản phẩm
        ProductDAO pd = new ProductDAO();
        List<Product> lp = pd.getProductsRestaurant();
        for (Product product : lp) {
            if (product.getRestaurantId() == resID) {
                countProduct++;
            }
        }

        // Đặt giá trị vào thuộc tính của request để truyền cho JSP
        request.setAttribute("countorder", countOrder);
        request.setAttribute("countproduct", countProduct);
        request.setAttribute("MapCusOrder", map);
        request.setAttribute("lod", lod);
        // Chuyển hướng đến trang JSP để hiển thị
        request.getRequestDispatcher("historyrestaurant.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
