/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Order;

/**
 *
 * @author Long1
 */
public class OrderDAO extends MyDAO {
    
        public List<Order> getOrder() {
        List<Order> t = new ArrayList<>();
        xSql = "select * from [Order]";
        int xId;
        int xRestaurant_Id;
        int xDelivery_Id;
        int xCustomer_Id;
        int xTotal_amount;
        String xStatus;
        java.sql.Date xOrder_date;
        Order x;
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                xId = rs.getInt("id");
                xRestaurant_Id = rs.getInt("restaurant_id");
                xCustomer_Id = rs.getInt("customer_id");
                xDelivery_Id = rs.getInt("delivery_id");
                xTotal_amount = rs.getInt("total_amount");
                xStatus = rs.getString("status");
                xOrder_date = rs.getDate("order_date");
                
                x = new Order(xId, xRestaurant_Id,xCustomer_Id, xDelivery_Id,  xTotal_amount, xStatus, xOrder_date);
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
        OrderDAO d = new OrderDAO();
        List<Order> lo = d.getOrder();
        if (lo == null) {
            System.out.println("List empty");
        } else {
            for (Order o : lo) {
                System.out.println(o);
                
            }
        }
    }
}
