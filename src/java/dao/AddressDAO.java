package dao;

import java.util.ArrayList;
import java.util.List;
import model.Address;

public class AddressDAO extends MyDAO {

    public Address getAddress(Address a) {
        xSql = "SELECT [id], [details], [state], [street]\n"
                + "  FROM [dbo].[Address]\n"
                + "  WHERE [details] = ?\n"
                + "    AND [state] = ?\n"
                + "    AND [street] = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, a.getDetails());
            ps.setString(2, a.getState());
            ps.setString(3, a.getStreet());
            rs = ps.executeQuery(); // Thực hiện truy vấn và lấy kết quả

            if (rs.next()) { // Kiểm tra nếu có hàng trả về
                return new Address(rs.getInt("id"), rs.getString("details"), rs.getString("state"), rs.getString("street"));
            }
        } catch (Exception e) {
            e.printStackTrace(); // In ra ngoại lệ nếu có lỗi
        }
        return null; // Trả về null nếu không có kết quả nào
    }

    public void createAddress(Address a) {
        xSql = "INSERT INTO [dbo].[Address]\n"
                + "           ([details]\n"
                + "           ,[state]\n"
                + "           ,[street])\n"
                + "     VALUES(\n"
                + "           ?\n"
                + "           ,?\n"
                + "           ,?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, a.getDetails());
            ps.setString(2, a.getState());
            ps.setString(3, a.getStreet());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Address getAddressByOrderId(int order_id) {
        String xSql = "select * from [Order] o\n"
                + "join customer c ON o.customer_id = c.id\n"
                + "join address a ON c.address_id = a.id\n"
                + "where o.id = ?;";

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, order_id);
            rs = ps.executeQuery(); // Thực hiện truy vấn và lấy kết quả

            if (rs.next()) {
                int id = rs.getInt("id");
                String details = rs.getString("details");
                String street = rs.getString("street");
                String state = rs.getString("state");

                return new Address(id, details, street, state);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Address> getAddress() {
        List<Address> t = new ArrayList<>();
        xSql = "select * from Address";
        int xId;
        String xDetails;
        String xStreet;
        String xState;
      
        Address x;
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                xId = rs.getInt("id");
                xDetails = rs.getString("details");
                xStreet = rs.getString("street");
                xState = rs.getString("state");
                
                x = new Address(xId, xDetails, xStreet, xState);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }
    
    public Address getAddressById(int id) {
        xSql = "SELECT [id], [details], [state], [street]\n"
                + "  FROM [dbo].[Address]\n"
                + "  WHERE [id] = ?\n";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery(); // Thực hiện truy vấn và lấy kết quả

            while (rs.next()) { // Kiểm tra nếu có hàng trả về
                Address a = new Address();
                a.setId(rs.getInt("id"));
                a.setDetails(rs.getString("details"));
                a.setState(rs.getString("state"));
                a.setStreet(rs.getString("street"));
                return a;

            }
        } catch (Exception e) {
            e.printStackTrace(); // In ra ngoại lệ nếu có lỗi
        }
        return null; // Trả về null nếu không có kết quả nào
    }
    
    public static void main(String[] args) {
        AddressDAO ad = new AddressDAO();
//        Address a = new Address(1, "thon 3 ", "thach hoa", "thach that");
        System.out.println(ad.getAddressByOrderId(2));
        // Tạo địa chỉ mới
//        ad.createAddress(a);
//
//        // Kiểm tra xem địa chỉ mới đã được thêm vào chưa
//        Address result = ad.getAddress(a);
//
//        if (result != null) {
//            System.out.println("Address created successfully:");
//            System.out.println("ID: " + result.getId() + ", Details: " + result.getDetails() + ", State: " + result.getState() + ", Street: " + result.getStreet());
//        } else {
//            System.out.println("Failed to create address.");
//        }
    }
}