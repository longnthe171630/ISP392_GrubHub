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
import java.util.Calendar;
import java.util.Map;

/**
 *
 * @author phaml
 */
@WebServlet(name = "RevenueServlet", urlPatterns = {"/revenue"})
public class RevenueServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int restaurantId = 1;

        // Get parameters from request
        String startMonthParam = request.getParameter("startMonth");
        String endMonthParam = request.getParameter("endMonth");
        String yearParam = request.getParameter("year");

        // Define default values for the last 6 months
        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH) + 1; // Months are 0-based
        int currentYear = calendar.get(Calendar.YEAR);

        int startMonth;
        int endMonth;
        int year;

        if (startMonthParam == null || endMonthParam == null || yearParam == null) {
            // Default to the last 6 months
            startMonth = currentMonth - 5;
            endMonth = currentMonth;
            year = currentYear;

            // Adjust for year transition
            if (startMonth <= 0) {
                startMonth += 12;
                year -= 1;
            }
        } else {
            startMonth = Integer.parseInt(startMonthParam);
            endMonth = Integer.parseInt(endMonthParam);
            year = Integer.parseInt(yearParam);
        }

        OrderDAO od = new OrderDAO();
        Map<String, Integer> revenueMap = od.getRevenueForPeriod(startMonth, endMonth, year, restaurantId);

        request.setAttribute("revenueMap", revenueMap);
        request.setAttribute("startMonth", startMonth);
        request.setAttribute("endMonth", endMonth);
        request.setAttribute("year", year);
        request.getRequestDispatcher("revenue.jsp").forward(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
