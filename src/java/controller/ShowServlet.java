package controller;

import dao.ProductDAO;
import model.Cart;
import model.CartItem;
import model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ShowServlet", urlPatterns = {"/showCart"})
public class ShowServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true); // Get or create session
        Cart cart = (Cart) session.getAttribute("cart");

        // Always reset the cart to prevent duplication
        cart = new Cart();
        session.setAttribute("cart", cart);

        // Retrieve cart data from cookies
        Cookie[] cookies = request.getCookies();
        String cookieCartData = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cart")) {
                    cookieCartData = cookie.getValue();
                    break;
                }
            }
        }

        // Debug: Print cookie data
        System.out.println("Cookie Cart Data: " + cookieCartData);

        // Assuming productList is obtained from ProductDAO
        ProductDAO productDAO = new ProductDAO();
        List<Product> productList = productDAO.getProducts();

        // Convert cookie data to List<CartItem>
        List<CartItem> cookieCartItems = parseCookieData(cookieCartData, productList);

        // Add items from cookies to the cart
        for (CartItem item : cookieCartItems) {
            cart.addItem(item);
        }

        // Save the updated cart in the session
        session.setAttribute("cart", cart);

        request.setAttribute("cart", cart);
        request.getRequestDispatcher("Cart.jsp").forward(request, response);
    }

    private List<CartItem> parseCookieData(String cookieCartData, List<Product> productList) {
        List<CartItem> cartItemList = new ArrayList<>();
        if (cookieCartData != null && !cookieCartData.isEmpty()) {
            String[] items = cookieCartData.split("/");
            for (String item : items) {
                String[] parts = item.split(":");
                if (parts.length == 2) {
                    int productId = Integer.parseInt(parts[0]);
                    int quantity = Integer.parseInt(parts[1]);

                    // Find the product by ID from productList
                    for (Product product : productList) {
                        if (product.getId() == productId) {
                            CartItem cartItem = new CartItem(product, quantity);
                            cartItemList.add(cartItem);
                            break;
                        }
                    }
                }
            }
        }
        return cartItemList;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Any shared logic between GET and POST can be added here
    }

    @Override
    public String getServletInfo() {
        return "Handles displaying the cart";
    }
}
