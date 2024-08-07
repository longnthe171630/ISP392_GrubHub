/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAO;
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
import model.Customer;

/**
 *
 * @author Admin
 */
public class CusLoginServlet extends HttpServlet {

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
                if (o.getName().equals("username")) {
                    request.setAttribute("username", o.getValue());
                }
                if (o.getName().equals("password")) {
                    request.setAttribute("password", o.getValue());
                }
            }
        }

        //b2 set user and pass vao login form
        request.getRequestDispatcher("LoginCus.jsp").forward(request, response);

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
        HttpSession session = request.getSession();
        String remember = request.getParameter("remember");
        CustomerDAO customerDAO = new CustomerDAO();
        Customer c = customerDAO.checkCustomer(user, pass);
        session.setAttribute("username",user);
        if (c == null) {
            request.setAttribute("alert", "Wrong user or password!");
            request.getRequestDispatcher("LoginCus.jsp").forward(request, response);
        } else {          
            session.setAttribute("acc", c);
            session.setAttribute("customer", c);
            //luu account len cookie
            Cookie u = new Cookie("username", user);
            Cookie p = new Cookie("password", pass);
            u.setMaxAge(60);
            if (remember != null) {
                p.setMaxAge(60);
            } else {
                p.setMaxAge(0);
            }
            response.addCookie(u);//luu len trinh duyet
            response.addCookie(p);
            response.sendRedirect("home");

            /**
             * Returns a short description of the servlet.
             *
             * @return a String containing servlet description
             */
        }// </editor-fold>
    }
}
