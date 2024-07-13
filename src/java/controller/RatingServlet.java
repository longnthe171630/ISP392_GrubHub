package controller;

import dao.FeedbackDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RatingServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rate = request.getParameter("rate");
        String description = request.getParameter("description");

        if (rate == null || description == null) {
            System.out.println("Missing parameters");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing parameters");
            return;
        }
//        FeedbackDAO fd = new FeedbackDAO()
//                

        System.out.println("Rating: " + rate);
        System.out.println("Description: " + description);

        request.setAttribute("rate", rate);
        request.setAttribute("description", description);
        request.getRequestDispatcher("Test.jsp").forward(request, response);
    }
}
