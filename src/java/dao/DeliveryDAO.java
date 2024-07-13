/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

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
                java.sql.Date xDelivery_date = rs.getDate("delivery_date");
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

    public List<Delivery> getDeliveryByStatus() {
        List<Delivery> t = new ArrayList<>();
        xSql = "SELECT * FROM [Delivery] WHERE [status] IN ('Đang giao',N'Đã giao', N'Không giao được');";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int xId = rs.getInt("id");
                int xOrder_id = rs.getInt("order_id");
                int xDelivery_person_id = rs.getInt("delivery_person_id");
                int xShip_price = rs.getInt("ship_price");
                java.sql.Date xDelivery_date = rs.getDate("delivery_date");
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
        List<Delivery> ld = d.getDeliveryByStatus();
        if (ld == null) {
            System.out.println("List empty");
        } else {
            for (Delivery dx : ld) {
                System.out.println(dx);

            }
        }
    }
}
