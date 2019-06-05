package cn.littleterry.java.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 获取连接实例
 *
 * @author X.Teng
 * @email tengxing7452@163.com
 * @date 18-4-27 上午11:21
 */
public class DbConn {
    private String driver="";
    private String url="";
    private String username="";
    private String password="";

    public static Connection getConnection() {
        Properties props = new Properties();
        Connection con = null;
        try {
            InputStream is = DbConn.class.getClassLoader().getResourceAsStream("db.properties");
            props.load(is);
            Class.forName(props.getProperty("db.driver-class-name"));
            con = DriverManager.getConnection(props.getProperty("db.url"),
                    props.getProperty("db.username"),
                    props.getProperty("db.password"));
        } catch (SQLException e) {
            System.out.println("Check database is UP and configs are correct");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Looks like db.property file has some issues");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Please include JDBC API jar in classpath");
            e.printStackTrace();
        }finally{}
        return con;
    }
}