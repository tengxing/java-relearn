package cn.littleterry.java.jdbc.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * JDBC没有事务操作例子
 *
 * @author XTENG
 * @email tengxing7452@163.com
 * @date 18-5-3 下午4:01
 */
public class JDBCInsertExample {
    public static final String INSERT_EMPLOYEE_QUERY = "insert into Employee (empId, name) values (?,?)";

    public static void main(String[] args) {

        Connection con = null;
        try {
            con = DBConnection.getConnection();
            /**
             * 插入id为100
             */
            insertEmployeeData(con, 100, "name1");
            /**
             * 继续插入id为100(会报错:Duplicate entry '100' for key 'PRIMARY)
             */
            insertEmployeeData(con, 100, "name2");

        } catch (SQLException | ClassNotFoundException e) {
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

    public static void insertEmployeeData(Connection con, int empId,String name)
            throws SQLException {
        PreparedStatement stmt = con.prepareStatement(INSERT_EMPLOYEE_QUERY);
        stmt.setInt(1, empId);
        stmt.setString(2, name);

        stmt.executeUpdate();
        System.out.println("Employee Data inserted successfully for ID=" + empId);
        stmt.close();
    }

}
