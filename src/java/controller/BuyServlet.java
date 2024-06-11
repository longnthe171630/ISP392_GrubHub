package controller;

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

public class BuyServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
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

            ProductDAO dao = new ProductDAO();
            Product p = dao.getProduct(id);
            double price = p.getPrice() * 1.2;  // Assuming 1.2 is the price multiplier
            CartItem t = new CartItem(p, num,(int) price);  // Ensure price is double
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
