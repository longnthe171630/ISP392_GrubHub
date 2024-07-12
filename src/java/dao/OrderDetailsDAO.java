/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import model.OrderDetails;

/**
 *
 * @author Long1
 */
public class OrderDetailsDAO extends MyDAO{
    
    public List<OrderDetails> getOrderDetails() {
        List<OrderDetails> t = new ArrayList<>();
        xSql = "select * from [OrderDetails]";
        int xId;
        int xOrder_Id;
        int xProduct_Id;
        int xQuantity;
        float xPrice;
        OrderDetails x;
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                xId = rs.getInt("id");
                xOrder_Id = rs.getInt("order_id");
                xProduct_Id = rs.getInt("product_id");
                xQuantity = rs.getInt("quantity");
                xPrice = rs.getFloat("price");
                x = new OrderDetails(xId, xOrder_Id, xProduct_Id, xQuantity, xPrice);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }
    
    public List<OrderDetails> getOrderDetailsByOrder_Locct(int orderID){
        List<OrderDetails> list = new ArrayList<>();
        xSql = "SELECT * FROM OrderDetails WHERE order_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, orderID);
            rs = ps.executeQuery();
            while (rs.next()) {                
                list.add(new OrderDetails(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getInt(5)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public OrderDetails getOrderDetailsByOrder(int order_id){
        String sql = "SELECT * FROM [OrderDetails] where [order_id] = ?";
        int xId, xOrder_id, xProduct_id, xQuantity;
        float xPrice;
             
        try {
            // Chuẩn bị câu lệnh SQL
            ps = connection.prepareStatement(sql);
            ps.setInt(1, order_id);  // Đặt giá trị cho tham số order_id
            rs = ps.executeQuery();

            // Lấy dữ liệu từ kết quả truy vấn
            if (rs.next()) {
                xId = rs.getInt("id");
                xOrder_id = rs.getInt("order_id");
                xProduct_id = rs.getInt("product_id");
                xQuantity = rs.getInt("quantity");
                xPrice = rs.getFloat("price");
  
                return new OrderDetails(xId, xOrder_id, xProduct_id, xQuantity, xPrice);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đảm bảo các tài nguyên được đóng sau khi sử dụng
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    public static void main(String[] args) {
        OrderDetailsDAO o = new OrderDetailsDAO();
        List<OrderDetails> lo = o.getOrderDetailsByOrder_Locct(1);
        System.out.println(lo);
       
    }
}
