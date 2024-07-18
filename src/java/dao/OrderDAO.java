package dao;

import java.security.Timestamp;
import model.Cart;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Account;
import model.Address;
import model.CartItem;
import model.Customer;
import model.Notification;
import model.Order;
import model.OrderDetails;
import model.Product;
import utils.Mail;

/**
 *
 * @author manh0
 */
public class OrderDAO extends MyDAO {

    private RestaurantDAO res = new RestaurantDAO();

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
        java.sql.Timestamp xOrder_date;
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
                xOrder_date = rs.getTimestamp("order_date");

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
                java.sql.Timestamp orderDate = rs.getTimestamp("order_date");

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

    public Order addOrder(Customer customer, Cart cart) {
        String sql;
        ps = null;
        rs = null;
        Order order = new Order();
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
                // Lấy ngày hiện tại
                LocalDate curDate = LocalDate.now();
                // Chuyển đổi LocalDate thành LocalDateTime (thêm thời gian 00:00:00)
                LocalDateTime dateTime = curDate.atStartOfDay();
                // Chuyển đổi LocalDateTime thành Timestamp
                java.sql.Timestamp date = java.sql.Timestamp.valueOf(dateTime); // Convert LocalDate to java.sql.Date

                sql = "INSERT INTO [Order] (restaurant_id, customer_id, total_amount, status, order_date) VALUES (?, ?, ?, ?, ?)";
                ps = con.prepareStatement(sql, ps.RETURN_GENERATED_KEYS);
                ps.setInt(1, restaurantId);
                ps.setInt(2, customer.getId());
                ps.setDouble(3, totalAmount);
                ps.setString(4, "Đang xử lí");
                ps.setTimestamp(5, date);

                System.out.println("Executing query: " + ps.toString());
                ps.executeUpdate();

                // Retrieve the generated order ID
                rs = ps.getGeneratedKeys();
                int orderId = 0;
                if (rs.next()) {
                    orderId = rs.getInt(1);
                    order = new Order(orderId, restaurantId, customer.getId(), (int) totalAmount, "Đang xử lí", date);
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

        return order;
    }

    public List<Notification> getNotificationsByAccountId(int accountId) {
        List<Notification> notifications = new ArrayList<>();
        String sql = "SELECT * FROM Notification WHERE account_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, accountId);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String desciption = rs.getString("descripsion");
                int order_id = rs.getInt("order_id");
//                notifications.add(new Notification(id, desciption, accountId, order_id));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Notifications size: " + notifications.size());
        return notifications;
    }

    public List<OrderDetails> getOrderDetailsByOrderId(int orderId) {
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM OrderDetails WHERE order_id = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, orderId);
            rs = ps.executeQuery();

            while (rs.next()) {
                OrderDetails orderDetails = new OrderDetails();
                orderDetails.setId(rs.getInt("id"));
                orderDetails.setOrder_id(rs.getInt("order_id"));
                orderDetails.setProduct_id(rs.getInt("product_id"));
                orderDetails.setQuantity(rs.getInt("quantity"));
                orderDetails.setPrice(rs.getFloat("price"));
                // Set other fields if necessary
                orderDetailsList.add(orderDetails);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderDetailsList;
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
                + "address r_address ON r.address_id = r_address.id where o.status = N'Waiting delivery'";

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
                java.sql.Timestamp orderDate = rs.getTimestamp("order_date");

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
                + "    r_address.details AS from_details, \n"
                + "    r_address.street AS from_street, \n"
                + "    r_address.state AS from_state, \n"
                + "    c_address.details AS to_details, \n"
                + "    c_address.street AS to_street, \n"
                + "    c_address.state AS to_state,\n"
                + "    o.total_amount, \n"
                + "    o.status, \n"
                + "    o.order_date\n"
                + "FROM \n"
                + "    [Order] o \n"
                + "JOIN \n"
                + "    customer c ON o.customer_id = c.id\n"
                + "JOIN \n"
                + "    account c_account ON c.account_id = c_account.id\n"
                + "JOIN \n"
                + "    address c_address ON c_account.address_id = c_address.id\n"
                + "JOIN \n"
                + "    restaurant r ON o.restaurant_id = r.id\n"
                + "JOIN \n"
                + "    account r_account ON r.account_id = r_account.id\n"
                + "JOIN \n"
                + "    address r_address ON r_account.address_id = r_address.id\n"
                + "WHERE \n"
                + "    o.id = ?;";
        Order x = null;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int xId = rs.getInt("id");
                int xTotal_amount = rs.getInt("total_amount");
                String xStatus = rs.getString("status");
                java.sql.Timestamp xOrder_date = rs.getTimestamp("order_date");
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
                java.sql.Timestamp xOrder_date = rs.getTimestamp("order_date");

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

    public List<Order> getAllOrderOf1Customer(int customerID) {
        List<Order> list = new ArrayList<>();
        xSql = "select * from [Order] where customer_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, customerID);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Order(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getTimestamp(6)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getTotalAmountByMonth(int month, int restaurantId) {
        xSql = "SELECT SUM(total_amount) AS totalamount \n"
                + "FROM [Order] \n"
                + "WHERE restaurant_id = ? AND MONTH(order_date) = ? ";
        int totalAmount = 0;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, restaurantId);
            ps.setInt(2, month);
            rs = ps.executeQuery();
            while (rs.next()) {
                totalAmount = rs.getInt("totalAmount");
            }
            return totalAmount;
        } catch (Exception e) {
        }
        return totalAmount;
    }

    public Map<String, Integer> getRevenueForPeriod(int startMonth, int endMonth, int year, int restaurantId) {
        xSql = "SELECT \n"
                + "    MONTH(order_date) AS month, \n"
                + "    SUM(total_amount) AS totalamount \n"
                + "FROM \n"
                + "    [Order] \n"
                + "WHERE \n"
                + "    restaurant_id = ? \n"
                + "    AND (\n"
                + "        (YEAR(order_date) = ? AND MONTH(order_date) BETWEEN ? AND ?)\n"
                + "        OR (YEAR(order_date) = ? AND MONTH(order_date) BETWEEN 1 AND ?)\n"
                + "    )\n"
                + "GROUP BY \n"
                + "    MONTH(order_date);";
        Map<String, Integer> revenueMap = new HashMap<>();
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, restaurantId);
            ps.setInt(2, year);
            ps.setInt(3, startMonth);
            ps.setInt(4, 12);
            ps.setInt(5, year + 1);
            ps.setInt(6, endMonth);
            rs = ps.executeQuery();
            while (rs.next()) {
                int month = rs.getInt("month");
                int totalAmount = rs.getInt("totalamount");
                revenueMap.put(String.valueOf(month), totalAmount);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return revenueMap;
    }

    public List<Order> getAddressRestaurant_CustomerWithId(Boolean sortList) {
        List<Order> orders = new ArrayList<>();
        xSql = "SELECT  o.id, r_address.details AS from_details, \n"
                + "    r_address.street AS from_street, \n"
                + "    r_address.state AS from_state,\n"
                + "    c_address.details AS to_details, \n"
                + "    c_address.street AS to_street, \n"
                + "    c_address.state AS to_state,\n"
                + "    o.total_amount,\n"
                + "    o.status, \n"
                + "    o.order_date\n"
                + "FROM [Order] o\n"
                + "JOIN customer c ON o.customer_id = c.id\n"
                + "JOIN account c_account ON c.account_id = c_account.id\n"
                + "JOIN address c_address ON c_account.address_id = c_address.id\n"
                + "JOIN restaurant r ON o.restaurant_id = r.id\n"
                + "JOIN account r_account ON r.account_id = r_account.id\n"
                + "JOIN address r_address ON r_account.address_id = r_address.id\n"
                + "WHERE o.status = N'Waiting delivery' ";

        if (sortList != null) {
            String time = sortList ? "ASC" : "DESC";
            xSql += "ORDER BY o.order_date " + time;
        }

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
                java.sql.Timestamp orderDate = rs.getTimestamp("order_date");

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

    public List<Order> getOrderByStatus() {
        List<Order> t = new ArrayList<>();
        xSql = "SELECT * FROM [Order] WHERE [status] IN (N'Waiting delivery');";
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
                java.sql.Timestamp xOrder_date = rs.getTimestamp("order_date");

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

    public void updateStatusOrder(int id) {
        String xSql = "UPDATE [Order]\n"
                + "SET [status] = N'Picking Up'\n"
                + "WHERE [id] = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStatusOrder_1(int id) {
        String xSql = "UPDATE [Order]\n"
                + "SET [status] = N'Delivering'\n"
                + "WHERE id = ?;";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStatusOrder_2(int id) {
        String xSql = "UPDATE [Order]\n"
                + "SET [status] = N'Success'\n"
                + "WHERE id = ?;";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStatusOrder_3(int id) {
        String xSql = "UPDATE [Order]\n"
                + "SET [status] = N'Failure'\n"
                + "WHERE id = ?;";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Order getRestaurant_Customer_ByOrderId(int id) {
        String xSql = "SELECT \n"
                + "    o.id,\n"
                + "    r.name AS res_name,\n"
                + "    r_account.phonenumber AS res_phone,\n"
                + "    c.name AS cus_name,\n"
                + "    c_account.phonenumber AS cus_phone,\n"
                + "    o.status,\n"
                + "    o.order_date\n"
                + "FROM \n"
                + "    [Order] o\n"
                + "JOIN \n"
                + "    Restaurant r ON o.restaurant_id = r.id\n"
                + "JOIN \n"
                + "    Customer c ON o.customer_id = c.id\n"
                + "JOIN \n"
                + "    account c_account ON c.account_id = c_account.id\n"
                + "JOIN \n"
                + "    account r_account ON r.account_id = r_account.id\n"
                + "WHERE \n"
                + "    o.id = ?";
        Order o = null;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                int xId = rs.getInt("id");
                String xRestaurant_name = rs.getString("res_name");
                String xRestaurant_phone = rs.getString("res_phone");
                String xCustomer_name = rs.getString("cus_name");
                String xCustomer_phone = rs.getString("cus_phone");
                String xStatus = rs.getString("status");
                java.sql.Timestamp xOrder_date = rs.getTimestamp("order_date");

                o = new Order(xId, xStatus, xOrder_date, xRestaurant_name, xRestaurant_phone, xCustomer_name, xCustomer_phone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return o;
    }

    public List<Order> searchAddressRestaurant_CustomerWithId(String from) {
        List<Order> orders = new ArrayList<>();
        String xSql = "SELECT  o.id, r_address.details AS from_details, \n"
                + "    r_address.street AS from_street, \n"
                + "    r_address.state AS from_state,\n"
                + "    c_address.details AS to_details, \n"
                + "    c_address.street AS to_street, \n"
                + "    c_address.state AS to_state,\n"
                + "    o.total_amount,\n"
                + "    o.status, \n"
                + "    o.order_date\n"
                + "FROM [Order] o\n"
                + "JOIN customer c ON o.customer_id = c.id\n"
                + "JOIN account c_account ON c.account_id = c_account.id\n"
                + "JOIN address c_address ON c_account.address_id = c_address.id\n"
                + "JOIN restaurant r ON o.restaurant_id = r.id\n"
                + "JOIN account r_account ON r.account_id = r_account.id\n"
                + "JOIN address r_address ON r_account.address_id = r_address.id\n"
                + "WHERE o.status = N'Waiting delivery'  ";

        // Thêm điều kiện tìm kiếm nếu từ khóa tìm kiếm không rỗng
        if (from != null && !from.trim().isEmpty()) {
            xSql += "AND (r_address.state LIKE ? OR c_address.state LIKE ?)";
        }

        try {
            ps = con.prepareStatement(xSql);
            // Thiết lập giá trị cho các tham số tìm kiếm
            if (from != null && !from.trim().isEmpty()) {
                String searchQuery = "%" + from.trim() + "%";
                ps.setString(1, searchQuery);
                ps.setString(2, searchQuery);
            }
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
                java.sql.Timestamp orderDate = rs.getTimestamp("order_date");

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

    public static void main(String[] args) {
        OrderDAO od = new OrderDAO();
//        List<Order> list = od.getAllOrderOf1Customer(2);
//        Map<String, Integer> map = od.getRevenueForPeriod(1, 6, 2024, 1);
//        for (Map.Entry<String, Integer> entry : map.entrySet()) {
//            System.out.println(entry.getKey() + ": " + entry.getValue());
//        }
        System.out.println(od.getRestaurant_Customer_ByOrderId(1));
    }
}
