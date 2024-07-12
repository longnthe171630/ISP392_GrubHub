/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;
import model.Customer;
import dao.AccountDAO;
import dao.CustomerDAO;
import dao.RestaurantDAO;

/**
 *
 * @author phaml
 */
@WebServlet(name="VerifyEmailCustomerServlet", urlPatterns={"/verifyemailcustomer"})
public class VerifyEmailCustomerServlet extends HttpServlet {
   


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        AccountDAO accountDAO = new AccountDAO();
        CustomerDAO cd = new CustomerDAO();
        
        RestaurantDAO rd = new RestaurantDAO();
        String email = request.getParameter("email");
        String token = request.getParameter("token");
        if (email == null || token == null || email.isEmpty() || token.isEmpty()) {
            response.sendRedirect("registercustomer.jsp");
            return;
        }
        String verifiedEmail = accountDAO.checkToken(email, token);
        if (verifiedEmail != null) {
            Account a= accountDAO.getAccountByEmail_VuPL(email);
            a.setActive(1);
            accountDAO.ResetToken(a.getId());
            accountDAO.updateAccount(a);
            request.setAttribute("msg", "Successful account registration");
            request.getRequestDispatcher("LoginCus.jsp");
        } else{
            Account a= accountDAO.getAccountByEmail_VuPL(email);
            cd.deleteCustomerByAccountId(a.getId());
            rd.deleteRestaurantByAccountId(a.getId());
            
            accountDAO.deleteAcountById(a.getId()); 
        }
        response.sendRedirect("LoginCus.jsp");
        
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    }

}
