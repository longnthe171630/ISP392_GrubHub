/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.*;
import utils.Validate;

/**
 *
 * @author phaml
 */
@WebServlet(name = "RegisterCustomerServlet", urlPatterns = {"/registercustomer"})
public class RegisterCustomerServlet extends HttpServlet {

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
            out.println("<title>Servlet RegisterCustomerServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterCustomerServlet at " + request.getContextPath() + "</h1>");
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
        Validate v = new Validate();
        CustomerDAO cd = new CustomerDAO();

        String name = request.getParameter("name");

        String userName = request.getParameter("username");

        String email = request.getParameter("email");

        String phoneNumber = request.getParameter("phonenumber");

        String passWord = request.getParameter("password");

        String cPassWord = request.getParameter("cpassword");

        String gender = request.getParameter("gender");

        String dob = request.getParameter("dob");

        String address = request.getParameter("address");

        boolean genderValue = gender != null && gender.equals("male");
//        java.sql.Date dobDate = null;
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // checck email
//        if (v.isValidEmail(email)) {
//            String msg = "Your email is not right";
//            request.setAttribute("msg", msg);
//            request.getRequestDispatcher("registercustomer.jsp").forward(request, response);
//        }
        //check phoneNumber
//        if (v.isValidPhone(phoneNumber)) {
//            String msg = "Your phone number is not right";
//            request.setAttribute("msg", msg);
//            request.getRequestDispatcher("registercustomer.jsp").forward(request, response);
//        }
        Customer cus = new Customer();
        cus = cd.checkCustomer(userName, passWord);
        if (cus == null && passWord.equals(cPassWord)) {
            cus = new Customer(name, userName, passWord, email, phoneNumber, dob, genderValue, 1);
            try {
                cd.insertCustomer(cus);
            } catch (Exception e) {
                String msg = "Complete";
                request.setAttribute("msg", msg);
                request.getRequestDispatcher("registercustomer.jsp").forward(request, response);
                return;
            }
            String msg = "Complete";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("registercustomer.jsp").forward(request, response);
            response.sendRedirect("registercustomer.jsp");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Validate v = new Validate();
        CustomerDAO cd = new CustomerDAO();
        AddressDAO ad = new AddressDAO();

        String name = request.getParameter("name");
        String userName = request.getParameter("username");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phonenumber");
        String passWord = request.getParameter("password");
        String cPassWord = request.getParameter("cpassword");
        String gender = request.getParameter("gender");
        String dob = request.getParameter("dob");
        String state = request.getParameter("state");
        String street = request.getParameter("street");
        String detailAddress = request.getParameter("detailaddress");
        int idAddress = 0;

        boolean genderValue = !"male".equalsIgnoreCase(gender);

        if (name == null || userName == null || email == null
                || phoneNumber == null || passWord == null || cPassWord == null
                || gender == null || dob == null || state == null || street == null
                || detailAddress == null) {
            String msg = "All fields must be filled out completely";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("registercustomer.jsp").forward(request, response);
            return;
        }
        if (!v.isValidEmail(email)) {
            String msg = "Email address has not been entered correctly";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("registercustomer.jsp").forward(request, response);
            return;
        }
        if (!v.isValidPhone(phoneNumber)) {
            String msg = "Phone number must be number with 10 charactors";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("registercustomer.jsp").forward(request, response);
            return;
        }

        if (!passWord.equals(cPassWord)) {
            String msg = "Passwords do not match.";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("registercustomer.jsp").forward(request, response);
            return;
        }

        Address add = new Address(detailAddress, state, street);

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

        Customer cus = cd.checkCustomer(userName, passWord);
        if (cus.getEmail() != null) {
            String msg = "Your email account is already in use.";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("registercustomer.jsp").forward(request, response);
        }
        if (cus == null) {
            Customer newCus = new Customer(name, userName, passWord, email, phoneNumber, dob, genderValue, idAddress);
            try {
                cd.insertCustomer(newCus);
                String msg = "Registration successfully.";
                request.setAttribute("msg", msg);
                request.getRequestDispatcher("registercustomer.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();  // Log the exception for debugging
                String msg = "Error during registration.";
                request.setAttribute("msg", msg);
                request.getRequestDispatcher("registercustomer.jsp").forward(request, response);
            }
        } else {
            String msg = "User already exists.";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("registercustomer.jsp").forward(request, response);
        }
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
