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
public class DeliveryDAO extends MyDAO{
    
    public List<Delivery> getDelivery() {
        List<Delivery> t = new ArrayList<>();
        xSql = "select * from Delivery";
        int xId;
        int xAddress_Id;
        int xDelivery_person_id;
        float xShip_price;
        java.sql.Date xDelivery_date;
        String xStatus;
        String xImage;
        
        Delivery x;
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                xId = rs.getInt("id");
                xAddress_Id = rs.getInt("address_id");
                xDelivery_person_id = rs.getInt("delivery_person_id");
                xShip_price = rs.getFloat("ship_price");
                xDelivery_date = rs.getDate("delivery_date");
                xStatus = rs.getString("status");
                xImage = rs.getString("image");
                x = new Delivery(xId, xAddress_Id, xDelivery_person_id, xShip_price, xDelivery_date,xStatus, xImage);
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
        List<Delivery> ld = d.getDelivery();
        if (ld == null) {
            System.out.println("List empty");
        } else {
            for (Delivery dx : ld) {
                System.out.println(dx);
                
            }
        }
    }
}
