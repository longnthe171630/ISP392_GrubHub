package dao;

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

    public static void main(String[] args) {
        AddressDAO ad = new AddressDAO();
        Address a = new Address(1, "thon 3 ", "thach hoa", "thach that");

        // Tạo địa chỉ mới
        ad.createAddress(a);

        // Kiểm tra xem địa chỉ mới đã được thêm vào chưa
        Address result = ad.getAddress(a);

        if (result != null) {
            System.out.println("Address created successfully:");
            System.out.println("ID: " + result.getId() + ", Details: " + result.getDetails() + ", State: " + result.getState() + ", Street: " + result.getStreet());
        } else {
            System.out.println("Failed to create address.");
        }
    }
}
