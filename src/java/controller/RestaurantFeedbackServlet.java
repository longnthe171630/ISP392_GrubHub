package controller;

import dao.AccountDAO;
import dao.FeedbackDAO;
import dao.ProductDAO;
import dao.RestaurantDAO;
import model.Feedback;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class RestaurantFeedbackServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        AccountDAO ad = new AccountDAO();
        RestaurantDAO rd = new RestaurantDAO();
        FeedbackDAO fd = new FeedbackDAO();
        ProductDAO pd = new ProductDAO();
        try {
            int accID = ad.getAccountID(username);
            System.out.println(accID);
            int resID = rd.getRestaurantIDbyAccID(accID);
            System.out.println(resID);
            List<Feedback> feedbackList = fd.getFeedbackOfARestaurant(resID);
            System.out.println(feedbackList);
            

            List<Integer> customerIDs = new ArrayList<>();
            List<Integer> productIDs = new ArrayList<>();
            for (Feedback feedback : feedbackList) {
                customerIDs.add(feedback.getCustomerID());
                productIDs.add(feedback.getProductID());
            }

            Map<Integer, String> customerNames = fd.getCustomerNamesByIDs(customerIDs);
            Map<Integer, String> productNames = pd.getProductNamesByIDs(productIDs); // Get product names

            System.out.println("Customer names map size: " + customerNames.size());

            // Get sorting parameter from request
            String sort = request.getParameter("sort");

            // Sort the feedback list based on the sorting parameter
            if ("asc".equals(sort)) {
                Collections.sort(feedbackList, Comparator.comparingInt(Feedback::getValue));
            } else if ("desc".equals(sort)) {
                Collections.sort(feedbackList, Comparator.comparingInt(Feedback::getValue).reversed());
            }

            request.setAttribute("feedbackList", feedbackList);
            request.setAttribute("customerNames", customerNames);
            request.setAttribute("productNames", productNames); // Set product names in the request

            // Debug output
//            System.out.println("Sorted feedback list: " + feedbackList);
            request.getRequestDispatcher("RestaurantFeedback.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error processing feedback data", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
