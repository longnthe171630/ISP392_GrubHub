package controller;

import dao.CartDAO;
import dao.ProductDAO;
import model.Cart;
import model.CartItem;
import model.Product;
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
            response.sendRedirect("LoginCus.jsp"); // Redirect to login if session is null
            return;
        }

        Customer customer = (Customer) session.getAttribute("account");
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
        Cart cart = getCartFromSessionOrCookie(request, response, productDAO);

        if (num == -1 && cart.getQuantityByID(id) <= 1) {
            cart.removeItem(id);
            cartDAO.deleteProductFromCart(customerId, id);
        } else {
            int numStore = product.getQuantity();
            if (num == 1 && cart.getQuantityByID(id) >= numStore) {
                num = 0;
            }
            double price = product.getPrice();
            CartItem cartItem = new CartItem(product, num, (int) price);
            cart.addItem(cartItem);
            cartDAO.updateCartQuantity(customerId, id, cart.getQuantityByID(id));
        }

        updateCartCookie(response, cart);

        request.setAttribute("cart", cart);
        request.getRequestDispatcher("Cart.jsp").forward(request, response);
    }

    private void deleteFromCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("CusLogin.jsp"); // Redirect to login if session is null
            return;
        }

        Customer customer = (Customer) session.getAttribute("account");
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

        Cart cart = getCartFromSessionOrCookie(request, response, null);

        cart.removeItem(id);

        updateCartCookie(response, cart);

        request.setAttribute("cart", cart);
        request.getRequestDispatcher("Cart.jsp").forward(request, response);
    }

    private Cart getCartFromSessionOrCookie(HttpServletRequest request, HttpServletResponse response, ProductDAO productDAO)
            throws IOException {
        HttpSession session = request.getSession(false);
        Cart cart = new Cart();

        if (session != null) {
            cart = (Cart) session.getAttribute("cart");
        }

        if (cart == null || cart.getItems().isEmpty()) {
            String txt = "";
            Cookie[] arr = request.getCookies();
            if (arr != null) {
                for (Cookie o : arr) {
                    if (o.getName().equals("cart")) {
                        txt += o.getValue();
                    }
                }
            }
            // Assuming productList is obtained from ProductDAO
            List<Product> productList = productDAO != null ? productDAO.getProducts() : null;

            // Convert List<Product> to List<CartItem>
            List<CartItem> cartItemList = new ArrayList<>();
            if (productList != null) {
                for (Product product : productList) {
                    // Create a CartItem object for each Product
                    CartItem cartItem = new CartItem(product, 1); // Assuming initial quantity is 1, adjust as needed
                    cartItemList.add(cartItem);
                }
            }

            // Initialize Cart with the List<CartItem>
            cart = new Cart(txt, cartItemList);
        }

        return cart;
    }

    private void updateCartCookie(HttpServletResponse response, Cart cart) {
        String txt = cart.serialize();
        Cookie c = new Cookie("cart", txt);
        c.setMaxAge(2 * 24 * 60 * 60);
        response.addCookie(c);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
