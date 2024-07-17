/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAO;
import dao.CustomerDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import dao.RestaurantDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import model.*;

/**
 *
 * @author phaml
 */
@WebServlet(name = "RestaurantDashboardServlet", urlPatterns = {"/restaurantdashboard"})
public class RestaurantDashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Use false to prevent creating a new session

        String username = (String) session.getAttribute("username");
        RestaurantDAO rd = new RestaurantDAO();
//        int resID = 1;
        int countOrder = 0, countProduct = 0;
        String status = "Waiting restaurant";

        // Tính toán số lượng đơn hàng mới
        OrderDAO cd = new OrderDAO();
        AccountDAO ad = new AccountDAO();
        CustomerDAO c = new CustomerDAO();
        int accID = ad.getAccountID(username);
        Account account = ad.getAccountByID(accID);
        int resID = rd.getRestaurantIDbyAccID(accID);
        Map<Customer, Order> mapCusOrder = new HashMap<>();
        Map<Customer, Account> mapAccCus = new HashMap<>();
        List<Order> lo = cd.getOrder();
        List<Order> lod = new ArrayList<>();
        for (Order order : lo) {
            if (order.getRestaurant_id() == resID && order.getStatus().equals(status)) {
                countOrder++;
                Customer cus = c.getCustomerByID_VuPL(order.getCustomer_id());

                Account acc = ad.getAccountByID(cus.getAccountID());

                mapCusOrder.put(cus, order);
                mapAccCus.put(cus, acc);

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
        request.setAttribute("MapCusOrder", mapCusOrder);
        request.setAttribute("MapAccCus", mapAccCus);
        request.setAttribute("lod", lod);
        // Chuyển hướng đến trang JSP để hiển thị
        request.getRequestDispatcher("RestaurantDashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
