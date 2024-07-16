/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAO;
import dao.CartDAO;
import dao.CustomerDAO;
import model.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Cart;
import model.CartItem;
import model.Customer;

/**
 *
 * @author Admin
 */
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        //b1 get user pass tu cookie
        Cookie arr[] = request.getCookies();
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

        //b2 set user and pass vao login form
        request.getRequestDispatcher("Login.jsp").forward(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        String user = request.getParameter("username");
        String pass = request.getParameter("password");
        String remember = request.getParameter("remember");
        AccountDAO accountDAO = new AccountDAO();
        Account a = accountDAO.checkAccount(user, pass);

        if (a == null) {
            request.setAttribute("alert", "Wrong user or password!");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("acc", a);
            session.setAttribute("username", user);

            //luu account len cookie
            Cookie u = new Cookie("userA", user);
            Cookie p = new Cookie("passA", pass);
            u.setMaxAge(60);
            if (remember != null) {
                p.setMaxAge(60);
            } else {
                p.setMaxAge(0);
            }
            response.addCookie(u);//luu len trinh duyet
            response.addCookie(p);

            switch (a.getRole()) {
                case 1: // Admin
                    response.sendRedirect("admin");
                    break;
                case 2: // Customer
                    if (a.getRole() == 2) { // Assuming role 2 is for customers
                        CustomerDAO customerDAO = new CustomerDAO();
                        Customer customer = customerDAO.getCustomerByAccID(a.getId());
                        CartDAO dao = new CartDAO();
                        List<CartItem> cartItems = dao.getProductsInCart(customer.getId());
                        Cart cart = new Cart(new ArrayList<>(cartItems)); // Initialize Cart with ArrayList

                        session.setAttribute("cart", cart);
                    }
                    response.sendRedirect("home");
                    break;
                case 3: // Restaurant
                    response.sendRedirect("RestaurantDashboard.jsp");
                    break;
                case 4: // Shipper
                    response.sendRedirect("ShipperDashboard.jsp");
                    break;
                default:
                    response.sendRedirect("Home.jsp");
                    break;
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
