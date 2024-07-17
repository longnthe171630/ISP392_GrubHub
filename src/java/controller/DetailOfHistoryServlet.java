/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.AccountDAO;
import dao.AddressDAO;
import dao.CustomerDAO;
import dao.NotificationDAO;
import dao.OrderDAO;
import dao.OrderDetailsDAO;
import dao.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Account;
import model.Address;
import model.Customer;
import model.Notification;
import model.Order;
import model.OrderDetails;
import model.Product;

/**
 *
 * @author phaml
 */
@WebServlet(name="DetailOfHistoryServlet", urlPatterns={"/detailofhistory"})
public class DetailOfHistoryServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String customerIdParam = request.getParameter("idCustomer");
        String orderIdParam = request.getParameter("idOrder");

        if (customerIdParam == null || orderIdParam == null) {
            response.sendRedirect("historyrestaurant");
            return;
        }

        try {
            int cusId = Integer.parseInt(customerIdParam);
            int orderId = Integer.parseInt(orderIdParam);
            NotificationDAO nd = new NotificationDAO();
            AccountDAO accDAO = new AccountDAO();
            CustomerDAO cd = new CustomerDAO();
            Customer customer = cd.getCustomerByID_VuPL(cusId);
            Account acc = accDAO.getAccountByID(customer.getAccountID());
//            Customer customer = cd.getCustomerByID_VuPL(cusId);
            OrderDAO dao = new OrderDAO();
            ProductDAO pd = new ProductDAO();
            AddressDAO ad = new AddressDAO();
            Order order = dao.getOrderById_VuPL(orderId);
            OrderDetailsDAO odd = new OrderDetailsDAO();
            List<OrderDetails> od = odd.getOrderDetails();
            List<OrderDetails> xOd = new ArrayList<>();
            List<Notification> ln = nd.getListNoti();
            String no= null;
            for (Notification notification : ln) {
                if (notification.getOrder_id() == order.getId()) {
                    no = notification.getDescripsion();
                }
            }
            for (OrderDetails orderDetails : od) {
                if (orderDetails.getOrder_id() == orderId) {
                    xOd.add(orderDetails);
                }
            }
            Map<Product, Integer> map = new HashMap<>();
            for (OrderDetails orderDetails : xOd) {
                map.put(pd.getProductVu(orderDetails.getProduct_id()), orderDetails.getQuantity());
            }
            Address a = ad.getAddressById(acc.getAddressID());
            request.setAttribute("listnotification", ln);
            request.setAttribute("notification", no);
            request.setAttribute("map", map);
            request.setAttribute("address", a);
            request.setAttribute("account", acc);
            request.setAttribute("order", order);
            request.setAttribute("customer", customer);
            request.getRequestDispatcher("detailofhistory.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       
    }
}
