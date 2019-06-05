package cn.littleterry.java.jdbc.preparedstatusmentin;

import cn.littleterry.java.jdbc.DbConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 固定一定数量的占位符,不够使用null
 *
 * @author XTENG
 * @email tengxing7452@163.com
 * @date 18-4-28 下午4:22
 */
public class JDBCPreparedStatementNULL {

    private static final String QUERY = "SELECT id,name,email,country,password FROM Users where id in ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final int PARAM_SIZE = 10;

    public static void printData(int[] ids) {

        if (ids.length > PARAM_SIZE) {
            System.out.println("Maximum input size supported is " + PARAM_SIZE);
            return;
        }
        System.out.println("Query="+QUERY);
        Connection con = DbConn.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(QUERY);

            int i = 1;
            for (; i <= ids.length; i++) {
                ps.setInt(i, ids[i - 1]);
            }

            for (; i <= PARAM_SIZE; i++) {
                ps.setNull(i, java.sql.Types.INTEGER);
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("User ID=" + rs.getInt("id") + ", Name=" + rs.getString("name"));
            }

            try {
                rs.close();
            } catch (SQLException e) {
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
