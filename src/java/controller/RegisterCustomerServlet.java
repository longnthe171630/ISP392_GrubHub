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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import model.*;
import utils.Mail;
import utils.Token;
import utils.Validate;
import java.util.*;

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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int role = 2;
        Validate v = new Validate();
        CustomerDAO cd = new CustomerDAO();
        AccountDAO accDAO = new AccountDAO();

        AddressDAO ad = new AddressDAO();
        // lấy dữ liệu ở form trang register
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
        if (!v.isValidUsername(userName)) {
            String msg = "Re-enter the username";
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
        // Kiểm tra ngày sinh có phải là ngày trong tương lai không

        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Date dateOfBirth = dateFormat.parse(dob);
            Date currentDate = new Date();
            if (dateOfBirth.after(currentDate)) {
                // Gửi thông báo lỗi nếu ngày sinh là ngày trong tương lai
                String msg = "Date of birth cannot be a future date.";
                request.setAttribute("msg", msg);
                request.getRequestDispatcher("registercustomer.jsp").forward(request, response);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý nếu có lỗi xảy ra trong quá trình chuyển đổi ngày tháng
            String msg = "Error processing date of birth.";
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

        Account acc = accDAO.checkAccount(userName, passWord);
        if (accDAO.getAccountByEmail(email) != null) {
            String msg = "Your email account is already in use.";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("registercustomer.jsp").forward(request, response);
        }
        if (acc == null) {
            Account newAcc = new Account(userName, passWord, email, phoneNumber, role, idAddress, 0, null, null);
            try {

                accDAO.addNewAccount(newAcc);
                Customer cus = new Customer(name, dob, genderValue, accDAO.getIDLastAcc());
                String token = new Token().generateRandomToken(18);
                String url = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/verifyemailcustomer?email=" + email + "&token=" + token;
                accDAO.insertToken(accDAO.getIDLastAcc(), token);
                new Mail().sendEmail(email, "Verify email", "Click here to verify email: " + url);
                cd.addNewCus(cus);
                String url1 = "registercustomer.jsp";
                String msg = "An email was sent. Please check your email!";
                request.setAttribute("msg", msg);
                request.setAttribute("acc", newAcc);
                request.getRequestDispatcher(url1).forward(request, response);
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

}
