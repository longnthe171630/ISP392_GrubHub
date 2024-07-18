package controller;

import dao.CartDAO;
import dao.CustomerDAO;
import model.Cart;
import model.CartItem;
import model.Account;
import model.Customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowServlet", urlPatterns = {"/show"})
public class ShowServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true); // Get or create session

        // Retrieve customer from session
        Account account = (Account) session.getAttribute("acc");
        Cart cart = (Cart) session.getAttribute("cart");

        // Initialize CartDAO and list to fetch cart items
        CartDAO cartDAO = new CartDAO();
        List<CartItem> listCart = null;

        // Check if cart exists in session; if not, initialize a new one
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        } else {
            // Load cart items from database using customer ID
            if (account != null && account.getRole() == 2) { // Assuming role 2 is for customers
                CustomerDAO customerDAO = new CustomerDAO();
                Customer customer = customerDAO.getCustomerByAccID(account.getId());
                System.out.println("Logged in as: " + account.getUsername());
                if (customer != null) {
                    listCart = cartDAO.getProductsInCart(customer.getId());
                    cart.setItems(listCart); // Set the retrieved items to cart
                }
            }
        }

        // Inside doGet method of ShowServlet
        if (listCart != null) {
            for (CartItem item : listCart) {
                System.out.println("Product ID: " + item.getProduct().getId() + ", Quantity: " + item.getQuantity());
            }
        } else {
            System.out.println("No items found in the cart.");
        }

        // Set attributes for forwarding to Cart.jsp
        request.setAttribute("listCart", listCart);

        // Forward the request to Cart.jsp for displaying the cart
        request.getRequestDispatcher("Cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // In case the servlet is called via POST
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Handles displaying the cart";
    }
}
