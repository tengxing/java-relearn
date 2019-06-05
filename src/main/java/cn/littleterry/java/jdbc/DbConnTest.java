package cn.littleterry.java.jdbc;

import java.sql.*;

/**
 * jdbc测试
 *
 * @author X.Teng
 * @email tengxing7452@163.com
 * @date 18-4-27 上午11:20
 */
public class DbConnTest {

    private static final String QUERY = "select id,name,email,country,password from Users";

    public static void main(String[] args) {

        //using try-with-resources to avoid closing resources (boiler plate code)
        try(Connection con = DbConn.getConnection();
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(QUERY)) {


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}