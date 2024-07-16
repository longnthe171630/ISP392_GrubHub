/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAO;
import dao.AddressDAO;
import dao.CustomerDAO;
import model.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.Address;
import utils.Validate;

/**
 *
 * @author Admin
 */
public class EditServlet extends HttpServlet {

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
            out.println("<title>Servlet EditServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditServlet at " + request.getContextPath() + "</h1>");
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
        String name = request.getParameter("name");
        String userNameSess = (String) session.getAttribute("username");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String phonenumber = request.getParameter("phonenumber");
        String dob = request.getParameter("dob");
        String genderString = request.getParameter("gender");
        String details = request.getParameter("details");
        String state = request.getParameter("state");
        String street = request.getParameter("street");
        int idAddress = 0;
        
        boolean gender = false;
        Validate v = new Validate();
        if (email == null || email.isEmpty() || phonenumber.isEmpty() || phonenumber == null || dob == null || details.isEmpty() ||details==null||state.isEmpty()||state==null|| street.isEmpty()|| street==null){
            String msg = "All fields must be filled out completely";
            request.setAttribute("alert", msg);
            request.getRequestDispatcher("load").forward(request, response);
            return;
        } 
        if (!v.isValidEmail(email)) {
            String msg = "Email address has not been entered correctly";
            request.setAttribute("alert", msg);
            request.getRequestDispatcher("load").forward(request, response);
            return;
        } else if (!v.isValidPhone(phonenumber)) {
            String msg = "Phone number must be number with 10 charactors";
            request.setAttribute("alert", msg);
            request.getRequestDispatcher("load").forward(request, response);
            return;
        }

        if (genderString != null) {
            gender = genderString.equalsIgnoreCase("Female");
            request.getRequestDispatcher("load").forward(request, response);
        }
        AddressDAO ad = new AddressDAO();
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
        try {
            //lấy ID acc
             AccountDAO accdao = new AccountDAO();
             int accID = accdao.getAccountID(userNameSess);
            
            Address newadd = ad.getAddressById(idAddress);
            CustomerDAO dao = new CustomerDAO();
           
            
//            Customer c = dao.getCustomer(username);
//            c.setEmail(email);
//            c.setPhoneNumber(phonenumber);
//            c.setDob(dob);
//            c.setAddress_id(idAddress);
//            c.setGender(gender);
            
            Account a = accdao.getAccountByID(accID);
            a.setEmail(email);
            a.setPhonenumber(phonenumber);
            a.setAddressID(idAddress);
            accdao.updateAccount(a);
            
            Customer c = dao.getCustomerByAccID(accID);
            c.setName(name);
            c.setDob(dob);
            c.setGender(gender);
            dao.updateCustomer(c);
            
            request.setAttribute("acc", a);
            request.setAttribute("address", newadd);
            request.setAttribute("customer", c);
            request.getRequestDispatcher("Showinfo.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
