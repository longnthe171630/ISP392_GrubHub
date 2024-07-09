/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.io.InputStream;
import java.security.Timestamp;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Address;
import model.Delivery;

/**
 *
 * @author Long1
 */
public class DeliveryDAO extends MyDAO {

    public List<Delivery> getDeliverySuccess() {
        List<Delivery> t = new ArrayList<>();
        xSql = "select * from Delivery where status = N'Đã giao'";
        Delivery x;
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int xId = rs.getInt("id");
                int xOrder_id = rs.getInt("order_id");
                int xDelivery_person_id = rs.getInt("delivery_person_id");
                int xShip_price = rs.getInt("ship_price");
                java.sql.Timestamp xDelivery_date = rs.getTimestamp("delivery_date");
                String xStatus = rs.getString("status");
                String xImage = rs.getString("image");
                x = new Delivery(xId, xOrder_id, xDelivery_person_id, xShip_price, xDelivery_date, xStatus, xImage);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public List<Delivery> getDeliveryFailure() {
        List<Delivery> t = new ArrayList<>();
        xSql = "select * from Delivery where status = N'Không giao được'";
        Delivery x;
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int xId = rs.getInt("id");
                int xOrder_id = rs.getInt("order_id");
                int xDelivery_person_id = rs.getInt("delivery_person_id");
                int xShip_price = rs.getInt("ship_price");
                java.sql.Timestamp xDelivery_date = rs.getTimestamp("delivery_date");
                String xStatus = rs.getString("status");
                String xImage = rs.getString("image");
                x = new Delivery(xId, xOrder_id, xDelivery_person_id, xShip_price, xDelivery_date, xStatus, xImage);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public float getShipPricByOrderId(int order_id) {
        xSql = "select * from Delivery where [order_id] = ?";

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, order_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                float xShip_price = rs.getFloat("ship_price");
                return xShip_price;
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Delivery> getDeliveryDashboard(int id, Boolean sortList) {
        List<Delivery> t = new ArrayList<>();
        xSql = "SELECT d.*\n"
                + "FROM Delivery d\n"
                + "JOIN Delivery_person dp ON d.delivery_person_id = dp.id\n"
                + "WHERE (d.status = 'Đang giao' OR d.status = N'Đang lấy hàng')\n"
                + "AND dp.id = ? ";

        if (sortList != null) {
            String time = sortList ? "ASC" : "DESC";
            xSql += "ORDER BY d.delivery_date " + time;
        }
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int xId = rs.getInt("id");
                int xOrder_id = rs.getInt("order_id");
                int xDelivery_person_id = rs.getInt("delivery_person_id");
                int xShip_price = rs.getInt("ship_price");
                java.sql.Timestamp xDelivery_date = rs.getTimestamp("delivery_date");
                String xStatus = rs.getString("status");
                String xImage = rs.getString("image");
                Delivery x = new Delivery(xId, xOrder_id, xDelivery_person_id, xShip_price, xDelivery_date, xStatus, xImage);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public List<Delivery> getDeliveryHistory(int id, Boolean sortList) {
        List<Delivery> t = new ArrayList<>();
        xSql = "SELECT d.*\n"
                + "FROM Delivery d\n"
                + "JOIN Delivery_person dp ON d.delivery_person_id = dp.id\n"
                + "WHERE (d.status = N'Đã giao' OR d.status = N'Không giao được')\n"
                + "AND dp.id = ? ";
        if (sortList != null) {
            String time = sortList ? "ASC" : "DESC";
            xSql += "ORDER BY d.delivery_date " + time;
        }
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int xId = rs.getInt("id");
                int xOrder_id = rs.getInt("order_id");
                int xDelivery_person_id = rs.getInt("delivery_person_id");
                int xShip_price = rs.getInt("ship_price");
                java.sql.Timestamp xDelivery_date = rs.getTimestamp("delivery_date");
                String xStatus = rs.getString("status");
                String xImage = rs.getString("image");
                Delivery x = new Delivery(xId, xOrder_id, xDelivery_person_id, xShip_price, xDelivery_date, xStatus, xImage);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public Delivery getDeliveryByOrderId(int id) {
        xSql = "select * from Delivery where [order_id] = ?";
        Delivery x = null;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int xId = rs.getInt("id");
                int xOrder_id = rs.getInt("order_id");
                int xDelivery_person_id = rs.getInt("delivery_person_id");
                int xShip_price = rs.getInt("ship_price");
                java.sql.Timestamp xDelivery_date = rs.getTimestamp("delivery_date");
                String xStatus = rs.getString("status");
                String xImage = rs.getString("image");
                x = new Delivery(xId, xOrder_id, xDelivery_person_id, xShip_price, xDelivery_date, xStatus, xImage);

            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return x;
    }

    public void updateStatusDelivery(int id) {
        String xSql = "UPDATE [Delivery]\n"
                + "SET [status] = N'Đang lấy hàng', delivery_date = CONVERT(VARCHAR(19), GETDATE(), 120)\n"
                + "WHERE [status] = N'Đang chờ' AND [order_id] = ?;";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStatusDelivery_2(int id) {
        String xSql = "UPDATE [Delivery]\n"
                + "SET [status] = N'Đã giao'\n , delivery_date = CONVERT(VARCHAR(19), GETDATE(), 120)\n"
                + "WHERE [status] = N'Đang giao' AND [order_id] = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStatusDelivery_3(int id) {
        String xSql = "UPDATE [Delivery]\n"
                + "SET [status] = N'Không giao được'\n , delivery_date = CONVERT(VARCHAR(19), GETDATE(), 120)\n"
                + "WHERE [status] = N'Đang giao' AND [order_id] = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStatusDelivery_4(int id) {
        String xSql = "UPDATE [Delivery]\n"
                + "SET [status] = N'Đang giao' , delivery_date = CONVERT(VARCHAR(19), GETDATE(), 120)\n"
                + "WHERE [status] = N'Đang lấy hàng' AND [order_id] = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int totalShipPriceByDeliveryPersonId(int id) {
        String xSql = "SELECT SUM(ship_price) AS total_SP\n"
                + "FROM [Delivery]\n"
                + "WHERE status = N'Đã giao' and delivery_person_id = ?";
        int total_SP = 0;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                total_SP = rs.getInt("total_SP");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total_SP;
    }

    public int totalDeliveryByDeliveryPersonId(int id) {
        String xSql = "SELECT COUNT(*) AS total_delivery\n"
                + "FROM [Delivery]\n"
                + "WHERE status = N'Đang giao' and delivery_person_id = ?";
        int total_D = 0;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                total_D = rs.getInt("total_delivery");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total_D;
    }

    public int totalDoneByDeliveryPersonId(int id) {
        String xSql = "SELECT COUNT(*) AS total_done\n"
                + "FROM [Delivery]\n"
                + "WHERE status = N'Đã giao' and delivery_person_id = ?";
        int total_D = 0;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                total_D = rs.getInt("total_done");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total_D;
    }

    public int totalCancelByDeliveryPersonId(int id) {
        String xSql = "SELECT COUNT(*) AS total_cancel\n"
                + "FROM [Delivery]\n"
                + "WHERE status = N'Không giao được' and delivery_person_id = ?";
        int total_D = 0;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                total_D = rs.getInt("total_cancel");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total_D;
    }

    public boolean updateDeliveryPersonId(int id, int order_id) {
        String xSql = "UPDATE delivery SET delivery_person_id = ? where order_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            ps.setInt(2, order_id);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void savePathToDatabase(String pathOfFile, int order_id) {
        String xSql = "UPDATE delivery SET image = ? WHERE order_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, pathOfFile);
            ps.setInt(2, order_id);
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Save to database succesfull!");
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getDeliveryPersonIdByUsername(String username) {
        String xSql = "SELECT dp.*\n"
                + "FROM Account a\n"
                + "JOIN Delivery_person dp ON a.id = dp.account_id\n"
                + "WHERE a.username = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                int xId = rs.getInt("id");
                return xId;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getDeliveryPersonIdByOrderId(int order_id) {
        xSql = "select delivery_person_id from Delivery where order_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, order_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                int xDelivery_person_id = rs.getInt("delivery_person_id");
                return xDelivery_person_id;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public String getImageDeliveryByOrderId(int order_id) {
        String xSql = "SELECT image FROM delivery WHERE order_id = ?";
        String imagePath = null;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, order_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                imagePath = rs.getString("image");
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return imagePath;
    }

    public List<Delivery> searchDeliveryDashboard(int id, String code) {
        List<Delivery> t = new ArrayList<>();
        xSql = "SELECT d.*\n"
                + "FROM Delivery d\n"
                + "JOIN Delivery_person dp ON d.delivery_person_id = dp.id\n"
                + "WHERE (d.status = 'Đang giao' OR d.status = N'Đang lấy hàng')\n"
                + "AND dp.id = ?";

        // Thêm điều kiện tìm kiếm nếu từ khóa tìm kiếm không rỗng
        if (code != null && !code.trim().isEmpty()) {
            xSql += " AND d.order_id LIKE ?";
        }

        try {
            ps = con.prepareStatement(xSql);
            // Thiết lập giá trị cho các tham số tìm kiếm
            ps.setInt(1, id);
            if (code != null && !code.trim().isEmpty()) {
                String searchQuery = "%" + code.trim() + "%";
                ps.setString(2, searchQuery);  // Thiết lập tham số thứ hai
            }
            rs = ps.executeQuery();

            while (rs.next()) {
                int xId = rs.getInt("id");
                int xOrder_id = rs.getInt("order_id");
                int xDelivery_person_id = rs.getInt("delivery_person_id");
                int xShip_price = rs.getInt("ship_price");
                java.sql.Timestamp xDelivery_date = rs.getTimestamp("delivery_date");
                String xStatus = rs.getString("status");
                String xImage = rs.getString("image");
                Delivery x = new Delivery(xId, xOrder_id, xDelivery_person_id, xShip_price, xDelivery_date, xStatus, xImage);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public List<Delivery> searchDeliveryHistory(int id, String code) {
        List<Delivery> t = new ArrayList<>();
        xSql = "SELECT d.*\n"
                + "FROM Delivery d\n"
                + "JOIN Delivery_person dp ON d.delivery_person_id = dp.id\n"
                + "WHERE d.status IN (N'Đã giao', N'Không giao được') AND dp.id = ?";
        // Thêm điều kiện tìm kiếm nếu từ khóa tìm kiếm không rỗng
        if (code != null && !code.trim().isEmpty()) {
            xSql += " AND d.order_id LIKE ?";
        }

        try {
            ps = con.prepareStatement(xSql);
            // Thiết lập giá trị cho các tham số tìm kiếm
            ps.setInt(1, id);
            if (code != null && !code.trim().isEmpty()) {
                String searchQuery = "%" + code.trim() + "%";
                ps.setString(2, searchQuery);  // Thiết lập tham số thứ hai
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                int xId = rs.getInt("id");
                int xOrder_id = rs.getInt("order_id");
                int xDelivery_person_id = rs.getInt("delivery_person_id");
                int xShip_price = rs.getInt("ship_price");
                java.sql.Timestamp xDelivery_date = rs.getTimestamp("delivery_date");
                String xStatus = rs.getString("status");
                String xImage = rs.getString("image");
                Delivery x = new Delivery(xId, xOrder_id, xDelivery_person_id, xShip_price, xDelivery_date, xStatus, xImage);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public long calculateDeliveryDuration(int order_id) {
        String sql = "SELECT * FROM [Delivery] WHERE [order_id] = ? ORDER BY delivery_date DESC";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, order_id);
            rs = ps.executeQuery();
            java.sql.Timestamp startDeliveryTime = null;
            java.sql.Timestamp endDeliveryTime = null;
            while (rs.next()) {
                java.sql.Timestamp delivery_date = rs.getTimestamp("delivery_date");
                String status = rs.getString("status");
                if (status.equals("Đang lấy hàng")) {
                    startDeliveryTime = delivery_date;
                } else if (status.equals("Đã giao") || status.equals("Không giao được")) {
                    endDeliveryTime = delivery_date;
                    break; // Break loop when found end time
                }
            }
            if (startDeliveryTime != null && endDeliveryTime != null) {
                // Calculate duration in milliseconds
                long durationInMillis = endDeliveryTime.getTime() - startDeliveryTime.getTime();
                int durationInMinutes = (int) (durationInMillis / (1000 * 60));
                return durationInMinutes;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; 
    }

    public static void main(String[] args) {
        DeliveryDAO d = new DeliveryDAO();
//        List<Delivery> ld = d.getDeliveryFailure();
//        if (ld == null) {
//            System.out.println("List empty");
//        } else {
//            for (Delivery dx : ld) {
//                System.out.println(dx);
//
//            }
//        }
        System.out.println(d.calculateDeliveryDuration(4));
    }
}
