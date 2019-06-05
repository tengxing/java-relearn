package cn.littleterry.java.jdbc.preparedstatusmentin;

import cn.littleterry.java.jdbc.DbConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 单个sql循环执行
 *
 * @author XTENG
 * @email tengxing7452@163.com
 * @date 18-4-28 下午3:58
 */
public class JDBCPreparedStatementSingle {

    private static final String QUERY = "SELECT id,name,email,country,password FROM Users where id =  ?";

    public static void printData(int[] ids) {
        Connection con = DbConn.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(QUERY);

            for (int empid : ids) {
                ps.setInt(1, empid);
                rs = ps.executeQuery();

                while (rs.next()) {
                    System.out.println("User ID="+rs.getInt("id")+", Name="+rs.getString("name"));
                }

                try {
                    rs.close();
                } catch (SQLException e) {
                }
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