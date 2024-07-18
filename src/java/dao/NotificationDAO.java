package dao;

import java.sql.SQLException;
import model.Notification;
import java.util.*;

public class NotificationDAO extends MyDAO {

    public Notification getNotificationByID(int id) {
        xSql = "select * from Notification where id =? ";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                String description = rs.getString("description");
                int orderId = rs.getInt("order_id");
                return new Notification(id, description, orderId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insetNotification(Notification no) {
        xSql = "INSERT INTO [dbo].[Notification]\n"
                + "           ([descripsion]\n"
                + "           ,[order_id])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, no.getDescripsion());
            ps.setInt(2, no.getOrder_id());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateNotification(Notification no) {
        xSql = "UPDATE [dbo].[Notification]\n"
                + "   SET [descripsion] = ?\n"
                + "      ,[order_id] = ?\n"
                + " WHERE id =? ";
    }

    public String getNotificationByOrderID(int orderId) {
        xSql = "select * from Notification where order_id =? ";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, orderId);
            rs = ps.executeQuery();
            while (rs.next()) {
                String description = rs.getString("descripsion");
                return description;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Notification> getListNoti() {
        xSql = "select * from notification";
        List<Notification> ln = new ArrayList<>();
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String descripsion = rs.getString("descripsion");
                int orderId = rs.getInt("order_id");
                Notification no =new Notification(id, descripsion, orderId);
                ln.add(no);
            }
            return ln;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ln;
    }
    
    public List<Notification> getListNotification(int id) {
        List<Notification> list = new ArrayList<>();
        String xSql = "select d.order_id, d.image, n.descripsion, n.notice_time from Notification n\n"
                + "join [order] o ON o.id = n.order_id\n"
                + "join [delivery] d ON d.order_id = o.id\n"
                + "where d.delivery_person_id = ?\n"
                + "order by n.notice_time DESC";
        Notification n;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                String xImage = rs.getString("image");
                String xDescription = rs.getString("descripsion");
                java.sql.Timestamp xNotice_time = rs.getTimestamp("notice_time");
                int xOrder_id = rs.getInt("order_id");
                n = new Notification(xDescription, xNotice_time, xOrder_id, xImage);
                list.add(n);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Notification getNoticeByOrderId(int order_id) {
        xSql = "select * from notification where order_id = ?";
        Notification x = null;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, order_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int xId = rs.getInt("id");
                String xDescription = rs.getString("descripsion");
                java.sql.Timestamp xNotice_time = rs.getTimestamp("notice_time");
                int xOrder_id = rs.getInt("order_id");
                x = new Notification(xId, xDescription, xNotice_time, xOrder_id);

            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return x;
    }

    public void InsertNotice(String des, int order_id) {
        xSql = "insert into Notification (descripsion, order_id, notice_time) values(?,?, CONVERT(VARCHAR(19), GETDATE(), 120))";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, des);
            ps.setInt(2, order_id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        NotificationDAO nd = new NotificationDAO();
//        List<Notification> ln = nd.getListNoti();
//        for (Notification notification : ln) {
//            System.out.println(notification);
//        }
        String no =nd.getNotificationByOrderID(2);
        System.out.println(no);
    }
}
