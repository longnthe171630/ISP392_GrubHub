/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import model.Feedback;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dell
 */
public class FeedbackDAO extends MyDAO {

    public List<Feedback> getListFeedback() {
        List<Feedback> list = new ArrayList<>();
        try {
            String sql = "select f.id, c.name, r.name,f.order_id, f.value,f.description\n"
                    + "from Feedback f\n"
                    + "join Customer c on c.id= f.customer_id\n"
                    + "join Restaurant r on r.id= f.restaurant_id";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Feedback a = new Feedback(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6));
                list.add(a);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

}
