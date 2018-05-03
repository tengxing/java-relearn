package cn.yjxxclub.java.jdbc.datasource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;

/**
 * 数据源工厂
 *
 * @author XTENG
 * @email tengxing7452@163.com
 * @date 18-5-3 上午10:57
 */
public class MyDataSourceFactory {
    private static String driver;
    private static String dbname;
    private static String url;
    private static String dbuser;
    private static String dbpwd;

    public static DataSource getMySQLDataSource() {
        url = "jdbc:mysql://127.0.0.1:3306/javaRelearn?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&rewriteBatchedStatements=true";
        dbuser = "root";
        dbpwd = "123456";
        MysqlDataSource mysqlDS = null;
        try {
            mysqlDS = new MysqlDataSource();
            mysqlDS.setURL(url);
            mysqlDS.setUser(dbuser);
            mysqlDS.setPassword(dbpwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mysqlDS;
    }

    public static DataSource getPostgresDataSource(){
        url = "127.0.0.1";
        dbname = "javarelearn";
        dbuser = "postgres";
        dbpwd = "123456";
        PGSimpleDataSource pgSimpleDataSource = null;
        try {
            pgSimpleDataSource = new PGSimpleDataSource();
            pgSimpleDataSource.setServerName(url);
            pgSimpleDataSource.setDatabaseName(dbname);
            pgSimpleDataSource.setUser(dbuser);
            pgSimpleDataSource.setPassword(dbpwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pgSimpleDataSource;
    }
}
