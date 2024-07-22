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
@WebServlet(name = "DeliverySettingsServlet", urlPatterns = {"/deliverysettings"})
public class DeliverySettingsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.getRequestDispatcher("deliverysettings.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy tham số ngôn ngữ từ yêu cầu
        String lang = request.getParameter("lang");
        if (lang != null) {
            // Cập nhật ngôn ngữ trong session
            request.getSession().setAttribute("lang", lang);
        }
        // Trả về mã trạng thái 200 OK mà không thực hiện điều hướng
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
