package controller;

import dao.CartDAO;
import dao.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cart;
import model.CartItem;
import model.Customer;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CheckoutServlet", urlPatterns = {"/checkout"})
public class CheckoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("account");

        if (customer == null) {
            response.sendRedirect("LoginCus.jsp");
            return;
        }

        CartDAO cartDAO = new CartDAO();
        List<CartItem> cartItems = cartDAO.getProductsInCart(customer.getId());

        // Debug: Print cart items
        for (CartItem item : cartItems) {
            System.out.println("CartItem: " + item.getProduct().getName() + ", Quantity: " + item.getQuantity() + ", Price: " + item.getPrice());
        }

        Cookie[] cookies = request.getCookies();
        String cookieCartData = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cart")) {
                    cookieCartData += cookie.getValue();
                }
            }
        }

        Cart cart = new Cart(cookieCartData, cartItems);

        // Debug: Print total money
        double totalMoney = cart.getTotalMoney();
        System.out.println("Total money in cart: " + totalMoney);

        OrderDAO orderDAO = new OrderDAO();
        orderDAO.addOrder(customer, cart);

        // Clear the session cart
        session.setAttribute("cart", new Cart());

        // Clear the cart cookie
        Cookie cookie = new Cookie("cart", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        // Debug: Check if cart is cleared
        Cart clearedCart = (Cart) session.getAttribute("cart");
        System.out.println("Cleared cart total money: " + clearedCart.getTotalMoney());
        cartDAO.clearCart(customer.getId());
        request.getRequestDispatcher("home").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Handles the checkout process and clears the cart";
    }
}
