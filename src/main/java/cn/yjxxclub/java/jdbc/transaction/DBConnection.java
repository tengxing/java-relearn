package cn.yjxxclub.java.jdbc.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DBConnection
 *
 * @author XTENG
 * @email tengxing7452@163.com
 * @date 18-5-3 下午3:58
 */
public class DBConnection {

    public final static String DB_DRIVER_CLASS = "com.mysql.jdbc.Driver";
    public final static String DB_URL = "jdbc:mysql://localhost:3306/javaRelearn";
    public final static String DB_USERNAME = "root";
    public final static String DB_PASSWORD = "123456";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {

        Connection con = null;
        Class.forName(DB_DRIVER_CLASS);
        con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        System.out.println("DB Connection created successfully");
        return con;
    }
}
