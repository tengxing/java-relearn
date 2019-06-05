package cn.littleterry.java.jdbc.batch;

import cn.yjxxclub.java.jdbc.DbConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
* PreparedStatement 普通插入
* Time Taken=14666
*
* @author XTENG
* @email tengxing7452@163.com
* @date 18-4-29 上午11:19
*/
public class JDBCPreparedStatement {
   public static void main(String[] args) {

       Connection con = null;
       PreparedStatement ps = null;
       String query = "insert into Employee values (?,?)";

       try {
           con = DbConn.getConnection();
           ps = con.prepareStatement(query);

           long start = System.currentTimeMillis();

           for (int i = 0; i < 10000; i++) {
               ps.setInt(1, i);
               ps.setString(2, "Name"+i);
               ps.execute();
           }
           System.out.println("Time Taken=" + (System.currentTimeMillis() - start));

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
