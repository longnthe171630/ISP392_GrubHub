/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAO;
import dao.AdminDAO;
import dao.CustomerDAO;
import dao.DeliveryDAO;
import dao.FeedbackDAO;
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
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.http.HttpSession;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import model.Account;
import model.Customer;
import model.Delivery;
import model.Feedback;
import model.Order;
import model.Product;
import model.Restaurant;

/**
 *
 * @author Dell
 */
@WebServlet(name = "AdminServlet", urlPatterns = {"/admin"})
public class AdminServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "0"; // Hoặc bất kỳ giá trị mặc định nào bạn muốn sử dụng
        }
        AccountDAO dao3 = new AccountDAO();
        RestaurantDAO dao4 = new RestaurantDAO();
        CustomerDAO dao9 = new CustomerDAO();
        switch (action) {
            case "home":
                AdminDAO dao = new AdminDAO();
                int orderCount = dao.countOrder();
                int accountCount = dao.countAccount();
                request.setAttribute("NumofUser", accountCount);
                request.setAttribute("orderCount", orderCount);

                OrderDAO dao2 = new OrderDAO();
                List<Order> list = dao2.getListOrder2();
                request.setAttribute("listOrder", list);
                request.getRequestDispatcher("Admin.jsp").forward(request, response);
                break;
            case "cus":

                List<Account> listAccount = dao3.getListAccount();
                request.setAttribute("listAccount", listAccount);
                request.getRequestDispatcher("AdminCus.jsp").forward(request, response);
                break;
            case "res":

                List<Restaurant> listRes = dao4.getListRestaurant();
                request.setAttribute("listRes", listRes);
                request.getRequestDispatcher("AdminRes.jsp").forward(request, response);
                break;
            case "deli":
                DeliveryDAO dao6 = new DeliveryDAO();
                List<Delivery> listDeli = dao6.getListDelivery();
                request.setAttribute("listDeli", listDeli);
                request.getRequestDispatcher("AdminDeli.jsp").forward(request, response);

                break;

            case "feed":

                FeedbackDAO dao5 = new FeedbackDAO();
                List<Feedback> listFeed = dao5.getListFeedback();
                List<Restaurant> listRes1 = dao4.getListRestaurant();
                List<Customer> listCus1 = dao9.getListCustomer();
                HashMap<Feedback, Customer> map1 = new HashMap<>();
                HashMap<Feedback, Restaurant> map2 = new HashMap<>();

                for (Feedback f : listFeed) {
                    for (Customer c : listCus1) {
                        if (c.getId() == f.getCustomer_id()) {
                            map1.put(f, c);
                        }

                    }
                    for (Restaurant r : listRes1) {
                        if (r.getId() == f.getRestaurant_id()) {
                            map2.put(f, r);
                        }
                    }
                }
                List<Map.Entry<Feedback, Customer>> sortedEntries = new ArrayList<>(map1.entrySet());
                sortedEntries.sort(Comparator.comparing(entry -> entry.getKey().getId()));

                request.setAttribute("map1", map1);
                request.setAttribute("sortedEntries", sortedEntries);
                request.setAttribute("listRes1", listRes1);
                request.getRequestDispatcher("AdminFeed.jsp").forward(request, response);

                break;
            case "ban":
                AccountDAO dao7 = new AccountDAO();
                try {
                    int accountId = Integer.parseInt(request.getParameter("accountId"));
                    dao7.banAccount(accountId);
                } catch (Exception e) {
                }
                List<Account> listBan = dao7.getListBanedAccount();
                request.setAttribute("listBan", listBan);
                request.getRequestDispatcher("AdminBan.jsp").forward(request, response);
                break;
            case "unban":
                AccountDAO dao8 = new AccountDAO();
                try {
                    int accountId = Integer.parseInt(request.getParameter("accountId"));
                    dao8.unbanAccount(accountId);
                } catch (Exception e) {
                }
                List<Account> listBan2 = dao8.getListBanedAccount();
                request.setAttribute("listBan", listBan2);
                request.getRequestDispatcher("AdminBan.jsp").forward(request, response);
                break;            
            default:
                // Xử lý giá trị mặc định hoặc trả về lỗi nếu action không hợp lệ
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action parameter.");
                break;

        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error ad
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int resId = Integer.parseInt(request.getParameter("id"));
        ProductDAO dao = new ProductDAO();
        List<Product> list = dao.getProductByResID(resId);
        request.setAttribute("productList", list);
        request.getRequestDispatcher("ProductList.jsp").forward(request, response);

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
