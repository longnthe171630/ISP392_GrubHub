/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CustomerDAO;
import dao.*;
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
import model.*;
import utils.Mail;

/**
 *
 * @author phaml
 */
@WebServlet(name = "RestaurantOrderDetailServlet", urlPatterns = {"/restauranorderdetail"})
public class RestaurantOrderDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String customerIdParam = request.getParameter("customerId");
        String orderIdParam = request.getParameter("orderId");

        if (customerIdParam == null || orderIdParam == null) {
            response.sendRedirect("restaurantdashboard");
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
            OrderDAO dao = new OrderDAO();
            ProductDAO pd = new ProductDAO();
            AddressDAO ad = new AddressDAO();
            Order order = dao.getOrderById_VuPL(orderId);
            OrderDetailsDAO odd = new OrderDetailsDAO();
            List<OrderDetails> od = odd.getOrderDetails();
            List<OrderDetails> xOd = new ArrayList<>();
            List<Notification> ln = nd.getListNoti();
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
            request.setAttribute("account", acc);
            request.setAttribute("map", map);
            request.setAttribute("address", a);
            request.setAttribute("order", order);
            request.setAttribute("customer", customer);
            request.getRequestDispatcher("restaurantorderdetail.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        OrderDAO od = new OrderDAO();
        CustomerDAO cd = new CustomerDAO();
        RestaurantDAO rd = new RestaurantDAO();
        AccountDAO ad =new AccountDAO();
        String reasonReject = request.getParameter("reason");
        String action = request.getParameter("action");
        int orderId = Integer.parseInt((String)request.getParameter("order_id"));
        Order o = od.getOrderById_VuPL(orderId);
        Restaurant restaurant = rd.getResByID_VuPL(o.getRestaurant_id());
//        String email = cd.getCustomerByID(o.getCustomer_id()).getEmail();
        Customer cus = cd.getCustomerByID_VuPL(o.getCustomer_id());
        String email = ad.getAccountByID(cus.getAccountID()).getEmail();
        if ("accept".equalsIgnoreCase(action)) {
            o.setStatus("Đang chờ");
            od.updateStatusOrder(o);
            new Mail().sendEmail(email, "Cập nhật đơn hàng", "Đơn hàng của bạn được mua ngày: "+o.getOrder_date()+ " đang trong quá trình vận chuyển" );
            response.sendRedirect("restaurantdashboard");
        } else if ("reject".equalsIgnoreCase(action)) {
            o.setStatus("Từ chối");
            od.updateStatusOrder(o);
            new Mail().sendEmail(email, "Cập nhật đơn hàng", "Đơn hàng của bạn được mua ngày: "+o.getOrder_date()+ 
                        ". Đã bị từ chối bởi cửa hàng "+ restaurant.getName()+" với lý do: " +reasonReject );
            Notification no = new Notification(0, reasonReject, orderId);
            NotificationDAO nd = new NotificationDAO();
            nd.insetNotification(no);
            response.sendRedirect("restaurantdashboard");
        }
    }
}
