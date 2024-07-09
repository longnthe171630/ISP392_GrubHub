package controller;

import dao.CartDAO;
import dao.CustomerDAO;
import dao.ProductDAO;
import model.Cart;
import model.CartItem;
import model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import model.Account;
import model.Customer;

public class BuyServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("acc");

        // Check if user is logged in
        if (account == null || account.getRole() != 2) { // Role 2 for customers
            // Redirect to login page or show error message
            response.sendRedirect("login"); // Change "login" to your actual login page URL
            return;
        }

        Cart cart = null;
        Object o = session.getAttribute("cart");
        if (o != null) {
            cart = (Cart) o;
        } else {
            cart = new Cart();
        }

        String tnum = request.getParameter("num");
        String tid = request.getParameter("id");
        int num, id;
        try {
            num = Integer.parseInt(tnum);
            id = Integer.parseInt(tid);
            System.out.println("Product ID: " + id + ", Quantity: " + num);  // Debugging line

            CustomerDAO customerDAO = new CustomerDAO();
            Customer customer = customerDAO.getCustomerByAccID(account.getId());
            if (customer != null) {
                CartDAO cartDAO = new CartDAO();
                cartDAO.addProductToCart(customer.getId(), id, num);  // Add product to cart in database
            } else {
                System.out.println("Customer not found for account ID: " + account.getId());
            }

            ProductDAO dao = new ProductDAO();
            Product p = dao.getProduct(id);
            CartItem t = new CartItem(p, num, p.getPrice());  // Ensure price is double
            cart.addItem(t);
        } catch (NumberFormatException e) {
            num = 1;  // Default quantity
        }

        List<CartItem> list = cart.getItems();
        session.setAttribute("cart", cart);
        session.setAttribute("listItemsInCart", list);
        session.setAttribute("cartSize", list.size());  // Corrected to cartSize
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            response.setContentType("application/json");
            response.getWriter().write("{\"cartSize\": " + cart.getItems().size() + "}");
        } else {
            response.sendRedirect("home");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
