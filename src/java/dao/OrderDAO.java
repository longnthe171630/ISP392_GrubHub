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
import model.Email;
import model.Order;
import model.Product;
import utils.Mail;

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
                xTotal_amount = rs.getInt("total_amount");
                xStatus = rs.getString("status");
                xOrder_date = rs.getDate("order_date");

                x = new Order(xId, xRestaurant_Id, xCustomer_Id, xTotal_amount, xStatus, xOrder_date);
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
        String xSql;
        ps = null;

        try {

            // Insert order into 'order' table
            LocalDate curDate = LocalDate.now();
            Date date = Date.valueOf(curDate); // Convert LocalDate to java.sql.Date

            xSql = "insert into [Order] (restaurant_id, customer_id, total_amount,status, order_date ) values (?, ?, ?, ?, ?)";
            ps = con.prepareStatement(xSql);
            ps.setInt(1, 1);
            ps.setInt(2, u.getId());
            ps.setDouble(3, cart.getTotalMoney());
            ps.setString(4, "Đang xử lí");
            ps.setDate(5, date);

            ps.executeUpdate();

            // Retrieve the generated order ID
            xSql = "select top 1 id from [Order] order by id desc";
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            int oid = 0;
            if (rs.next()) {
                oid = rs.getInt(1);
            }
            rs.close();

            // Insert order details into 'orderdetails' table
            xSql = "insert into Orderdetails (order_id, product_id, quantity, price) values (?, ?, ?, ?)";
            ps = con.prepareStatement(xSql);
            for (CartItem i : cart.getItems()) {
                ps.setInt(1, oid);
                ps.setInt(2, i.getProduct().getId());
                ps.setInt(3, i.getQuantity());
                ps.setDouble(4, i.getPrice());
                ps.executeUpdate();
            }

            // Update product quantity
            xSql = "update product set quantity = quantity - ? where id = ?";
            ps = con.prepareStatement(xSql);
            for (CartItem i : cart.getItems()) {
                ps.setInt(1, i.getQuantity());
                ps.setInt(2, i.getProduct().getId());
                ps.executeUpdate();
            }
          sendOrderConfirmationEmail(u, cart);
        } catch (SQLException e) {
            e.printStackTrace(); // Log or handle SQLException
        }
    }

    private void sendOrderConfirmationEmail(Customer customer, Cart cart) {
        String customerEmail = customer.getEmail();
        String subject = "Your order confirmation";
        String message = "Thank you for your order. Your order total is: " + cart.getTotalMoney();

        Mail mail = new Mail();
        boolean emailSent = mail.sendEmail1(customerEmail, subject, message);

        if (emailSent) {
            System.out.println("Order confirmation email sent successfully.");
        } else {
            System.out.println("Failed to send order confirmation email.");
        }
    }

//
//    public static void main(String[] args) {
//        OrderDAO orderDAO = new OrderDAO();
//        ProductDAO dao = new ProductDAO();
//
//        // Assume that the ProductDAO can retrieve products from the database
//        List<Product> productList = dao.getProducts();
//
//        // Select a product from the productList to add to the cart
//        Product product = productList.get(0); // Select the first product for simplicity
//
//        // Create a CartItem
//        CartItem cartItem = new CartItem(product, 1, product.getPrice()); // Example: quantity = 1
//
//        // Create a Cart and add the CartItem to it
//        Cart cart = new Cart();
//        cart.addItem(cartItem);
//
//        Customer customer = new Customer();
//        customer.setId(1); // Set ID of the customer
//        customer.setEmail("manh07051@gmail.com"); // Set email of the customer
//
//        // Call the addOrder method and pass the customer and cart
//        orderDAO.addOrder(customer, cart);
//    }

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
                + "address r_address ON r.address_id = r_address.id where o.status = N'Đang chờ'";

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

    public List<Order> getOrderByStatus() {
        List<Order> t = new ArrayList<>();
        xSql = "SELECT * FROM [Order] WHERE [status] IN (N'Đang chờ');";
        Order x;
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int xId = rs.getInt("id");
                int xRestaurant_Id = rs.getInt("restaurant_id");
                int xCustomer_Id = rs.getInt("customer_id");
                int xTotal_amount = rs.getInt("total_amount");
                String xStatus = rs.getString("status");
                java.sql.Date xOrder_date = rs.getDate("order_date");

                x = new Order(xId, xRestaurant_Id, xCustomer_Id, xTotal_amount, xStatus, xOrder_date);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }
    
    public static void main(String[] args) {
        OrderDAO d = new OrderDAO();
        List<Order> lo = d.getAddressRestaurant_CustomerWithId();
        if (lo == null) {
            System.out.println("List empty");
        } else {
            for (Order o : lo) {
                System.out.println(o);

            }
        }
//        d.getAddressRestaurant_CustomerWithId();
//        d.getOrderById(1);
    }
}