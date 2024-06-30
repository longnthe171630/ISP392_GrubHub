package dao;

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
            ps.setString(1, no.getDescription());
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
