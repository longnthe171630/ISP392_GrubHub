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
import java.util.Date;
import java.util.List;
import model.Account;

/**
 *
 * @author Long1
 */
public class AccountDAO extends MyDAO {

   

    public int getAddressIdByAccId(int accID) {
        xSql = "select address_id from [dbo].[Account] where id =?";
        int id = 0;

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, accID);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            return id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public int getAccountID(String username) {
        xSql = "SELECT [id] from [dbo].[Account] where username = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void insertAccount(Account a) throws SQLException {
        xSql = "INSERT INTO Account (username, password, email, phonenumber, role) VALUES (?, ?, ?, ?, ?)";

        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, a.getUsername());
            ps.setString(2, a.getPassword());
            ps.setString(3, a.getEmail());
            ps.setString(4, a.getPhonenumber());
            ps.setInt(5, a.getRole());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getIdLastAccount() {
        xSql = "SELECT TOP 1 * FROM account ORDER BY id DESC";
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

    public boolean checkEmail(String email) {

        xSql = "SELECT [email]\n"
                + "FROM [dbo].[Account]\n"
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

    //lấy thông tin tài khoản thông qua username
//    public Account getAccountInfo(String username) {
//        xSql = "SELECT [id],[username],[password],[email],[phonenumber],[role] FROM [dbo].[Account] where username =?";
//        try {
//            ps = con.prepareStatement(xSql);
//            ps.setString(1, username);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                Account a = new Account(rs.getInt(1),
//                        rs.getString(2),
//                        rs.getString(3),
//                        rs.getString(4),
//                        rs.getString(5),
//                        rs.getInt(6));
//
//                return a;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

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
    
     public List<Account> getListAccount() {
        List<Account> list = new ArrayList<>();
        String sql = "select * from Account where active=1";

        Account a;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                PreparedStatement st = con.prepareStatement(sql);
                int xId = rs.getInt("id");
                String xUsername = rs.getString("username");
                String xPassword = rs.getString("password");
                String xEmail = rs.getString("email");
                String xPhonenumber = rs.getString("phonenumber");
                int xRole = rs.getInt("role");
                a = new Account(xId, xUsername, xPassword, xEmail, xPhonenumber, xRole);
                list.add(a);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public void banAccount(int accountId) {
        String sql = "UPDATE Account SET active =0 where id= ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, accountId); //Thiếu dòng này để set giá trị cho dấu ?
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void unbanAccount(int accountId) {
        String sql = "UPDATE Account SET active =1 where id= ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, accountId); //Thiếu dòng này để set giá trị cho dấu ?
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<Account> getListBanedAccount() {
        List<Account> list = new ArrayList<>();
        String sql = "select * from Account where active=0";

        Account a;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                PreparedStatement st = con.prepareStatement(sql);
                int xId = rs.getInt("id");
                String xUsername = rs.getString("username");
                String xPassword = rs.getString("password");
                String xEmail = rs.getString("email");
                String xPhonenumber = rs.getString("phonenumber");
                int xRole = rs.getInt("role");
                a = new Account(xId, xUsername, xPassword, xEmail, xPhonenumber, xRole);
                list.add(a);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
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
        String sql = "UPDATE Account SET token = ?, token_time = ? WHERE id = ?";

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
        String xSql = "UPDATE [dbo].[Account] \n"
                + "SET token = NULL, token_time = NULL \n"
                + "WHERE id = ?;";

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

    //22/06 ---db mới----vupl
    public void addNewAccount(Account a) {
        xSql = "INSERT INTO [dbo].[Account]\n"
                + "           ([username]\n"
                + "           ,[password]\n"
                + "           ,[email]\n"
                + "           ,[phonenumber]\n"
                + "           ,[address_id]\n"
                + "           ,[role]\n"
                + "           ,[active]\n"
                + "           ,[image]\n"
                + "           ,[create_date])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,GETDATE())";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, a.getUsername());
            ps.setString(2, a.getPassword());
            ps.setString(3, a.getEmail());
            ps.setString(4, a.getPhonenumber());
            ps.setInt(5, a.getAddressID());
            ps.setInt(6, a.getRole());
            ps.setInt(7, a.getActive());
            ps.setString(8, a.getImg());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getIDLastAcc() {

        xSql = "SELECT TOP 1 * FROM account ORDER BY id DESC";
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

    public Account getAccountByEmail_VuPL(String email) {
        xSql = "select * from account where email = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String userName = rs.getString("username");
                String passWord = rs.getString("password");
                String phoneNumber = rs.getString("phonenumber");
                int addressID = rs.getInt("address_id");
                int role = rs.getInt("role");
                int active = rs.getInt("active");
                String img = rs.getString("image");
                Date createDate = rs.getDate("create_date");
                return new Account(id, userName, passWord, email,
                        phoneNumber, role, addressID, active, img, createDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateAccount(Account a) {
        xSql = "UPDATE [dbo].[Account]\n"
                + "   SET [username] = ?\n"
                + "      ,[password] = ?\n"
                + "      ,[email] = ?\n"
                + "      ,[phonenumber] = ?\n"
                + "      ,[address_id] = ?\n"
                + "      ,[role] = ?\n"
                + "      ,[active] = ?\n"
                + "      ,[image] = ?\n"
                + " WHERE id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, a.getUsername());
            ps.setString(2, a.getPassword());
            ps.setString(3, a.getEmail());
            ps.setString(4, a.getPhonenumber());
            ps.setInt(5, a.getAddressID());
            ps.setInt(6, a.getRole());
            ps.setInt(7, a.getActive());
            ps.setString(8, a.getImg());
            ps.setInt(9, a.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAcountById(int id) {
        xSql = "DELETE FROM [dbo].[Account]\n"
                + "      WHERE id =?";
        try {
            ps = con.prepareStatement(xSql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Account getAccountByID(int account_id) {
        xSql = "select * from Account where id =?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, account_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                
                String userName = rs.getString("username");
                String passWord = rs.getString("password");
                String phoneNumber = rs.getString("phonenumber");
                String email = rs.getString("email");
                int addressID = rs.getInt("address_id");
                int role = rs.getInt("role");
                int active = rs.getInt("active");
                String img = rs.getString("image");
                Date createDate = rs.getDate("create_date");
                Account account = new Account(account_id, userName, passWord, email, phoneNumber, role, addressID, active, img, createDate);
                return account;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        AccountDAO accountDAO = new AccountDAO();
//        Account a = new Account("vupl", "123", "ganhataox3@gmail.com", "0987654321", 2, 11, 1, null, null);
        int a = accountDAO.getIDLastAcc();
        System.out.println(a);

    }
}
