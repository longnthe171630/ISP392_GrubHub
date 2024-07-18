package controller;

import dao.FeedbackDAO;
import model.Feedback;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Customer;
import model.Restaurant;

public class CusFeedbackServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FeedbackDAO fd = new FeedbackDAO();
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        int productId = Integer.parseInt(request.getParameter("productId"));
        System.out.println(orderId);
        System.out.println(productId);
        Feedback feedback = fd.getFeedbackByOrderIdAndProductId(orderId,productId);
        System.out.println(feedback);
        List<Customer> customerList = fd.getAllCustomers();
        List<Restaurant> restaurantList = fd.getAllRestaurants();

        // Create maps for customer and restaurant names
        Map<Integer, String> customerNames = new HashMap<>();
        for (Customer customer : customerList) {
            customerNames.put(customer.getId(), customer.getName());
        }

        Map<Integer, String> restaurantNames = new HashMap<>();
        for (Restaurant restaurant : restaurantList) {
            restaurantNames.put(restaurant.getId(), restaurant.getName());
        }
        request.setAttribute("productId", productId);
        request.setAttribute("customerId", customerId);
        request.setAttribute("feedback", feedback);
        request.setAttribute("customerNames", customerNames);
        request.setAttribute("restaurantNames", restaurantNames);
        request.getRequestDispatcher("CustomerViewFeedback.jsp").forward(request, response);
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
