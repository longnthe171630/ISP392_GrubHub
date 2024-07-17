package controller;

import dao.ProductDAO;
import model.Account;
import model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "FavoriteServlet", urlPatterns = {"/favorite"})
public class FavoriteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        handleRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        handleRequest(request, response);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("acc");

        // Check if user is logged in
        if (account == null || account.getRole() != 2) { // Role 2 for customers
            // Redirect to login page or show error message
            response.sendRedirect("login"); // Change "login" to your actual login page URL
            return;
        }

        String productId = request.getParameter("productId");
        String action = request.getParameter("action");

        if (productId == null || productId.isEmpty()) {
            deleteProductById(productId, request.getSession());
        }

        // Assuming you have a method to get a product by ID
        if ("delete".equals(action)) {
            if (productId != null && !productId.isEmpty()) {
                deleteProductById(productId, session);
            }
            // Redirect or send response back
            response.sendRedirect("Favorites.jsp");
            return;
        }

        if (productId != null && !productId.isEmpty()) {
            // Assuming you have a method to get a product by ID
            Product product = getProductById(productId);
            List<Product> favorites = (List<Product>) session.getAttribute("favorites");
            if (favorites == null) {
                favorites = new ArrayList<>();
            }

            // Add the product to the favorites list if it doesn't already exist
            if (!favorites.contains(product)) {
                favorites.add(product);
            }

            session.setAttribute("favorites", favorites);
            if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
                response.setContentType("application/json");
                response.getWriter().write("{\"favoritesSize\": " + favorites.size() + "}");
            } else {
                response.sendRedirect("Favorites.jsp");  // Redirect back to the home page or appropriate page
            }
        }
    }

    private Product getProductById(String productId) {
        ProductDAO productDAO = new ProductDAO();
        return productDAO.getProduct(Integer.parseInt(productId)); // Ensure your getProduct method takes an integer
    }

    private void deleteProductById(String productId, HttpSession session) {
        List<Product> favorites = (List<Product>) session.getAttribute("favorites");
        if (favorites != null) {
            Iterator<Product> iterator = favorites.iterator();
            while (iterator.hasNext()) {
                Product product = iterator.next();
                if (String.valueOf(product.getId()).equals(productId)) {
                    iterator.remove();
                    break; // Exit loop once found and removed
                }
            }
            session.setAttribute("favorites", favorites);
        }
    }

    @Override
    public String getServletInfo() {
        return "Handles adding products to favorites";
    }
}
