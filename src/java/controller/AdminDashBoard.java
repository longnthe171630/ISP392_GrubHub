/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import model.Order;

/**
 *
 * @author manh0
 */
@WebServlet(name="AdminDashBoard", urlPatterns={"/admindashboard"})
public class AdminDashBoard extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Initialize DAOs
        OrderDAO orderDAO = new OrderDAO();
        Map<String, Double> monthlyRevenue = orderDAO.getMonthlyRevenue();
        
        request.setAttribute("monthlyRevenue", monthlyRevenue);
        request.getRequestDispatcher("Revenue.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet for fetching monthly revenue data";
    }

}
