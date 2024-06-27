/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DeliveryDAO;
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
        String action = request.getParameter("action");
        int order_id = Integer.parseInt(request.getParameter("id"));

        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("acc");
        int id = delivery.getDeliveryPersonIdByUsername(account.getUsername());

        switch (action) {
            case "accept":
                if (delivery.getDeliveryPersonIdByOrderId(order_id) != 0) { // Kiểm tra nếu không có delivery_person_id
                    String err = "The order was just received by someone else!";
                    request.getSession().setAttribute("err", err);
                    response.sendRedirect("deliveryorder");
                    break;
                } else {
                    delivery.updateDeliveryPersonId(id, order_id);
                    order.updateStatusOrderToDelivery(order_id);
                    delivery.updateStatusDelivery(order_id);
                    response.sendRedirect("deliverydashboard");
                    break;
                }
            case "reject":
                response.sendRedirect("deliveryorder");
                break;
            case "start":
                delivery.updateStatusDelivery_4(order_id);
                response.sendRedirect("deliverydashboard");
                break;
            case "wait":
                response.sendRedirect("deliveryorder");
                break;
            case "success":
                Part part = request.getPart("photo");
                // kiểm tra ảnh
                if (part.getSubmittedFileName() == null || part.getSubmittedFileName().trim().isEmpty() || part == null) {
                    String err = "Confirmation photo cannot be blank if delivered successfully!";
                    request.getSession().setAttribute("err", err);
                    response.sendRedirect("deliverydashboard");
                    break;
                } else {
                    try {
                        // lay duong dan luu anh
                        String path = request.getServletContext().getRealPath("/images/delivery_photo");
                        File dir = new File(path);

                        // xem duong dan nay da ton tai chua
                        if (!dir.exists()) {
                            // neu chua thì tạo
                            dir.mkdirs();
                        }
                        File image = new File(dir, part.getSubmittedFileName());

                        //ghi file vao trong duong dan 
                        part.write(image.getAbsolutePath());
                        // lấy đường dẫn của ảnh khi lưu vào để lưu vào db
                        String pathOfFile = request.getContextPath() + "/images/delivery_photo/" + image.getName();
                        delivery.savePathToDatabase(pathOfFile, order_id);
                        request.setAttribute("image", image);
                    } catch (IOException e) {
                        String err1 = "Lỗi catch rồi!";
                        System.out.println(err1);
                        request.setAttribute("err1", err1);
                        request.getRequestDispatcher("deliverystatus").forward(request, response);
                    }
                }
                delivery.updateStatusDelivery_2(order_id);
                response.sendRedirect("deliveryhistory");
                break;
            default:
                String err = "";
                request.getSession().setAttribute("err", err);
                response.sendRedirect("deliverydashboard");
                break;
        }
    }
}
