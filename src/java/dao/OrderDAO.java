package dao;

import model.Cart;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import model.Address;
import model.CartItem;
import model.Customer;
import model.Order;

/**
 *
 * @author manh0
 */
public class OrderDAO extends MyDAO {

    public List<Order> getOrder() {
        List<Order> t = new ArrayList<>();
        xSql = "select * from [Order]";
        int xId;
        int xRestaurant_Id;
        int xDelivery_Id;
        int xCustomer_Id;
        int xTotal_amount;
        String xStatus;
        java.sql.Date xOrder_date;
        Order x;
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                xId = rs.getInt("id");
                xRestaurant_Id = rs.getInt("restaurant_id");
                xCustomer_Id = rs.getInt("customer_id");
                xDelivery_Id = rs.getInt("delivery_id");
                xTotal_amount = rs.getInt("total_amount");
                xStatus = rs.getString("status");
                xOrder_date = rs.getDate("order_date");

                x = new Order(xId, xRestaurant_Id, xCustomer_Id, xDelivery_Id, xTotal_amount, xStatus, xOrder_date);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public int getNumberOrders() {
        try {
            String sql = "SELECT COUNT(*) FROM Orders";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                int number = rs.getInt(1);
                return number;
            }
        } catch (Exception e) {
        }
        return 1;
    }

    public void addOrder(Customer u, Cart cart) {
        xSql = "insert to [order](customer_id,total_amount,order_date) values [?, ? , ?]";
        LocalDate curDate = LocalDate.now();
        Date date = Date.valueOf(curDate); // Convert LocalDate to java.sql.Date

        try {
            ps = con.prepareStatement(xSql);
            ps.setDate(1, date);
            ps.setInt(2, u.getId());
            ps.setDouble(3, cart.getTotalMoney());
            ps.executeUpdate();

            xSql = "select top 1 id from [Order] order by id desc";
            ps = con.prepareStatement(xSql);
            ps.executeQuery();
            if (rs.next()) {
                int oid = rs.getInt(1);
                for (CartItem i : cart.getItems()) {
                    xSql = "insert to [OrderDetails](order_id,product_id,quantity,price) values(?, ?, ?, ?)";
                    ps = con.prepareStatement(xSql);
                    ps.setInt(1, oid);
                    ps.setInt(2, i.getProduct().getId());
                    ps.setInt(3, i.getQuantity());
                    ps.setDouble(4, i.getPrice());
                    ps.executeUpdate();

                }
            }
            xSql = "update product set quantity=quantity-? where product_id = ?";
            ps = con.prepareStatement(xSql);
            for (CartItem i : cart.getItems()) {
                ps.setInt(1, i.getQuantity());
                ps.setInt(1, i.getProduct().getId());
                ps.executeUpdate();
            }

        } catch (Exception e) {
        }
    }

    public List<Order> getAddressRestaurant_CustomerWithId() {
        List<Order> orders = new ArrayList<>();
        String xSql = "SELECT o.id, r_address.details AS from_details, r_address.street AS from_street, r_address.state AS from_state,\n"
                + "c_address.details AS to_details, c_address.street AS to_street, c_address.state AS to_state,\n"
                + "o.total_amount,\n"
                + "o.status, \n"
                + "o.order_date\n"
                + "FROM\n"
                + "[Order] o\n"
                + "JOIN\n"
                + "customer c ON o.customer_id = c.id\n"
                + "JOIN\n"
                + "address c_address ON c.address_id = c_address.id\n"
                + "JOIN \n"
                + "restaurant r ON o.restaurant_id = r.id\n"
                + "JOIN\n"
                + "address r_address ON r.address_id = r_address.id;";

        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String fromDetails = rs.getString("from_details");
                String fromStreet = rs.getString("from_street");
                String fromState = rs.getString("from_state");
                String toDetails = rs.getString("to_details");
                String toStreet = rs.getString("to_street");
                String toState = rs.getString("to_state");
                int total_Amount = rs.getInt("total_amount");
                String status = rs.getString("status");
                java.sql.Date orderDate = rs.getDate("order_date");

                Address fromAddress = new Address(fromDetails, fromStreet, fromState);
                Address toAddress = new Address(toDetails, toStreet, toState);
                Order order = new Order(id, total_Amount, status, orderDate, fromAddress, toAddress);
                orders.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return orders;
    }

    public Order getOrderById(int id) {
        xSql = "SELECT \n"
                + "    o.id, \n"
                + "    r_address.details AS from_details, r_address.street AS from_street, r_address.state AS from_state,\n"
                + "    c_address.details AS to_details, c_address.street AS to_street, c_address.state AS to_state,\n"
                + "    o.total_amount, \n"
                + "    o.status, \n"
                + "    o.order_date\n"
                + "FROM \n"
                + "    [Order] o \n"
                + "JOIN \n"
                + "    customer c ON o.customer_id = c.id\n"
                + "JOIN \n"
                + "    address c_address ON c.address_id = c_address.id\n"
                + "JOIN \n"
                + "    restaurant r ON o.restaurant_id = r.id\n"
                + "JOIN \n"
                + "    address r_address ON r.address_id = r_address.id\n"
                + "where o.id = ?";
        Order x = null;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int xId = rs.getInt("id");
                int xTotal_amount = rs.getInt("total_amount");
                String xStatus = rs.getString("status");
                java.sql.Date xOrder_date = rs.getDate("order_date");
                String fromDetails = rs.getString("from_details");
                String fromStreet = rs.getString("from_street");
                String fromState = rs.getString("from_state");
                String toDetails = rs.getString("to_details");
                String toStreet = rs.getString("to_street");
                String toState = rs.getString("to_state");

                Address fromAddress = new Address(fromDetails, fromStreet, fromState);
                Address toAddress = new Address(toDetails, toStreet, toState);
                x = new Order(xId, xTotal_amount, xStatus, xOrder_date, fromAddress, toAddress);

            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }

    public static void main(String[] args) {
        OrderDAO d = new OrderDAO();
//        List<Order> lo = d.getOrder();
//        if (lo == null) {
//            System.out.println("List empty");
//        } else {
//            for (Order o : lo) {
//                System.out.println(o);
//
//            }
//        }
        d.getAddressRestaurant_CustomerWithId();
        d.getOrderById(1);
    }
}
