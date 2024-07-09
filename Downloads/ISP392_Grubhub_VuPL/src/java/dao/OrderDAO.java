package dao;

import model.Cart;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.Account;
import model.Address;
import model.CartItem;
import model.Customer;
import model.Notification;
import model.Order;
import model.Product;
import utils.Mail;

/**
 *
 * @author manh0
 */
public class OrderDAO extends MyDAO {

    public Map<String, Double> getMonthlyRevenue() {
        Map<String, Double> revenue = new HashMap<>();
        String sql = """
                     SELECT FORMAT(order_date, 'yyyy-MM') AS month, SUM(total_amount) AS revenue
                     FROM [Order]
                     GROUP BY FORMAT(order_date, 'yyyy-MM')
                     ORDER BY FORMAT(order_date, 'yyyy-MM')""";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                revenue.put(rs.getString("month"), rs.getDouble("revenue"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return revenue;
    }

    public double totalMoneyMonth(int month, int year) {
        String sql = """
                     select SUM(total_amount) from [Order]
                                     where MONTH(order_date)= ? and year(order_date)= ?""";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, month);
            ps.setInt(2, year);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

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

    public List<Order> getListOrder() {
        List<Order> list = new ArrayList<>();
        try {
            String sql = """
                        SELECT 
                             o.id AS id,
                             r.id AS restaurant_id,
                             c.id AS customer_id,
                             o.total_amount,
                             o.status,
                             o.order_date
                         FROM 
                             [Order] AS o
                         JOIN 
                             Restaurant AS r ON r.id = o.restaurant_id
                         JOIN 
                             Customer AS c ON c.id = o.customer_id
                         order by order_date desc""";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int orderId = rs.getInt("id");
                int restaurantName = rs.getInt("restaurant_id");
                int customerName = rs.getInt("customer_id");
                int totalAmount = rs.getInt("total_amount");
                String status = rs.getString("status");
                Date orderDate = rs.getDate("order_date");

                Order a = new Order(orderId, restaurantName, customerName, totalAmount, status, orderDate);
                list.add(a);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            // Close resources
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return list;
    }

    public void addOrder(Customer customer, Cart cart) {
        String sql;
        ps = null;
        rs = null;

        try {
            // Start transaction
            con.setAutoCommit(false);

            // Group items by restaurant
            Map<Integer, List<CartItem>> itemsByRestaurant = new HashMap<>();
            for (CartItem item : cart.getItems()) {
                Product product = item.getProduct();
                if (product != null && product.getRestaurantId() != 0) {
                    int restaurantId = product.getRestaurantId();
                    itemsByRestaurant.computeIfAbsent(restaurantId, k -> new ArrayList<>()).add(item);
                } else {
                    System.out.println("Invalid product or restaurant: " + product);

                }
            }

            // Process each group of items
            for (Map.Entry<Integer, List<CartItem>> entry : itemsByRestaurant.entrySet()) {
                int restaurantId = entry.getKey();
                List<CartItem> items = entry.getValue();

                // Calculate total amount for this order
                double totalAmount = items.stream().mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity()).sum();

                // Log the calculated total amount for debugging
                System.out.println("Total amount for restaurant ID " + restaurantId + ": " + totalAmount);

                // Insert order into 'Order' table
                LocalDate curDate = LocalDate.now();
                Date date = Date.valueOf(curDate); // Convert LocalDate to java.sql.Date

                sql = "INSERT INTO [Order] (restaurant_id, customer_id, total_amount, status, order_date) VALUES (?, ?, ?, ?, ?)";
                ps = con.prepareStatement(sql, ps.RETURN_GENERATED_KEYS);
                ps.setInt(1, restaurantId);
                ps.setInt(2, customer.getId());
                ps.setDouble(3, totalAmount);
                ps.setString(4, "Đang xử lí");
                ps.setDate(5, date);

                System.out.println("Executing query: " + ps.toString());
                ps.executeUpdate();

                // Retrieve the generated order ID
                rs = ps.getGeneratedKeys();
                int orderId = 0;
                if (rs.next()) {
                    orderId = rs.getInt(1);
                }

                // Insert order details into 'Orderdetails' table
                sql = "INSERT INTO Orderdetails (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
                ps = con.prepareStatement(sql);
                for (CartItem item : items) {
                    ps.setInt(1, orderId);
                    ps.setInt(2, item.getProduct().getId());
                    ps.setInt(3, item.getQuantity());
                    ps.setDouble(4, item.getProduct().getPrice());
                    System.out.println("Inserting order detail: " + ps.toString());
                    ps.executeUpdate();
                }

                // Update product quantity
                sql = "UPDATE product SET quantity = quantity - ? WHERE id = ?";
                ps = con.prepareStatement(sql);
                for (CartItem item : items) {
                    ps.setInt(1, item.getQuantity());
                    ps.setInt(2, item.getProduct().getId());
                    System.out.println("Updating product quantity: " + ps.toString());
                    ps.executeUpdate();
                }
            }

            // Commit transaction
            con.commit();

            // Send order confirmation email
            Mail.sendOrderConfirmationEmail(customer, cart);

        } catch (SQLException e) {
            try {
                // Rollback transaction on error
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                // Restore auto-commit mode
                if (con != null) {
                    con.setAutoCommit(true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Clean up
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Notification> getNotificationsByAccountId(int accountId) {
        List<Notification> notifications = new ArrayList<>();
        String sql = "SELECT * FROM Notifications WHERE account_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, accountId);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String desciption = rs.getString("description");
                int order_id = rs.getInt("order_id");
                notifications.add(new Notification(id, desciption, accountId, order_id));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return notifications;
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

    public Order getOrderById_VuPL(int id) {
        xSql = "SELECT * FROM [order] WHERE id = ?";
        Order o = null;

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                int xId = rs.getInt("id");
                int xRestaurant_Id = rs.getInt("restaurant_id");
                int xCustomer_Id = rs.getInt("customer_id");
                int xTotal_amount = rs.getInt("total_amount");
                String xStatus = rs.getString("status");
                java.sql.Date xOrder_date = rs.getDate("order_date");

                o = new Order(xId, xRestaurant_Id, xCustomer_Id, xTotal_amount, xStatus, xOrder_date);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return o;
    }

    public void updateStatusOrder(Order o) {
        xSql = "UPDATE [dbo].[Order]\n"
                + "   SET\n"
                + "      [status] = ?\n"
                + " WHERE id =?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, o.getStatus());
            ps.setInt(2, o.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Khởi tạo khách hàng
        OrderDAO orderDAO = new OrderDAO();
        double total = orderDAO.totalMoneyMonth(7, 2024);
        System.out.println(total);

    }
}