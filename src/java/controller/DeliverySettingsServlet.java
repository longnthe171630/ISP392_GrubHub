/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.DeliveryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Long1
 */
@WebServlet(name="DeliverySettingsServlet", urlPatterns={"/deliverysettings"})
public class DeliverySettingsServlet extends HttpServlet {
   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        DeliveryDAO dao = new DeliveryDAO();
        request.getRequestDispatcher("deliverysettings.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String status = request.getParameter("status");
        if(status.equals("payment")){
            request.getRequestDispatcher("deliverypayment.jsp").forward(request, response);
        }else{
            request.getRequestDispatcher("historyactive.jsp").forward(request, response);
        }
    }

}