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

    public List<Delivery> getListDelivery() {
        List<Delivery> list = new ArrayList<>();
        try {
            String sql = "select d.id, d.order_id, dp.name,d. ship_price, d.delivery_date, d.status\n"
                    + "from Delivery d\n"
                    + "join Delivery_person dp on dp.id= d.delivery_person_id";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Delivery a = new Delivery(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getDate(5),
                        rs.getString(6));
                list.add(a);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
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
