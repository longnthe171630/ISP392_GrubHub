package controller;

import dao.FeedbackDAO;
import dao.ProductDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Feedback;

/**
 * Servlet implementation class AddFeedbackServlet
 */
public class AddFeedbackServlet extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Get the parameters
            String orderIdStr = request.getParameter("orderId");
            String productIdStr = request.getParameter("productId");
            String customerIdStr = request.getParameter("customerId");
            String valueStr = request.getParameter("number");
            String description = request.getParameter("description");

            // Validate parameters
            if (orderIdStr == null || productIdStr == null || customerIdStr == null || valueStr == null || description == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing parameters");
                return;
            }

            // Parse the parameters
            int orderId = Integer.parseInt(orderIdStr);
            int productID = Integer.parseInt(productIdStr);
            int customerID = Integer.parseInt(customerIdStr);
            int value = Integer.parseInt(valueStr);

            // Get restaurant ID
            ProductDAO pd = new ProductDAO();
            int restaurantID = pd.getRestaurantIdByProductId(productID);

            // Create a new Feedback object
            Feedback feedback = new Feedback(customerID, restaurantID, orderId, value, productID, description, null);

            // Add feedback to the database
            FeedbackDAO fd = new FeedbackDAO();
            
            System.out.println(value + " : value");
            System.out.println(description + " : descript");
            fd.addFeedback(feedback);

            // Redirect to a confirmation page or display the feedback

            response.sendRedirect("historyorder");

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid number format");
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Server error");
            e.printStackTrace();
        }
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
        return "AddFeedbackServlet";
    }
}
