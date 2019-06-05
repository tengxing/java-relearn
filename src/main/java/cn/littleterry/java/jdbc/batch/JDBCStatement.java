package cn.littleterry.java.jdbc.batch;

import cn.yjxxclub.java.jdbc.DbConn;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Statement 普通插入
 * Time Taken=14666
 *
 * @author XTENG
 * @email tengxing7452@163.com
 * @date 18-4-29 上午11:17
 */
public class JDBCStatement {

    public static void main(String[] args) {

        Connection con = null;
        Statement stmt = null;

        try {
            con = DbConn.getConnection();
            stmt = con.createStatement();

            long start = System.currentTimeMillis();
            for (int i = 0; i < 10000; i++) {
                String query = "insert into Employee values (" + i + ",'Name" + i + "')";
                stmt.execute(query);
            }
            System.out.println("Time Taken=" + (System.currentTimeMillis() - start));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
