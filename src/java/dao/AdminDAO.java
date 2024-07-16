/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.Order;

/**
 *
 * @author Dell
 */
public class AdminDAO extends MyDAO {

    public int countOrder() {
        String sql = "select COUNT(id) from [Order]";
        int count = 0;
        try { 
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return count;
    }
    public int countAccount(){
         String sql = "select COUNT(id) from Account";
        int count = 0;
        try { 
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return count;
    }
     public static void main(String[] args) {
        AdminDAO adminDAO = new AdminDAO();
        int orderCount = adminDAO.countOrder();
        int accountCount= adminDAO.countAccount();
        // In ra kết quả nếu cần thiết
        System.out.println("The number of orders is: " + orderCount);
                System.out.println("The number of orders is: " + accountCount );

    }

    }
    
    
