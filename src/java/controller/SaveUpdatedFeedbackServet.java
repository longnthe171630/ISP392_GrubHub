package controller;

import dao.FeedbackDAO;
import model.Feedback;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SaveUpdatedFeedbackServet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FeedbackDAO fd = new FeedbackDAO();

        try {
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            int productId = Integer.parseInt(request.getParameter("productId"));
            int customerId = Integer.parseInt(request.getParameter("customerId"));
            int rate = Integer.parseInt(request.getParameter("rate"));
            String description = request.getParameter("description");

            if (rate < 1 || rate > 5) {
                request.setAttribute("Alert", "Rating must be between 1 and 5.");
                request.getRequestDispatcher("editFeedback.jsp").forward(request, response);
                return;
            }

            Feedback feedback = fd.getFeedbackByOrderIdAndProductId(orderId, productId);
//            if (feedback == null) {
//                feedback = new Feedback();
////                feedback.setCustomerID(customerId);
//                feedback.setOrderID(orderId);
//                feedback.setProductID(productId);
//            }

            feedback.setValue(rate);
            feedback.setDescription(description);
            fd.updateFeedback(feedback);

            response.sendRedirect("historydetails?orderId=" + orderId + "&customerId=" + customerId);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input data.");
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while updating feedback.");
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
        return "Servlet for updating feedback";
    }
}
