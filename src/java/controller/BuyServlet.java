package controller;

import dao.CartDAO;
import dao.ProductDAO;
import model.Customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class BuyServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDAO productDAO = new ProductDAO();
        CartDAO cartDAO = new CartDAO();

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("account");

        if (customer == null) {
            response.sendRedirect("LoginCus.jsp");
            return;
        }

        int customerId = customer.getId();
        String productId = request.getParameter("id");
        String num = request.getParameter("num");

        // Add product to the database cart
        int quantity = Integer.parseInt(num);
        cartDAO.addProductToCart(customerId, Integer.parseInt(productId), quantity);

        // Manage cookie
        Cookie[] cookies = request.getCookies();
        String txt = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cart")) {
                    txt += cookie.getValue();
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }

        if (txt.isEmpty()) {
            txt = productId + ":" + num;
        } else {
            txt = txt + "/" + productId + ":" + num;
        }
        Cookie c = new Cookie("cart", txt);
        c.setMaxAge(2 * 24 * 60 * 60);  // 2 days
        response.addCookie(c);

        request.getRequestDispatcher("home").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
