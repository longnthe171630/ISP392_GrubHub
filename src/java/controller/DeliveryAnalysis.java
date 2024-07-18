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
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import model.Account;
import model.Delivery;
import model.Notification;

/**
 *
 * @author Long1
 */
@WebServlet(name = "DeliveryAnalysis", urlPatterns = {"/deliveryanalysis"})
public class DeliveryAnalysis extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Lấy data bằng session
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("acc");

        //Lấy dữ liệu từ DAO
        NotificationDAO dao1 = new NotificationDAO();
        DeliveryDAO dao = new DeliveryDAO();
        //Lấy info của account login vào
        int id = dao.getDeliveryPersonIdByUsername(account.getUsername());
        List<Notification> notice = dao1.getListNotification(id);
        
        int totalDone = dao.totalDoneByDeliveryPersonId(id);
        int totalCancel = dao.totalCancelByDeliveryPersonId(id);

        double successRate = 0;
        double failureRate = 0;

        // Kiểm tra tổng số đơn hàng
        if ((totalDone + totalCancel) != 0) {
            successRate = (double) totalDone / (totalDone + totalCancel) * 100;
            failureRate = (double) totalCancel / (totalDone + totalCancel) * 100;
        } else {
            // Nếu không có đơn hàng, có thể gán giá trị mặc định
            successRate = 0;
            failureRate = 0;
        }

        DecimalFormat df = new DecimalFormat("#.#"); // Định dạng một chữ số thập phân
        String formattedSuccessRate = df.format(successRate);
        String formattedFailureRate = df.format(failureRate);

        int time1 = dao.getTotalDeliveryTimeSuccess(id);
        int time2 = dao.getTotalDeliveryTimeFailed(id);

        String labels1 = dao.getDeliveryDatesSuccess(id);
        String labels2 = dao.getDeliveryDatesFailure(id);
        List<Integer> data1 = dao.getDeliveryTimesSuccess(id);
        List<Integer> data2 = dao.getDeliveryTimesFailure(id);

        int avgTimeSuccess = 0;
        int avgTimeFailure = 0;
        int avgTimeAll = 0;
        if (totalDone != 0) {
            avgTimeSuccess = time1 / totalDone;
        }

        if (totalCancel != 0) {
            avgTimeFailure = time2 / totalCancel;
        }

        if ((totalDone + totalCancel) != 0) {
            avgTimeAll = (time1 + time2) / (totalDone + totalCancel);
        }

        int[] deliveryTimes = dao.getDeliveryMaxMin(id);
        
        request.setAttribute("notice", notice);
        request.setAttribute("totaldone", totalDone);
        request.setAttribute("totalcancel", totalCancel);
        request.setAttribute("time1", time1);
        request.setAttribute("time2", time2);
        request.setAttribute("formattedSuccessRate", formattedSuccessRate);
        request.setAttribute("formattedFailureRate", formattedFailureRate);
        request.setAttribute("time2", time2);
        request.setAttribute("labels1", labels1);
        request.setAttribute("labels2", labels2);
        request.setAttribute("data1", data1);
        request.setAttribute("data2", data2);
        request.setAttribute("avgTimeSuccess", avgTimeSuccess);
        request.setAttribute("avgTimeFailure", avgTimeFailure);
        request.setAttribute("avgTimeAll", avgTimeAll);
        request.setAttribute("minDeliveryTime", deliveryTimes[0]);
        request.setAttribute("maxDeliveryTime", deliveryTimes[1]);

        request.getRequestDispatcher("deliveryanalysis.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String status = request.getParameter("status");
        //Lấy data bằng session
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("acc");

        //Lấy dữ liệu từ DAO
        DeliveryDAO dao = new DeliveryDAO();
        //Lấy info của account login vào
        int id = dao.getDeliveryPersonIdByUsername(account.getUsername());
        List<Delivery> list;
        if (status.equals("success")) {
            list = dao.getDeliverySuccess(id);
        } else {
            list = dao.getDeliveryFailure(id);
        }
        request.setAttribute("list", list);
        request.getRequestDispatcher("deliveryanalysisdetails.jsp").forward(request, response);
    }

}