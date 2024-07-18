/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import model.Product;

/**
 *
 * @author manh0
 */
public class SortProductServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SortProductServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SortProductServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        String sortBy = request.getParameter("sortBy");
        String sortOrder = request.getParameter("sortOrder");

        ProductDAO dao = new ProductDAO();
        // Logic to retrieve and sort products based on sortBy and sortOrder
        List<Product> products = dao.getProducts(); // Adjust to your method

        // Sort products based on sortBy and sortOrder
        if ("price".equals(sortBy)) {
            if ("asc".equals(sortOrder)) {
                Collections.sort(products, Comparator.comparing(Product::getPrice));
            } else if ("desc".equals(sortOrder)) {
                Collections.sort(products, Comparator.comparing(Product::getPrice).reversed());
            }
        } else if ("name".equals(sortBy)) {
            if ("asc".equals(sortOrder)) {
                Collections.sort(products, Comparator.comparing(Product::getName));
            } else if ("desc".equals(sortOrder)) {
                Collections.sort(products, Comparator.comparing(Product::getName).reversed());
            }
        }
        // Set sorted product list as request attribute
        request.setAttribute("sortedProducts", products);

        // Forward to your JSP where products are displayed
        request.getRequestDispatcher("Category.jsp").forward(request, response);
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
        processRequest(request, response);
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
