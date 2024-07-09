/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DeliveryDAO;
import dao.NotificationDAO;
import dao.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.InputStream;
import java.net.URLEncoder;
import model.Account;
import model.Delivery;
import utils.Image;

/**
 *
 * @author Long1
 */
@MultipartConfig()
@WebServlet(name = "DeliveryStatusServlet", urlPatterns = {"/deliverystatus"})
public class DeliveryStatusServlet extends HttpServlet {

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

        OrderDAO order = new OrderDAO();
        DeliveryDAO delivery = new DeliveryDAO();
        NotificationDAO notice = new NotificationDAO();
        String action = request.getParameter("action");
        int order_id = Integer.parseInt(request.getParameter("id"));

        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("acc");
        int id = delivery.getDeliveryPersonIdByUsername(account.getUsername());

        Image image = new Image();
        switch (action) {
            case "accept":
                if (delivery.getDeliveryPersonIdByOrderId(order_id) != 0) { // Kiểm tra nếu không có delivery_person_id
                    String err = "The order was just received by someone else!";
                    request.getSession().setAttribute("err", err);
                    response.sendRedirect("deliveryorder");
                    break;
                } else {
                    delivery.updateDeliveryPersonId(id, order_id);
                    order.updateStatusOrder(order_id);
                    delivery.updateStatusDelivery(order_id);
                    delivery.calculateDeliveryDuration(order_id);
                    String err = "You have successfully received this order!";
                    request.getSession().setAttribute("err", err);
                    response.sendRedirect("deliverydashboard");
                    break;
                }
            case "reject":
                String err2 = "Okay, let's look at the other orders";
                request.getSession().setAttribute("err", err2);
                response.sendRedirect("deliveryorder");
                break;
            case "start":
                delivery.updateStatusDelivery_4(order_id);
                order.updateStatusOrder_1(order_id);
                String err = "Let's complete this order!";
                request.getSession().setAttribute("err", err);
                response.sendRedirect("deliverydashboard");
                break;
            case "wait":
                String err1 = "Okay, wait a minute!";
                request.getSession().setAttribute("err", err1);
                response.sendRedirect("deliverydashboard");
                break;
            case "success":
                Part part = request.getPart("photo");
                // Sử dụng ImageUploader để xử lý upload ảnh
                String imagePathS = image.uploadImage(request, part, "/images/delivery_photo", "err");
                if (imagePathS == null) {
                    response.sendRedirect("deliverydashboard");
                } else {
                    try {
                        //Lưu đường dẫn ảnh vào database
                        delivery.savePathToDatabase(imagePathS, order_id);
                        request.setAttribute("image", image);
                        delivery.updateStatusDelivery_2(order_id);
                        delivery.calculateDeliveryDuration(order_id);
                        order.updateStatusOrder_2(order_id);
                        String err3 = "The order has been delivered successfully!";
                        request.getSession().setAttribute("err", err3);
                        notice.InsertNotice("The order has been delivered successfully!", order_id);
                        response.sendRedirect("deliveryhistory");
                    } catch (Exception e) {
                        String err0 = "Lỗi catch rồi!";
                        System.out.println(err0);
                        request.setAttribute("err1", err0);
                        request.getRequestDispatcher("deliverydashboard").forward(request, response);
                    }
                }
                break;
            case "failure":
                Part part1 = request.getPart("photo");
                String reason = request.getParameter("reason");
                // Sử dụng ImageUploader để xử lý upload ảnh
                String imagePathF = image.uploadImage(request, part1, "/images/delivery_photo", "err");
                if (imagePathF == null) {
                    response.sendRedirect("deliverydashboard");
                } else {
                    try {
                        //Lưu đường dẫn ảnh vào database
                        delivery.savePathToDatabase(imagePathF, order_id);
                        request.setAttribute("image", image);
                        delivery.updateStatusDelivery_3(order_id);
                        order.updateStatusOrder_3(order_id);
                        String err3 = "The order has been delivered failure!";
                        request.getSession().setAttribute("err", err3);
                        notice.InsertNotice("The order has been delivered failure!<br>Reason: "+reason, order_id);
                        response.sendRedirect("deliveryhistory");
                    } catch (Exception e) {
                        String err3 = "Lỗi catch rồi!";
                        System.out.println(err3);
                        request.setAttribute("err1", err3);
                        request.getRequestDispatcher("deliverydashboard").forward(request, response);
                    }
                }
                break;
            default:
                String err4 = "Something went wrong!";
                request.getSession().setAttribute("err", err4);
                response.sendRedirect("deliverydashboard");
                break;
        }
    }
}
