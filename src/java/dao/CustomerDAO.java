package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Account;
import model.Customer;

public class CustomerDAO extends MyDAO {
    
    public String getNameByAccID(int accID){
        xSql = "SELECT [name] from Customer where account_id=?";
        String customerName = null;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, accID);
            rs = ps.executeQuery();
            while (rs.next()){
                    customerName = rs.getString("name");           
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerName;
    }

    
        public Account getAccountByCustomerId(int customerId) {
        xSql = "SELECT a.id, a.email FROM [Account] a JOIN [Customer] c ON a.id = c.account_id WHERE c.id = ?";
        Account account = null;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, customerId);
            rs = ps.executeQuery();
            if (rs.next()) {
                account = new Account();
                account.setId(rs.getInt("id"));
                account.setEmail(rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }
        public List<Customer> getListCustomer(){
        List<Customer> list = new ArrayList<>();
       String sql = "select*from Customer";
       try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
               int xId= rs.getInt("id");
                String xName= rs.getString("name");
                String xDob= rs.getString("dob");
                Boolean xGender= rs.getBoolean("gender");
                Customer c= new Customer(xId, xName, xDob, xGender);
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public int getAddressIdByCusId(int cusID) {
        xSql = "select address_id from [dbo].Customer where id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, cusID);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getCusIdByUsername(String username) {
        xSql = "select id from [dbo].[Customer] where username = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public Customer checkCustomer(String username, String password) {

        xSql = "SELECT [username], [password]\n"
                + "FROM [dbo].[Customer]\n"
                + "WHERE [username] = ? And [password] = ?";

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
                + "           ,[account_id])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?))";

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
            ps.setInt(4, c.getAccountID());
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

    public Customer getCustomerByAccID(int accID) {
        xSql = "SELECT [id]\n"
                + "      ,[name]\n"
                + "      ,[dob]\n"
                + "      ,[gender]\n"
                + "      ,[account_id]\n"
                + "  FROM [dbo].[Customer] where [account_id] =?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, accID);
            rs = ps.executeQuery();
            while (rs.next()) {                
                Customer c = new Customer(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getBoolean(4),
                        rs.getInt(5));
                return c;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Customer getCustomer(String username) {
        xSql = "SELECT [username]\n"
                + "      ,[password]\n"
                + "      ,[email]\n"
                + "      ,[phonenumber]\n"
                + "      ,[dob]\n"
                + "      ,[gender]\n"
                + "  FROM [dbo].[Customer]\n"
                + "  where username = ? ";
        DateFormat inputDate = new SimpleDateFormat("yyyy-MM-dd");

        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, username);
            rs = ps.executeQuery();

            while (rs.next()) {
                Customer c = new Customer(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getBoolean(6));
                return c;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

        return null;

    }
    
    public void updateCustomer(Customer c)throws SQLException{
       xSql ="UPDATE Customer SET name = ?,dob = ?,gender = ? where id=?";
       DateFormat inputDate = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date utilDate = inputDate.parse(c.getDob());
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            ps = con.prepareStatement(xSql);
            ps.setString(1, c.getName());
            ps.setDate(2, sqlDate);
            ps.setBoolean(3, c.isGender());
            ps.setInt(4, c.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
       
    }

    public void updateUser(Customer a) throws SQLException {
        xSql = "UPDATE Customer "
                + "SET email = ?, "
                + "phonenumber = ?, "
                + "dob = ?, "
                + "gender = ? "
                + "WHERE username = ?";
        DateFormat inputDate = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date utilDate = inputDate.parse(a.getDob());

            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            ps = con.prepareStatement(xSql);
            ps.setString(1, a.getEmail());
            ps.setString(2, a.getPhoneNumber());
            ps.setDate(3, sqlDate);
            ps.setBoolean(4, a.isGender());
            ps.setString(5, a.getUserName());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Customer getCustomerByID(int id) {
        xSql = "SELECT [username]\n"
                + "          ,[name]"
                + "      ,[password]\n"
                + "      ,[email]\n"
                + "      ,[phonenumber]\n"
                + "      ,[dob]\n"
                + "      ,[gender]\n"
                + "  FROM [dbo].[Customer]\n"
                + "  where id = ? ";
        DateFormat inputDate = new SimpleDateFormat("yyyy-MM-dd");

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                Customer c = new Customer(
                        rs.getString(2),
                        rs.getString(1),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getBoolean(7));
                return c;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

        return null;

    }

    public Customer getCustomerByID_VuPL(int id) {
        xSql = "SELECT * "
                + "FROM [dbo].[Customer] "
                + "WHERE id = ?";

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String dob = rs.getString("dob");
                boolean gender = Boolean.parseBoolean(rs.getString("gender"));
                int accountID = rs.getInt("account_id");

                Customer customer = new Customer(id, name, dob, gender, accountID);
                return customer;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any exceptions thrown during SQL execution or ResultSet handling
        }

        // Return null if no customer with the given id was found
        return null;
    }
    //function mới 22/06

    public void addNewCus(Customer cus) throws ParseException {
        xSql = "INSERT INTO [dbo].[Customer]\n"
                + "           ([name]\n"
                + "           ,[dob]\n"
                + "           ,[gender]\n"
                + "           ,[account_id])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        DateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            // Phân tích chuỗi ngày đầu vào
            java.util.Date utilDate = inputDateFormat.parse(cus.getDob());

            // Chuyển đổi sang java.sql.Date
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            // Chuẩn bị câu lệnh SQL
            ps = con.prepareStatement(xSql);
            ps.setString(1, cus.getName());
            ps.setDate(2, sqlDate);
            ps.setBoolean(3, cus.isGender());
            ps.setInt(4, cus.getAccountID());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteCustomerByAccountId(int accountId) {
        xSql = "DELETE FROM [dbo].[Customer]\n"
                + "      WHERE account_id =?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, accountId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public int getCusIdByAccId(int accID){
        xSql = "select id from Customer where account_id = ? ";
        int id = 0;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, accID);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
    
//     public Map<Integer, String> getCustomerNamesByIDs(List<Integer> customerIDs)throws SQLException{
//        Map<Integer, String> customerNames = new HashMap<>();
//        if (customerIDs.isEmpty()) return customerNames;
//
//        StringBuilder sql = new StringBuilder("SELECT id, name FROM Customer WHERE id IN (");
//        for (int i = 0; i < customerIDs.size(); i++) {
//            sql.append("?");
//            if (i < customerIDs.size() - 1) {
//                sql.append(", ");
//            }
//        }
//        sql.append(")");
//
//        try {
//            ps = con.prepareStatement(sql.toString());
//            for (int i = 0; i < customerIDs.size(); i++) {
//                ps.setInt(i + 1, customerIDs.get(i));
//            }
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                customerNames.put(rs.getInt("id"), rs.getString("name"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (rs != null) rs.close();
//                if (ps != null) ps.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return customerNames;
//    }

    public static void main(String[] args) {
        CustomerDAO cd = new CustomerDAO();
        int accid = 4;
        cd.deleteCustomerByAccountId(16);
        System.out.println("done");
    }
}
