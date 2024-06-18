package controller;

import dao.CartDAO;
import dao.CustomerDAO;
import model.Cart;
import model.CartItem;
import model.Customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CusLoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("username");
        String pass = request.getParameter("password");

        CustomerDAO customerDAO = new CustomerDAO();
        Customer a = customerDAO.getAccount(user, pass);

        if (a == null) {
            String er = "Username: " + user + " and password: " + pass + " don't exist";
            request.setAttribute("error", er);
            request.getRequestDispatcher("LoginCus.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession(true);
            session.setAttribute("account", a);

            // Retrieve cart items for the customer
            CartDAO dao = new CartDAO();
            List<CartItem> cartItems = dao.getProductsInCart(a.getId());
            Cart cart = new Cart(new ArrayList<>(cartItems)); // Initialize Cart with ArrayList

            session.setAttribute("cart", cart);

            // Handling cookies for remembering username and password
            Cookie[] arr = request.getCookies();
            if (arr != null) {
                for (Cookie o : arr) {
                    if (o.getName().equals("userA")) {
                        request.setAttribute("username", o.getValue());
                    }
                    if (o.getName().equals("passA")) {
                        request.setAttribute("password", o.getValue());
                    }
                }
            }

            request.getRequestDispatcher("home").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("username");
        String pass = request.getParameter("password");
        String remember = request.getParameter("remember");

        CustomerDAO customerDAO = new CustomerDAO();
        Customer c = customerDAO.getAccount(user, pass);

        if (c == null) {
            request.setAttribute("alert", "Wrong username or password!");
            request.getRequestDispatcher("LoginCus.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("account", c);

            // Retrieve cart items for the customer
            CartDAO dao = new CartDAO();
            List<CartItem> cartItems = dao.getProductsInCart(c.getId());
            Cart cart = new Cart(new ArrayList<>(cartItems)); // Initialize Cart with ArrayList

            session.setAttribute("cart", cart);

            // Setting cookies for remembering username and password
            Cookie userCookie = new Cookie("userA", user);
            Cookie passCookie = new Cookie("passA", pass);
            userCookie.setMaxAge(60); // Set expiry in seconds
            passCookie.setMaxAge(remember != null ? 60 : 0); // Set expiry only if "remember" is checked

            response.addCookie(userCookie);
            response.addCookie(passCookie);

            response.sendRedirect("home");
        }
    }

    @Override
    public String getServletInfo() {
        return "Customer Login Servlet";
    }
}
