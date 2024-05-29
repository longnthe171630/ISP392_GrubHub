/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import model.Account;

/**
 *
 * @author Long1
 */
public class AccountDAO extends MyDAO {
    
    //kiểm tra tài khoản
    public Account checkAccount(String username, String password) {
        String sql = "SELECT * FROM Account where username = ? and password = ?";
        //String fullname = null;

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            rs = ps.executeQuery();
            if (rs.next()) {
                return new Account(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getString("phonenumber"), rs.getInt("role"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    //đăng kí
    public void register(Account a) {
        String sql = "INSERT INTO [dbo].[Account]\n"
                + "           ([username]\n"
                + "           ,[password]\n"
                + "           ,[email]\n"
                + "           ,[phonenumber]\n"
                + "           ,[role])\n"
                + "             VALUES\n"
                + "           (?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, a.getUsername());
            st.setString(2, a.getPassword());
            st.setString(3, a.getEmail());
            st.setString(4, a.getPhonenumber());
            st.setInt(5, a.getRole());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //đổi mật khẩu
    public void changePassword(String username, String newPassword) {
        String sql = "UPDATE [dbo].[Account]\n"
                + "   SET [password] = ?\n"
                + " WHERE [username]=?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, newPassword);
            st.setString(2, username);

            //Thực hiện truy vấn
            int rowsAffected = st.executeUpdate();
            //Nếu có ít nhất 1 hàng được thêm vào, in t.báo thành công!
            if (rowsAffected > 0) {
                System.out.println("Password changed successfully.");
            } else {
                System.out.println("Failed to change password.");
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while changing password: " + e.getMessage());
        } finally {
            // Đảm bảo các tài nguyên được đóng sau khi sử dụng
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

//kiểm tra trùng username
//    public boolean existedUser(String username) {
//        String sql = "SELECT [username]\n"
//                + "      ,[password]\n"
//                + "      ,[email]\n"
//                + "      ,[phonenumber]\n"
//                + "      ,[role]\n"
//                + "  FROM [dbo].[Account] where username=?";
//        try {
//            PreparedStatement st = connection.prepareStatement(sql);
//            st.setString(1, username);
//            ResultSet rs = st.executeQuery();
//            if (rs.next()) {
//                return true;
//            }
//        } catch (Exception e) {
//        }
//        return false;
//    }
//kiểm tra trùng email
//    public boolean existedEmail(String email) {
//        String sql = "SELECT [username]\n"
//                + "      ,[password]\n"
//                + "      ,[email]\n"
//                + "      ,[phonenumber]\n"
//                + "      ,[role]\n"
//                + "  FROM [dbo].[Account] where email=?";
//        try {
//            PreparedStatement st = connection.prepareStatement(sql);
//            st.setString(1, email);
//            ResultSet rs = st.executeQuery();
//            if (rs.next()) {
//                return true;
//            }
//        } catch (Exception e) {
//        }
//        return false;
//    }
//kiểm tra trùng số điện thoại
//    public boolean existedPhoneNumber(String phonenumber) {
//        String sql = "SELECT [username]\n"
//                + "      ,[password]\n"
//                + "      ,[email]\n"
//                + "      ,[phonenumber]\n"
//                + "      ,[role]\n"
//                + "  FROM [dbo].[Account] where phonenumber=?";
//        try {
//            PreparedStatement st = connection.prepareStatement(sql);
//            st.setString(1, phonenumber);
//            ResultSet rs = st.executeQuery();
//            if (rs.next()) {
//                return true;
//            }
//        } catch (Exception e) {
//        }
//        return false;
//    }
    public Account getAccountByEmail(String email) {
        String sql = "SELECT * FROM Account WHERE email = ?";
        int xId;
        String xUsername, xPassword, xEmail, xPhonenumber;
        int xRole;

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Chuẩn bị câu lệnh SQL
            ps = connection.prepareStatement(sql);
            ps.setString(1, email);  // Đặt giá trị cho tham số email
            rs = ps.executeQuery();

            // Lấy dữ liệu từ kết quả truy vấn
            if (rs.next()) {
                xId = rs.getInt("id");
                xUsername = rs.getString("username");
                xPassword = rs.getString("password");
                xEmail = rs.getString("email");
                xPhonenumber = rs.getString("phonenumber");
                xRole = rs.getInt("role");
                return new Account(xId, xUsername, xPassword, xEmail, xPhonenumber, xRole);
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void insertToken(int id, String token) {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

        // Add 30 minutes to the current timestamp
        long millisecondsIn30Minutes = 30 * 60 * 1000;
        Timestamp next30MinutesTimestamp = new Timestamp(currentTimestamp.getTime() + millisecondsIn30Minutes);
        String sql = "UPDATE Account SET token = ?, expiretime = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, token);
            statement.setTimestamp(2, new Timestamp(currentTimestamp.getTime())); // assume expiretime is a java.util.Date or java.sql.Timestamp
            statement.setInt(3, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ResetToken(int id) {
        String xSql = "UPDATE account SET token = '' WHERE id = ?";
        
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            ps.executeUpdate();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String checkToken(String email, String token) {
        String xSql = "SELECT * FROM account WHERE email = ? AND token = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, email);
            ps.setString(2, token);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("email");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        AccountDAO accountDAO = new AccountDAO();

        // Kiểm thử hàm getAccountByEmail
        String emailToTest = "icon0690@gmail.com";
        Account account = accountDAO.getAccountByEmail(emailToTest);

        if (account != null) {
            System.out.println("Account found: " + account);
        } else {
            System.out.println("No account found with email: " + emailToTest);
        }

    }
}
