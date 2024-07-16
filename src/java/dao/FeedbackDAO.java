/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Customer;
import model.Feedback;
import model.Restaurant;

/**
 *
 * @author Admin
 */
public class FeedbackDAO extends MyDAO {

    public void addFeedback(Feedback f) {
        xSql = "INSERT INTO [dbo].[Feedback]"
                + "([customer_id],[restaurant_id],[order_id],[value],[description],[image],[product_id]) "
                + "VALUES (?,?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, f.getCustomerID());
            ps.setInt(2, f.getRestaurantID());
            ps.setInt(3, f.getOrderID());
            ps.setInt(4, f.getValue());
            ps.setString(5, f.getDescription());
            ps.setString(6, f.getImg());
            ps.setInt(7, f.getProductID());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
      public List<Feedback> getListFeedback() {
        List<Feedback> list = new ArrayList<>();
        try {
            String sql = "select f.id, c.name, r.name,f.order_id, f.value,f.description\n"
                    + "from Feedback f\n"
                    + "join Customer c on c.id= f.customer_id\n"
                    + "join Restaurant r on r.id= f.restaurant_id";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Feedback a = new Feedback(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8));
                list.add(a);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public void deleteFeedback(int orderId, int product_id) {
        xSql = "DELETE FROM [dbo].[Feedback]\n"
                + "      WHERE order_id = ? and product_id=?";

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, orderId);
            ps.setInt(2, product_id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateFeedback(Feedback f) {
        xSql = "UPDATE [dbo].[Feedback]\n"
                + "   SET [value] = ?,\n"
                + "       [description] = ?\n"
                + " WHERE [order_id] = ? AND [product_id] = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, f.getValue());
            ps.setString(2, f.getDescription());
            ps.setInt(3, f.getOrderID());
            ps.setInt(4, f.getProductID());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Feedback getFeedbackByOrderIdAndProductId(int order_id, int product_id) {
        {
            xSql = "SELECT * FROM Feedback where order_id = ? and product_id = ?";
            try {
                ps = con.prepareStatement(xSql);
                ps.setInt(1, order_id);
                ps.setInt(2, product_id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    return new Feedback(rs.getInt(1),
                            rs.getInt(2),
                            rs.getInt(3),
                            rs.getInt(4),
                            rs.getInt(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getInt(8));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public boolean hasFeedback(int order_id, int product_id) {
        xSql = "SELECT COUNT(*) FROM Feedback where order_id = ? and product_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, order_id);
            ps.setInt(2, product_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Feedback> getAllFeedback() {
        List<Feedback> list = new ArrayList<>();
        xSql = "SELECT * FROM feedback";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Feedback(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> list = new ArrayList<>();
        xSql = "SELECT * FROM customer";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Customer(rs.getInt("id"), rs.getString("name")));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Restaurant> getAllRestaurants() {//only id and name?
        List<Restaurant> list = new ArrayList<>();
        xSql = "SELECT * FROM restaurant";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Restaurant(rs.getInt(1), rs.getString(2)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //lấy all feedback của 1 nhà hàng
    public List<Feedback> getFeedbackOfARestaurant(int restaurantID) {
        List<Feedback> list = new ArrayList<>();
        xSql = "select * from Feedback where restaurant_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, restaurantID);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int customerId = rs.getInt("customer_id");
                int restaurantId = rs.getInt("restaurant_id");
                int orderId = rs.getInt("order_id");
                int value = rs.getInt("value");
                String description = rs.getString("description");
                String img = rs.getString("image");
                int productId = rs.getInt("product_id");
                Feedback f = new Feedback(id, customerId, restaurantId, orderId, value, description, img, productId);
                list.add(f);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Map<Integer, String> getCustomerNamesByIDs(List<Integer> customerIDs) {
        Map<Integer, String> customerNames = new HashMap<>();
        if (customerIDs.isEmpty()) {
            return customerNames;
        }

        StringBuilder sql = new StringBuilder("SELECT id, name FROM Customer WHERE id IN (");
        for (int i = 0; i < customerIDs.size(); i++) {
            sql.append("?");
            if (i < customerIDs.size() - 1) {
                sql.append(", ");
            }
        }
        sql.append(")");

        try {
            ps = con.prepareStatement(sql.toString());
            for (int i = 0; i < customerIDs.size(); i++) {
                ps.setInt(i + 1, customerIDs.get(i));
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                customerNames.put(rs.getInt("id"), rs.getString("name"));
            }
            System.out.println("Customer names retrieved: " + customerNames);
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
        return customerNames;
    }

    public static void main(String[] args) throws SQLException {
        FeedbackDAO dao = new FeedbackDAO();
        List<Feedback> list = dao.getFeedbackOfARestaurant(1);
        System.out.println(list);
    }
}
