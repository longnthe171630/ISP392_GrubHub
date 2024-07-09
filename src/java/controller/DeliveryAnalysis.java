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
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Account;
import model.Delivery;

/**
 *
 * @author Long1
 */
@WebServlet(name="DeliveryAnalysis", urlPatterns={"/deliveryanalysis"})
public class DeliveryAnalysis extends HttpServlet {
   

    
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
        
        int totalShip = dao.totalShipPriceByDeliveryPersonId(id);
        int totalDone = dao.totalDoneByDeliveryPersonId(id);
        int totalDelivery = dao.totalDeliveryByDeliveryPersonId(id);
        int totalCancel = dao.totalCancelByDeliveryPersonId(id);
        
        request.setAttribute("totaldone", totalDone);
        request.setAttribute("totalship", totalShip);
        request.setAttribute("totaldelivery", totalDelivery);
        request.setAttribute("totalcancel", totalCancel);
        
        request.getRequestDispatcher("deliveryanalysis.jsp").forward(request, response);
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String status = request.getParameter("status");
        DeliveryDAO dao = new DeliveryDAO();
        
        List<Delivery> list;
        if(status.equals("success")){
            list = dao.getDeliverySuccess();
        }else{
            list = dao.getDeliveryFailure();
        }
        request.setAttribute("list", list);
        request.getRequestDispatcher("deliveryanalysisdetails.jsp").forward(request, response);
    }

}
