/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import model.DeliveryPerson;

/**
 *
 * @author Long1
 */
public class DeliveryPersonDAO extends MyDAO{
    public int getIdByAccId(int accID) {
        xSql = "select id from Delivery_person where account_id = ?";
        int id = 0;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, accID);
            rs = ps.executeQuery();
            if (rs.next()) {
                return id = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public DeliveryPerson getDeliveryPerson(int id) {
        xSql = "Select * from Delivery_person where id =?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                DeliveryPerson dp = new DeliveryPerson(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getBoolean(4),
                        rs.getInt(5));
                return dp;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void UpdateDeliverPrson(DeliveryPerson d) {
        xSql = "UPDATE Delivery_person SET name = ?,dob = ?,gender = ? where id=?";
        DateFormat inputDate = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date utilDate = inputDate.parse(d.getDob());
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            ps = con.prepareStatement(xSql);
            ps.setString(1, d.getName());
            ps.setDate(2, sqlDate);
            ps.setBoolean(3, d.isGender());
            ps.setInt(4, d.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int temp = 1;
        DeliveryPersonDAO dao = new DeliveryPersonDAO();
        DeliveryPerson dp = dao.getDeliveryPerson(temp);
        System.out.println(dp);
    }
}
