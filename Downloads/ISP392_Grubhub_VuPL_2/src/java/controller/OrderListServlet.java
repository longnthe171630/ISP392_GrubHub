package controller;

import dao.AccountDAO;
import dao.CustomerDAO;
import dao.FeedbackDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Order;
import dao.OrderDAO;
import dao.RestaurantDAO;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;

@WebServlet("/orderList")
public class OrderListServlet extends HttpServlet {

    private OrderDAO orderDAO = new OrderDAO(); // Assume you have this DAO class

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        AccountDAO ad = new AccountDAO();
        CustomerDAO cd = new CustomerDAO();
        OrderDAO od = new OrderDAO();
        RestaurantDAO rd = new RestaurantDAO();
        FeedbackDAO fd = new FeedbackDAO();

        int accID = ad.getAccountID(username);

        Customer cusID = cd.getCustomerByAccID(accID);
        if (cusID != null) {
            List<Order> orders = orderDAO.getOrdersByCustomerId(cusID.getId());
            request.setAttribute("orders", orders);
        } else {
            // Handle the case where cusID is null, perhaps by redirecting or displaying an error message
            // Example: request.setAttribute("errorMessage", "Customer ID not found");
        }

        request.getRequestDispatcher("OrderList.jsp").forward(request, response);
    }
}
