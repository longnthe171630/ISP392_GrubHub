/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.io.InputStream;
import java.security.Timestamp;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import model.Address;
import model.Delivery;

/**
 *
 * @author Long1
 */
public class DeliveryDAO extends MyDAO {

    public List<Delivery> getDeliverySuccess(int id) {
        List<Delivery> list = new ArrayList<>();
        xSql = "select * from Delivery where status = N'Success' and delivery_person_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int xOrder_id = rs.getInt("order_id");
                int xDelivery_person_id = rs.getInt("delivery_person_id");
                int xShip_price = rs.getInt("ship_price");
                java.sql.Timestamp xDelivery_date = rs.getTimestamp("delivery_date");
                String xStatus = rs.getString("status");
                java.sql.Timestamp xStart_time = rs.getTimestamp("start_time");
                java.sql.Timestamp xEnd_time = rs.getTimestamp("end_time");

                // Tính toán khoảng thời gian nếu có đủ dữ liệu
                int deliveryTime = -1; // Mặc định -1 nếu không đủ dữ liệu
                if (xStart_time != null && xEnd_time != null) {
                    deliveryTime = calculateDeliveryTime(xStart_time, xEnd_time);
                }

                Delivery delivery = new Delivery(xOrder_id,xDelivery_person_id,xShip_price, xDelivery_date, xStatus, deliveryTime, xStart_time, xEnd_time);
                list.add(delivery);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Delivery> getDeliveryFailure(int id) {
        List<Delivery> list = new ArrayList<>();
        xSql = "select * from Delivery where status = N'Failure' and delivery_person_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int xOrder_id = rs.getInt("order_id");
                int xDelivery_person_id = rs.getInt("delivery_person_id");
                int xShip_price = rs.getInt("ship_price");
                java.sql.Timestamp xDelivery_date = rs.getTimestamp("delivery_date");
                String xStatus = rs.getString("status");
                java.sql.Timestamp xStart_time = rs.getTimestamp("start_time");
                java.sql.Timestamp xEnd_time = rs.getTimestamp("end_time");

                // Tính toán khoảng thời gian nếu có đủ dữ liệu
                int deliveryTime = -1; // Mặc định -1 nếu không đủ dữ liệu
                if (xStart_time != null && xEnd_time != null) {
                    deliveryTime = calculateDeliveryTime(xStart_time, xEnd_time);
                }

                Delivery delivery = new Delivery(xOrder_id,xDelivery_person_id,xShip_price, xDelivery_date, xStatus, deliveryTime, xStart_time, xEnd_time);
                list.add(delivery);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Integer> getDeliveryTimesSuccess(int id) {
        Map<String, Integer> deliveryCountPerDay = new HashMap<>();
        String xSql = "SELECT delivery_person_id, delivery_date FROM Delivery WHERE status = N'Success' and delivery_person_id = ?";

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int xDelivery_person_id = rs.getInt("delivery_person_id");
                java.sql.Timestamp xDelivery_date = rs.getTimestamp("delivery_date");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = sdf.format(xDelivery_date);

                // Tăng số lượng đơn hàng trong ngày đó
                deliveryCountPerDay.put(formattedDate, deliveryCountPerDay.getOrDefault(formattedDate, 0) + 1);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Chuyển đổi Map thành List và sắp xếp theo ngày
        List<Map.Entry<String, Integer>> sortedDeliveryCountPerDay = new ArrayList<>(deliveryCountPerDay.entrySet());
        sortedDeliveryCountPerDay.sort(Map.Entry.comparingByKey());

        // Trả về danh sách số lượng đơn hàng mỗi ngày
        List<Integer> deliveryCounts = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : sortedDeliveryCountPerDay) {
            deliveryCounts.add(entry.getValue());
        }
        return deliveryCounts;
    }

    public List<Integer> getDeliveryTimesFailure(int id) {
        Map<String, Integer> deliveryCountPerDay = new HashMap<>();
        String xSql = "SELECT delivery_person_id, delivery_date FROM Delivery WHERE status = N'Failure' and delivery_person_id = ?";

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int xDelivery_person_id = rs.getInt("delivery_person_id");
                java.sql.Timestamp xDelivery_date = rs.getTimestamp("delivery_date");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = sdf.format(xDelivery_date);

                // Tăng số lượng đơn hàng trong ngày đó
                deliveryCountPerDay.put(formattedDate, deliveryCountPerDay.getOrDefault(formattedDate, 0) + 1);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Chuyển đổi Map thành List và sắp xếp theo ngày
        List<Map.Entry<String, Integer>> sortedDeliveryCountPerDay = new ArrayList<>(deliveryCountPerDay.entrySet());
        sortedDeliveryCountPerDay.sort(Map.Entry.comparingByKey());

        // Trả về danh sách số lượng đơn hàng mỗi ngày
        List<Integer> deliveryCounts = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : sortedDeliveryCountPerDay) {
            deliveryCounts.add(entry.getValue());
        }
        return deliveryCounts;
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
                + "WHERE (d.status = 'Delivering' OR d.status = N'Picking Up')\n"
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
                java.sql.Timestamp xStart_time = rs.getTimestamp("start_time");
                java.sql.Timestamp xEnd_time = rs.getTimestamp("end_time");
                Delivery x = new Delivery(xId, xOrder_id, xDelivery_person_id, xShip_price, xDelivery_date, xStatus, xImage, xStart_time, xEnd_time);
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
                + "WHERE (d.status = N'Success' OR d.status = N'Failure')\n"
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
                java.sql.Timestamp xStart_time = rs.getTimestamp("start_time");
                java.sql.Timestamp xEnd_time = rs.getTimestamp("end_time");
                Delivery x = new Delivery(xId, xOrder_id, xDelivery_person_id, xShip_price, xDelivery_date, xStatus, xImage, xStart_time, xEnd_time);
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
                java.sql.Timestamp xStart_time = rs.getTimestamp("start_time");
                java.sql.Timestamp xEnd_time = rs.getTimestamp("end_time");
                x = new Delivery(xId, xOrder_id, xDelivery_person_id, xShip_price, xDelivery_date, xStatus, xImage, xStart_time, xEnd_time);

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
                + "SET [status] = N'Picking Up', delivery_date = CONVERT(VARCHAR(19), GETDATE(), 120), start_time = CONVERT(VARCHAR(19), GETDATE(), 120)\n"
                + "WHERE [status] = N'Waiting delivery' AND [order_id] = ?;";
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
                + "SET [status] = N'Success'\n , delivery_date = CONVERT(VARCHAR(19), GETDATE(), 120), end_time = CONVERT(VARCHAR(19), GETDATE(), 120)\n"
                + "WHERE [status] = N'Delivering' AND [order_id] = ?";
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
                + "SET [status] = N'Failure'\n , delivery_date = CONVERT(VARCHAR(19), GETDATE(), 120), end_time = CONVERT(VARCHAR(19), GETDATE(), 120)\n"
                + "WHERE [status] = N'Delivering' AND [order_id] = ?";
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
                + "SET [status] = N'Delivering' , delivery_date = CONVERT(VARCHAR(19), GETDATE(), 120)\n"
                + "WHERE [status] = N'Picking Up' AND [order_id] = ?";
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
                + "WHERE status = N'Success' and delivery_person_id = ?";
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
                + "WHERE status = N'Delivering' and delivery_person_id = ?";
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
                + "WHERE status = N'Success' and delivery_person_id = ?";
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
                + "WHERE status = N'Failure' and delivery_person_id = ?";
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
                + "WHERE (d.status = 'Delivering' OR d.status = N'Picking Up')\n"
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
                java.sql.Timestamp xStart_time = rs.getTimestamp("start_time");
                java.sql.Timestamp xEnd_time = rs.getTimestamp("end_time");
                Delivery x = new Delivery(xId, xOrder_id, xDelivery_person_id, xShip_price, xDelivery_date, xStatus, xImage, xStart_time, xEnd_time);
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
                + "WHERE d.status IN (N'Success', N'Failure') AND dp.id = ?";
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
                java.sql.Timestamp xStart_time = rs.getTimestamp("start_time");
                java.sql.Timestamp xEnd_time = rs.getTimestamp("end_time");
                Delivery x = new Delivery(xId, xOrder_id, xDelivery_person_id, xShip_price, xDelivery_date, xStatus, xImage, xStart_time, xEnd_time);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public int getTotalDeliveryTimeSuccess(int id) {
        int totalDeliveryTime = 0;
        xSql = "SELECT delivery_person_id, start_time, end_time FROM Delivery WHERE status = N'Success' and delivery_person_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int xDelivery_person_id = rs.getInt("delivery_person_id");
                java.sql.Timestamp startTime = rs.getTimestamp("start_time");
                java.sql.Timestamp endTime = rs.getTimestamp("end_time");

                if (startTime != null && endTime != null) {
                    totalDeliveryTime += calculateDeliveryTime(startTime, endTime);
                }
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalDeliveryTime;
    }

    public int getTotalDeliveryTimeFailed(int id) {
        int totalDeliveryTime = 0;
        xSql = "SELECT delivery_person_id, start_time, end_time FROM Delivery WHERE status = N'Failure' and delivery_person_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int xDelivery_person_id = rs.getInt("delivery_person_id");
                java.sql.Timestamp startTime = rs.getTimestamp("start_time");
                java.sql.Timestamp endTime = rs.getTimestamp("end_time");

                if (startTime != null && endTime != null) {
                    totalDeliveryTime += calculateDeliveryTime(startTime, endTime);
                }
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalDeliveryTime;
    }

    public String getDeliveryDatesSuccess(int id) {
        Set<String> deliveryDatesSet = new LinkedHashSet<>(); // Sử dụng LinkedHashSet để giữ thứ tự sắp xếp
        List<String> deliveryDates = new ArrayList<>();
        xSql = "SELECT DISTINCT delivery_person_id, delivery_date FROM Delivery WHERE delivery_date IS NOT NULL AND status = N'Success' and delivery_person_id = ? ORDER BY delivery_date ASC;"; // Sử dụng DISTINCT để loại bỏ các giá trị trùng lặp
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int xDelivery_person_id = rs.getInt("delivery_person_id");
                java.sql.Timestamp xDelivery_date = rs.getTimestamp("delivery_date");
                String formattedDate = sdf.format(xDelivery_date);
                deliveryDatesSet.add(formattedDate);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        deliveryDates.addAll(deliveryDatesSet); // Chuyển LinkedHashSet sang ArrayList

        // Chuyển ArrayList thành một chuỗi JSON định dạng mong muốn
        StringBuilder jsonBuilder = new StringBuilder("[");
        for (int i = 0; i < deliveryDates.size(); i++) {
            jsonBuilder.append("'").append(deliveryDates.get(i)).append("'");
            if (i < deliveryDates.size() - 1) {
                jsonBuilder.append(", ");
            }
        }
        jsonBuilder.append("]");

        // In ra chuỗi JSON kết quả
        return jsonBuilder.toString();
    }

    public String getDeliveryDatesFailure(int id) {
        Set<String> deliveryDatesSet = new LinkedHashSet<>(); // Sử dụng LinkedHashSet để giữ thứ tự sắp xếp
        List<String> deliveryDates = new ArrayList<>();
        xSql = "SELECT DISTINCT delivery_person_id, delivery_date FROM Delivery WHERE delivery_date IS NOT NULL AND status = N'Failure' and delivery_person_id = ? ORDER BY delivery_date ASC;"; // Sử dụng DISTINCT để loại bỏ các giá trị trùng lặp
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int xDelivery_person_id = rs.getInt("delivery_person_id");
                java.sql.Timestamp xDelivery_date = rs.getTimestamp("delivery_date");
                String formattedDate = sdf.format(xDelivery_date);
                deliveryDatesSet.add(formattedDate);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        deliveryDates.addAll(deliveryDatesSet); // Chuyển LinkedHashSet sang ArrayList

        // Chuyển ArrayList thành một chuỗi JSON định dạng mong muốn
        StringBuilder jsonBuilder = new StringBuilder("[");
        for (int i = 0; i < deliveryDates.size(); i++) {
            jsonBuilder.append("'").append(deliveryDates.get(i)).append("'");
            if (i < deliveryDates.size() - 1) {
                jsonBuilder.append(", ");
            }
        }
        jsonBuilder.append("]");

        // In ra chuỗi JSON kết quả
        return jsonBuilder.toString();
    }

    public int[] getDeliveryMaxMin(int id) {
        String xSql = "SELECT delivery_person_id, start_time, end_time FROM Delivery where delivery_person_id = ?";
        int minDeliveryTime = Integer.MAX_VALUE; // Khởi tạo với giá trị lớn nhất
        int maxDeliveryTime = Integer.MIN_VALUE; // Khởi tạo với giá trị nhỏ nhất

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int xDelivery_person_id = rs.getInt("delivery_person_id");
                java.sql.Timestamp xStart_time = rs.getTimestamp("start_time");
                java.sql.Timestamp xEnd_time = rs.getTimestamp("end_time");

                // Tính toán khoảng thời gian nếu có đủ dữ liệu
                int deliveryTime = -1; // Mặc định -1 nếu không đủ dữ liệu
                if (xStart_time != null && xEnd_time != null) {
                    deliveryTime = calculateDeliveryTime(xStart_time, xEnd_time);

                    // Cập nhật thời gian ít nhất
                    if (deliveryTime >= 0 && deliveryTime < minDeliveryTime) {
                        minDeliveryTime = deliveryTime;
                    }
                    // Cập nhật thời gian tối đa
                    if (deliveryTime >= 0 && deliveryTime > maxDeliveryTime) {
                        maxDeliveryTime = deliveryTime;
                    }
                }
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Trả về thời gian tối thiểu và tối đa
        return new int[]{minDeliveryTime, maxDeliveryTime};
    }

    public int calculateDeliveryTime(java.sql.Timestamp start_time, java.sql.Timestamp end_time) {
        if (start_time != null && end_time != null) {
            LocalDateTime startTime = start_time.toLocalDateTime();
            LocalDateTime endTime = end_time.toLocalDateTime();
            Duration duration = Duration.between(startTime, endTime);
            return (int) duration.toMinutes();
        } else {
            return -1; // Trả về -1 nếu không có đủ dữ liệu
        }
    }

    public void insertShipper(int id) {
        xSql = "INSERT INTO [dbo].[Delivery_person]"
                + "([account_id]"
                + "VALUES(?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public List<Delivery> getDelivery() {
        List<Delivery> t = new ArrayList<>();
        xSql = "select * from Delivery";
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
    
    public static void main(String[] args) {
        DeliveryDAO d = new DeliveryDAO();
//        List<String> ld = d.getDeliveryDatesSorted();
//        if (ld == null) {
//            System.out.println("List empty");
//        } else {
//            for (String dx : ld) {
//                System.out.println(dx);
//            }
//        }
        System.out.println(d.getDeliveryPersonIdByUsername("vupl"));
    }
}
