/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Notification;
import java.sql.SQLException;
/**
 *
 * @author Long1
 */
public class NotificationDAO extends MyDAO{
    
    public Notification getNoticeByOrderId(int order_id){
        xSql = "select * from notification where order_id = ?";
        Notification x = null;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, order_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int xId = rs.getInt("id");
                String xDescription = rs.getString("descripsion");
                int xOrder_id = rs.getInt("order_id");
                x = new Notification(xId,xDescription, xOrder_id);
                
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return x;
    }
    
    public void InsertNotice(String des, int order_id){
        xSql = "insert into Notification values(?,?)";
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
        NotificationDAO d = new NotificationDAO();
//        List<Delivery> ld = d.getDeliveryByStatus();
//        if (ld == null) {
//            System.out.println("List empty");
//        } else {
//            for (Delivery dx : ld) {
//                System.out.println(dx);
//                
//            }
//        }
        System.out.println(d.getNoticeByOrderId(1));
    }
    
}
