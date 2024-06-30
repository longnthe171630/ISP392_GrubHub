package controller;

import dao.CartDAO;
import dao.ProductDAO;
import model.CartItem;
import model.Customer;
import model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("acc");

        if (customer != null) {
            int customerId = customer.getId();
            CartDAO cartDAO = new CartDAO();
            List<CartItem> cartItems = cartDAO.getProductsInCart(customerId);
            double total = calculateTotal(cartItems);

            request.setAttribute("cartItems", cartItems);
            request.setAttribute("total", total);
            request.getRequestDispatcher("Cart.jsp").forward(request, response);
        } else {
            response.sendRedirect("LoginCus.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("acc");

        if (customer != null) {
            try {
                int customerId = customer.getId();
                int productId = Integer.parseInt(request.getParameter("productId"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));

                CartDAO cartDAO = new CartDAO();
                cartDAO.addProductToCart(customerId, productId, quantity);

                response.sendRedirect("cart");
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.sendRedirect("error.jsp");
            }
        } else {
            response.sendRedirect("LoginCus.jsp");
        }
    }

    private double calculateTotal(List<CartItem> cartItems) {
        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }
        return total;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
