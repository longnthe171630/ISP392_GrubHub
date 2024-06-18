package controller;

import dao.CartDAO;
import dao.CustomerDAO;
import dao.ProductDAO;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cart;
import model.CartItem;
import model.Customer;
import model.Product;

public class ShopServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Customer customer = (Customer) session.getAttribute("account");
        
        if (customer == null) {
            // Handle login failure
            response.sendRedirect("LoginCus.jsp");
            return;
        }
        
        // Get products in cart from database
        CartDAO cartDAO = new CartDAO();
        List<CartItem> cartItems = cartDAO.getProductsInCart(customer.getId());

        // Retrieve cart data from cookies
        Cookie[] cookies = request.getCookies();
        StringBuilder cartData = new StringBuilder();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cart")) {
                    cartData.append(URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8));
                }
            }
        }

        // Create cart object using both database and cookie data
        Cart cart = new Cart(cartData.toString(), cartItems);

        // Get cart items and size
        List<CartItem> finalCartItems = cart.getItems();
        int cartSize = finalCartItems != null ? finalCartItems.size() : 0;

        // Set attributes for the cart size and items
        session.setAttribute("cart", cart);
        session.setAttribute("cartSize", cartSize);
        
        // Fetch all products to display on the home page
        ProductDAO productDAO = new ProductDAO();
        List<Product> productList = productDAO.getProducts();
        
        // Set attributes and forward to Home.jsp
        request.setAttribute("size", cartSize);
        request.setAttribute("data", productList);
        request.getRequestDispatcher("Home.jsp").forward(request, response);
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
