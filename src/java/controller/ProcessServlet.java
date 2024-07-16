package controller;

import dao.CartDAO;
import dao.CustomerDAO;
import dao.ProductDAO;
import model.Cart;
import model.CartItem;
import model.Product;
import model.Customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import model.Account;

@WebServlet(name = "ProcessServlet", urlPatterns = {"/process"})
public class ProcessServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        updateCart(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        deleteFromCart(request, response);
    }

    private void updateCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("Login.jsp"); // Redirect to login if session is null
            return;
        }

        Account account = (Account) session.getAttribute("acc");
        if (account != null && account.getRole() == 2) { // Assuming role 2 is for customers
            CustomerDAO customerDAO = new CustomerDAO();
            Customer customer = customerDAO.getCustomerByAccID(account.getId());
            int customerId = customer.getId();
            int id, num;
            try {
                id = Integer.parseInt(request.getParameter("id"));
                num = Integer.parseInt(request.getParameter("num"));
            } catch (NumberFormatException e) {
                response.sendRedirect("Cart.jsp"); // Handle invalid input
                return;
            }

            ProductDAO productDAO = new ProductDAO();
            Product product = productDAO.getProduct(id);
            if (product == null) {
                response.sendRedirect("Cart.jsp"); // Handle non-existing product
                return;
            }

            CartDAO cartDAO = new CartDAO();
            Cart cart = (Cart) session.getAttribute("cart");

            if (cart == null) {
                cart = new Cart();
            }

            int availableQuantity = product.getQuantity();
            int currentQuantity = cart.getQuantityByID(id);

            // Update quantity or remove item based on user input
            if (num == -1 && currentQuantity <= 1) {
                cart.removeItem(id);
                cartDAO.deleteProductFromCart(customerId, id);
            } else {
                if (num == 1 && currentQuantity + num > availableQuantity) {
                    num = 0;
                    String message = "alert('Sản phẩm này đã hết hàng hoặc không đủ số lượng.');";
                    request.setAttribute("outofstock", message);
                }
                double price = product.getPrice();
                CartItem cartItem = new CartItem(product, num, (int) price);
                cart.addItem(cartItem);
                cartDAO.updateCartQuantity(customerId, id, cart.getQuantityByID(id));
            }

            session.setAttribute("cart", cart);
            request.setAttribute("cart", cart);
        }

        request.getRequestDispatcher("Cart.jsp").forward(request, response);
    }

    private void deleteFromCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("Login.jsp"); // Redirect to login if session is null
            return;
        }

        Account account = (Account) session.getAttribute("acc");
        if (account != null && account.getRole() == 2) { // Assuming role 2 is for customers
            CustomerDAO customerDAO = new CustomerDAO();
            Customer customer = customerDAO.getCustomerByAccID(account.getId());
            int customerId = customer.getId();
            int id;
            try {
                id = Integer.parseInt(request.getParameter("id"));
            } catch (NumberFormatException e) {
                response.sendRedirect("Cart.jsp"); // Handle invalid input
                return;
            }

            CartDAO cartDAO = new CartDAO();
            cartDAO.deleteProductFromCart(customerId, id);
            Cart cart = (Cart) session.getAttribute("cart");

            if (cart == null) {
                cart = new Cart();
            }

            cart.removeItem(id);
            session.setAttribute("cart", cart);
            request.setAttribute("cart", cart);
        } else {
            response.sendRedirect("Login.jsp"); // Redirect to login if no customer is found
            return;
        }

        request.getRequestDispatcher("Cart.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Handles updating and deleting items in the cart";
    }
}
