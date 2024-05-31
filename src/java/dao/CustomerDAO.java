package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import model.Customer;

public class CustomerDAO extends MyDAO {

    //kiểm tra tài khoản, mật khẩu có trong db customer không.
    public Customer checkCustomer(String username, String password) {

        xSql = "SELECT [username], [password]\n"
                + "FROM [dbo].[Customer]\n"
                + "WHERE [username] = ? AND [password] = ?";

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
    public boolean checkEmail(String email) {

        xSql = "SELECT [email]\n"
                + "FROM [dbo].[Customer]\n"
                + "WHERE [email]= ?";

        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, email);


            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    // thêm một tài khoản người mua mới vào db
    public void insertCustomer(Customer c) throws ParseException {
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
                + "           ,GETDATE()\n"
                + "           ,1)";
        DateFormat inputDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat outputDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            java.util.Date utilDate = inputDateFormat.parse(c.getDob());
            String formattedDateStr = outputDateFormat.format(utilDate);
            java.util.Date formattedUtilDate = outputDateFormat.parse(formattedDateStr);
            java.sql.Date sqlDate = new java.sql.Date(formattedUtilDate.getTime());
            // Format the date to the desired format
            ps = con.prepareStatement(xSql);
            ps.setString(1, c.getName());
//            ps.setDate(2, new java.sql.Date(c.getDob().getTime())); // Chuyển đổi từ java.util.Date sang java.sql.Date
            ps.setDate(2, sqlDate); // Chuyển đổi từ java.util.Date sang java.sql.Date
            ps.setBoolean(3, c.isGender());
            ps.setInt(4, c.getAddress_id());
            ps.setString(5, c.getUserName());
            ps.setString(6, c.getPassWord());
            ps.setString(7, c.getEmail());
            ps.setString(8, c.getPhoneNumber());
            ps.executeUpdate();
            ps.close(); // Đóng PreparedStatement sau khi sử dụng
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

   
// test function

    public static void main(String[] args) {
        // Chuỗi ngày tháng có định dạng "dd-MM-yyyy"
        CustomerDAO cd = new CustomerDAO();
        String name = "vu long pham";
        String userName = "vupl";
        String passWord = "123";
        String email = "vupl123@gmail.com";
        String phoneNumber = "012345";
        Boolean gender = true;
        int address_id = 1;
        String dobStr = "11/01/2003";

    }
}
