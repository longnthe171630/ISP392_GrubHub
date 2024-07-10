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
import model.Restaurant;

/**
 *
 * @author manh0
 */
public class OrderDAO extends MyDAO {

    private RestaurantDAO res = new RestaurantDAO();

    public List<Order> getOrder() {
        List<Order> t = new ArrayList<>();
        xSql = "select * from [Order]";
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
                + "WHERE o.status = N'Đang chờ' ";

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

    public Order getOrderById(int id) {
        xSql = "SELECT \n"
                + "    o.id, \n"
                + "    r_address.details AS from_details, \n"
                + "    r_address.street AS from_street, \n"
                + "    r_address.state AS from_state,\n"
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
                + "SET [status] = N'Đang lấy hàng'\n"
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
                + "SET [status] = N'Đang giao'\n"
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
                + "SET [status] = N'Đã giao'\n"
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
                + "SET [status] = N'Không giao được'\n"
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
                + "WHERE o.status = N'Đang chờ'  ";

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
        OrderDAO d = new OrderDAO();
        List<Order> lo = d.getAddressRestaurant_CustomerWithId(Boolean.FALSE);
        if (lo == null) {
            System.out.println("List empty");
        } else {
            for (Order o : lo) {
                System.out.println(o);

            }
        }
        //System.out.println(d.getOrderById(1));
    }

}
