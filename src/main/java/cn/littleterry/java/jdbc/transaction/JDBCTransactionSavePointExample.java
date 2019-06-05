package cn.littleterry.java.jdbc.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;

/**
 * SavePoint半回滚例子
 *
 * @author XTENG
 * @email tengxing7452@163.com
 * @date 18-5-4 下午2:06
 */
public class JDBCTransactionSavePointExample {
    public static final String INSERT_EMPLOYEE_QUERY = "insert into Employee (empId, name) values (?,?)";

    public static void main(String[] args) {

        Connection con = null;
        Savepoint savepoint = null;
        try {
            con = DBConnection.getConnection();

            /**
             * 关闭自动提交
             */
            con.setAutoCommit(false);

            insertEmployeeData(con, 90, "name90");
            insertEmployeeData(con, 91, "name91");

            // 如果能够执行到这就打一个点
            savepoint = con.setSavepoint("EmployeeSavePoint");
            insertEmployeeData(con, 91, "name91");

            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (savepoint == null) {
                    //回滚所有
                    con.rollback();
                    System.out.println("JDBC Transaction rolled back successfully");
                } else {
                    //半回滚(回滚到一个point)
                    con.rollback(savepoint);
                    //并且提交
                    con.commit();
                    System.out.println("JDBC Transaction rolled back endPoint:"+savepoint.getSavepointName());
                }
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
