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
                + "WHERE [username] = ? OR [password] = ?";

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
        String xSql = "INSERT INTO [dbo].[Customer]\n"
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

        DateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            // Phân tích chuỗi ngày đầu vào
            java.util.Date utilDate = inputDateFormat.parse(c.getDob());

            // Chuyển đổi sang java.sql.Date
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            // Chuẩn bị câu lệnh SQL
            ps = con.prepareStatement(xSql);
            ps.setString(1, c.getName());
            ps.setDate(2, sqlDate);
            ps.setBoolean(3, c.isGender());
            ps.setInt(4, c.getAddress_id());
            ps.setString(5, c.getUserName());
            ps.setString(6, c.getPassWord());
            ps.setString(7, c.getEmail());
            ps.setString(8, c.getPhoneNumber());

            // Thực hiện câu lệnh SQL
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getIdLastCustomer() {

        xSql = "SELECT TOP 1 * FROM customer ORDER BY id DESC";
        try {
            int id;
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
                return id;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void insertToken(int customerId, String token) throws SQLException {
        xSql = "UPDATE customer SET token = ?, token_expiry = ? WHERE id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, token);
            // Set token expiry time (e.g., 24 hours from now)
            ps.setTimestamp(2, new Timestamp(System.currentTimeMillis() + 24 * 3600 * 1000));
            ps.setInt(3, customerId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isTokenValid(String email, String token) throws SQLException {
        xSql = "SELECT token, token_expiry FROM customer WHERE email = ?";
        try {
            ps.setString(1, email);
            rs = ps.executeQuery();

            if (rs.next()) {
                String storedToken = rs.getString("token");
                Timestamp tokenExpiry = rs.getTimestamp("token_expiry");
                if (storedToken != null && storedToken.equals(token) && tokenExpiry.after(new Timestamp(System.currentTimeMillis()))) {
                    return true;

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void verifyEmail(String email) throws SQLException {
        xSql = "UPDATE customer SET email_verified = 1 WHERE email = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, email);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void ResetToken(int id) {
        String xSql = "UPDATE customer SET token = '' WHERE id = ?";
        
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            ps.executeUpdate();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

// test function
    public static void main(String[] args) {
        // Chuỗi ngày tháng có định dạng "dd-MM-yyyy"
        CustomerDAO cd = new CustomerDAO();
        System.out.println(cd.getIdLastCustomer());
    }
}
