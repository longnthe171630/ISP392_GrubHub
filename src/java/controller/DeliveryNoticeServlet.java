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
import java.util.ArrayList;
import java.util.List;
import model.Account;
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
        
        //Lấy data bằng session
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("acc");
        //Lấy dữ liệu từ DAO
        DeliveryDAO dao = new DeliveryDAO();
        //Lấy info của account login vào
        int id = dao.getDeliveryPersonIdByUsername(account.getUsername());
        
        NotificationDAO notice = new NotificationDAO();
        List<Notification> list = notice.getListNotification(id);
        
        request.setAttribute("list", list);
        request.getRequestDispatcher("deliverynotice.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        String reason = request.getParameter("reason");
//        int order_id = Integer.parseInt(request.getParameter("id"));
//
//        HttpSession session = request.getSession();
//
//        DeliveryDAO delivery = new DeliveryDAO();
//        NotificationDAO notice = new NotificationDAO();
//
//        notice.InsertNotice("You was update a status order: "+reason, order_id);
//        delivery.updateStatusDelivery_3(order_id);
//        Notification des = notice.getNoticeByOrderId(order_id);
//
//        String err = "Updated the reason for the failed order!!";
//        request.getSession().setAttribute("err", err);
//        response.sendRedirect("deliveryhistory");
    }

}