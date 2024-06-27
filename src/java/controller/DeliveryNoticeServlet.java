/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DeliveryDAO;
import dao.NotificationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Notification;

/**
 *
 * @author Long1
 */
@WebServlet(name = "DeliveryNoticeServlet", urlPatterns = {"/deliverynotice"})
public class DeliveryNoticeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
        String reason = request.getParameter("reason");
        int order_id = Integer.parseInt(request.getParameter("id"));

        HttpSession session = request.getSession();

        DeliveryDAO delivery = new DeliveryDAO();
        NotificationDAO notice = new NotificationDAO();

        notice.InsertNotice(reason, order_id);
        delivery.updateStatusDelivery_3(order_id);
        Notification des = notice.getNoticeByOrderId(order_id);

        String err = "Updated the reason for the failed order!!";
        request.getSession().setAttribute("err", err);
        response.sendRedirect("deliveryhistory");
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
