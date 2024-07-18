/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAO;
import dao.AddressDAO;
import dao.RestaurantDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.Address;
import model.Restaurant;
import utils.Validate;

/**
 *
 * @author Admin
 */
public class EditResServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditResServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditResServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        AddressDAO ad = new AddressDAO();
        RestaurantDAO rd = new RestaurantDAO();
        AccountDAO accdao = new AccountDAO();
        String username = (String) session.getAttribute("username");
//        int resID = (int) session.getAttribute("ResID");
        String name = request.getParameter("name");
        String phonenumber = request.getParameter("phonenumber");
        String email = request.getParameter("email");
        String state = request.getParameter("state");
        String street = request.getParameter("street");
        String details = request.getParameter("details");
        int idAddress = 0;

        Validate v = new Validate();
        if (name == null || name.isEmpty() || email == null || email.isEmpty() || phonenumber.isEmpty() || phonenumber == null || state.isEmpty() || state == null || street.isEmpty() || street == null || details.isEmpty() || details == null) {
            String msg = "All fields must be filled out completely";
            request.setAttribute("alert", msg);
            request.getRequestDispatcher("loadres").forward(request, response);

            return;
        }
        Address add = new Address(details, state, street);

// Kiểm tra xem địa chỉ đã tồn tại trong cơ sở dữ liệu chưa
        Address existingAddress = ad.getAddress(add);

        if (existingAddress == null) {
            // Nếu địa chỉ chưa tồn tại, tạo nó trong cơ sở dữ liệu
            ad.createAddress(add);
            idAddress = ad.getAddress(add).getId(); // Lấy ID của địa chỉ vừa tạo
        } else {
            // Nếu địa chỉ đã tồn tại, sử dụng ID hiện tại của nó
            idAddress = existingAddress.getId();
        }

        if (!v.isValidEmail(email)) {
            String msg = "Email address has not been entered correctly";
            request.setAttribute("alert", msg);
            request.getRequestDispatcher("loadres").forward(request, response);
            return;
        } else if (!v.isValidPhone(phonenumber)) {
            String msg = "Phone number must be number with 10 charactors";
            request.setAttribute("alert", msg);
            request.getRequestDispatcher("loadres").forward(request, response);
            return;
        }
        try {
            Address newadd = ad.getAddressById(idAddress);
            request.setAttribute("address", newadd);

            int accID = accdao.getAccountID(username);
            Account a = accdao.getAccountByID(accID);
            a.setPhonenumber(phonenumber);
            a.setEmail(email);
            accdao.updateAccount(a);
            request.setAttribute("acc", a);
            

            Restaurant r = rd.getRestaurantByAccID_loc(accID);
            r.setName(name);
            rd.updateRestaurant(r);
            request.setAttribute("restaurant", r);

            request.getRequestDispatcher("EditRestaurant.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
           
        }

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
        processRequest(request, response);
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
