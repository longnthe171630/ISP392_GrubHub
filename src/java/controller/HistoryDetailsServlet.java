package controller;

import dao.FeedbackDAO;
import dao.OrderDetailsDAO;
import dao.ProductDAO;
import model.OrderDetails;
import model.Product;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "HistoryDetailsServlet", urlPatterns = {"/historydetails"})
public class HistoryDetailsServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        OrderDetailsDAO od = new OrderDetailsDAO();
        FeedbackDAO fd = new FeedbackDAO();
        ProductDAO pd = new ProductDAO();
        
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        List<OrderDetails> listOD = od.getOrderDetailsByOrder_Locct(orderId);

        // Maps to hold product names and images
        Map<Integer, String> productNames = new HashMap<>();
        Map<Integer, String> productImages = new HashMap<>();
        Map<Integer, Boolean> feedbackStatuses = new HashMap<>();

        List<Product> products = pd.getProducts(); // Assume this method fetches all products
        for (Product product : products) {
            productNames.put(product.getId(), product.getName());
            productImages.put(product.getId(), product.getImage());
        }

        // Check if each order detail has feedback
        for (OrderDetails orderDetail : listOD) {
            boolean hasFeedback = fd.hasFeedback(orderDetail.getId(),orderDetail.getProduct_id());
            feedbackStatuses.put(orderDetail.getId(), hasFeedback);
        }
        request.setAttribute("customerId", customerId);
        request.setAttribute("OrderID", orderId);
        request.setAttribute("listOD", listOD);
        request.setAttribute("productNames", productNames);
        request.setAttribute("productImages", productImages);
        request.setAttribute("feedbackStatuses", feedbackStatuses);

        request.getRequestDispatcher("HistoryDetails.jsp").forward(request, response);
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
        return "Handles displaying order details with feedback links";
    }
}
