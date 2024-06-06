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
        xSql = "select * from [Delivery]";
        int xId;
        int xAddress_Id;
        float xShip_price;
        int xOrder_Id;
        java.sql.Date xDelivery_date;
        String xStatus;
        int xDelivery_person;
        int xAccount_id;
        Delivery x;
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                xId = rs.getInt("id");
                xAddress_Id = rs.getInt("address_id");
                xShip_price = rs.getFloat("ship_price");
                xOrder_Id = rs.getInt("order_id");
                xDelivery_date = rs.getDate("delivery_date");
                xStatus = rs.getString("status");
                xDelivery_person = rs.getInt("delivery_person");
                xAccount_id = rs.getInt("account_id");
                x = new Delivery(xId, xAddress_Id, xShip_price, xOrder_Id, xDelivery_date, xStatus, xDelivery_person, xAccount_id);
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
