package dao;

import model.Cart;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import model.Address;
import model.CartItem;
import model.Category;
import model.Customer;
import model.Email;
import model.Order;
import model.Product;
import model.Restaurant;
import utils.Mail;

/**
 *
 * @author manh0
 */
public class OrderDAO extends MyDAO {

    private CategoryDAO cd = new CategoryDAO();
    private RestaurantDAO rt = new RestaurantDAO();

    public void addOrder(Customer customer, Cart cart) {
        String sql;
        ps = null;
        rs = null;

        try {
            // Start transaction
            con.setAutoCommit(false);

            // Insert order into 'Order' table
            LocalDate curDate = LocalDate.now();
            Date date = Date.valueOf(curDate); // Convert LocalDate to java.sql.Date

            sql = "INSERT INTO [Order] (restaurant_id, customer_id, total_amount, status, order_date) VALUES (?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql, ps.RETURN_GENERATED_KEYS);
            ps.setInt(1, 1); // Assuming restaurant_id is 1
            ps.setInt(2, customer.getId());
            ps.setDouble(3, cart.getTotalMoney());
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
            for (CartItem item : cart.getItems()) {
                ps.setInt(1, orderId);
                ps.setInt(2, item.getProduct().getId());
                ps.setInt(3, item.getQuantity());
                ps.setDouble(4, item.getPrice());
                System.out.println("Inserting order detail: " + ps.toString());
                ps.executeUpdate();
            }

            // Update product quantity
            sql = "UPDATE product SET quantity = quantity - ? WHERE id = ?";
            ps = con.prepareStatement(sql);
            for (CartItem item : cart.getItems()) {
                ps.setInt(1, item.getQuantity());
                ps.setInt(2, item.getProduct().getId());
                System.out.println("Updating product quantity: " + ps.toString());
                ps.executeUpdate();
            }

            // Commit transaction
            con.commit();

            sendOrderConfirmationEmail(customer, cart);

        } catch (SQLException e) {
            try {
                // Rollback transaction on error
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                // Restore auto-commit mode
                con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Clean up
            if (rs != null) try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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

    public List<Product> getTopOrderedProducts() {
        List<Product> topProducts = new ArrayList<>();
        String sql = """
                    SELECT TOP 5 p.id, p.name, p.price,p.quantity, p.rating,p.description,p.image,p.create_date,p.restaurant_id,p.category_id,p.status  ,SUM(od.quantity) AS total_quantity
                    FROM Orderdetails od
                    JOIN product p ON od.product_id = p.id
                    GROUP BY p.id, p.name, p.price,p.quantity, p.rating,p.description,p.image,p.create_date,p.restaurant_id,p.category_id,p.status
                    ORDER BY total_quantity DESC;""";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int xProductId = rs.getInt("id");
                String xName = rs.getString("name");
                int xPrice = rs.getInt("price");
                float xRating = rs.getFloat("rating");
                String xDescription = rs.getString("description");
                String xImage = rs.getString("image");
                boolean xStatus = rs.getBoolean("status");
                java.util.Date xCreate_date = rs.getDate("create_date");
                int xQuantity = rs.getInt("quantity");
                Restaurant s = rt.getRestaurantById(rs.getInt("restaurant_id"));
                Category c = cd.getCategoryId(rs.getInt("category_id"));

                Product x = new Product(xProductId, xName, xPrice, xQuantity, xDescription, xImage, xStatus, xRating, xCreate_date, s, c);
                topProducts.add(x);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return topProducts;
    }

    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
        OrderDAO dao1 = new OrderDAO();
        List<Product> list = dao1.getTopOrderedProducts();
        for (Product product : list) {
            System.out.println(product);
        }
    }
}
