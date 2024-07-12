package controller;

import dao.AccountDAO;
import dao.CustomerDAO;
import dao.FeedbackDAO;
import dao.OrderDAO;
import dao.RestaurantDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Order;
import model.Restaurant;

public class CustomerHistoryOrderServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        
        AccountDAO ad = new AccountDAO();
        CustomerDAO cd = new CustomerDAO();
        OrderDAO od = new OrderDAO();
        RestaurantDAO rd = new RestaurantDAO();
        FeedbackDAO fd = new FeedbackDAO();

        int accID = ad.getAccountID(username);
        int cusID = cd.getCusIdByAccId(accID);
        List<Restaurant> listR = fd.getAllRestaurants();
        List<Order> listO = od.getAllOrderOf1Customer(cusID);

        Map<Integer, String> restaurantNames = new HashMap<>();
        for (Restaurant restaurant : listR) {
            restaurantNames.put(restaurant.getId(), restaurant.getName());
        }
        request.setAttribute("customerId", cusID);
        request.setAttribute("orderList", listO);
        request.setAttribute("restaurantNames", restaurantNames);

        // Debug output to verify data

        request.getRequestDispatcher("OrderHistory.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
