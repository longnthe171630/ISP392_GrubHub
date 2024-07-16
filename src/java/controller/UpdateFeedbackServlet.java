package controller;

import dao.FeedbackDAO;
import model.Feedback;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "UpdateFeedbackServlet", urlPatterns = {"/updateFeedback"})
public class UpdateFeedbackServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set the character encoding for the request
        request.setCharacterEncoding("UTF-8");

        // Get the orderId from the request parameter
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        int productId= Integer.parseInt(request.getParameter("productId"));
        
        // Retrieve the existing feedback using FeedbackDAO
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        Feedback feedback = feedbackDAO.getFeedbackByOrderIdAndProductId(orderId,productId);

        // Set the feedback as a request attribute and forward to the JSP page
        request.setAttribute("feedback", feedback);
        request.setAttribute("customerId", customerId);
        request.setAttribute("productId", productId);
        request.getRequestDispatcher("EditFeedback.jsp").forward(request, response);
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
        return "Servlet that retrieves feedback for editing";
    }
}
