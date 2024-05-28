package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import model.Customer;

public class CustomerDAO extends MyDAO {

    //kiểm tra tài khoản, mật khẩu có trong db customer không.
    public Customer checkCustomer(String username, String password) {
        xSql = "SELECT [username]\n"
                + "      ,[password]\n"
                + "  FROM [dbo].[Customer] where username = ? and password = ?";
        //String fullname = null;

        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, username);
            ps.setString(2, password);

            rs = ps.executeQuery();
            if (rs.next()) {
                return new Customer(username, password);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    // thêm một tài khoản người mua mới vào db
    public void insertCustomer(Customer c) {
        xSql = "INSERT INTO [dbo].[Customer]\n"
                + "           ([name]\n"
                + "           ,[dob]\n"
                + "           ,[gender]\n"
                + "           ,[address_id]\n"
                + "           ,[username]\n"
                + "           ,[password]\n"
                + "           ,[email]\n"
                + "           ,[phonenumber]\n"
                + "           ,[create_date]\n"
                + "           ,[status])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,CURDATE()\n"
                + "           ,1)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, c.getName());
            ps.setDate(2, new java.sql.Date(c.getDob().getTime())); // Chuyển đổi từ java.util.Date sang java.sql.Date
            ps.setBoolean(3, c.isGender());
            ps.setInt(4, c.getAddress_id());
            ps.setString(5, c.getUserName());
            ps.setString(6, c.getPassWord());
            ps.setString(7, c.getEmail());
            ps.setString(8, c.getPhoneNumber());
            ps.setDate(9, java.sql.Date.valueOf(LocalDate.now())); // Ngày hiện tại

            ps.executeUpdate();
            ps.close(); // Đóng PreparedStatement sau khi sử dụng
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
