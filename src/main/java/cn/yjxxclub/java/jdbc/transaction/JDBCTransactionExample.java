package cn.yjxxclub.java.jdbc.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * JDBC事务操作例子
 *
 * @author XTENG
 * @email tengxing7452@163.com
 * @date 18-5-3 下午4:08
 */
public class JDBCTransactionExample {
    public static final String INSERT_EMPLOYEE_QUERY = "insert into Employee (empId, name) values (?,?)";

    public static void main(String[] args) {

        Connection con = null;
        try {
            con = DBConnection.getConnection();

            /**
             * 关闭自动提交
             */
            con.setAutoCommit(false);


            insertEmployeeData(con, 100, "name1");
            insertEmployeeData(con, 100, "name2");

            /**
             * 手动提交
             */
            con.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                /**
                 * 回滚
                 */
                con.rollback();
                System.out.println("JDBC Transaction rolled back successfully");
            } catch (SQLException e1) {
                System.out.println("SQLException in rollback" + e1.getMessage());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void insertEmployeeData(Connection con, int empId, String name)
            throws SQLException {
        PreparedStatement stmt = con.prepareStatement(INSERT_EMPLOYEE_QUERY);
        stmt.setInt(1, empId);
        stmt.setString(2, name);

        stmt.executeUpdate();
        System.out.println("Employee Data inserted successfully for ID=" + empId);
        stmt.close();
    }

}
