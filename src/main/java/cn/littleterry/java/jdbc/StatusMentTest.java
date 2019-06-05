package cn.littleterry.java.jdbc;

import java.sql.*;

/**
 * StatusMent VS PreparedStatusMent
 * 比较结果:PreparedStatusMent可以防止SQL注入
 *
 * @author XTENG
 * @email tengxing7452@163.com
 * @date 18-4-27 下午3:46
 */
public class StatusMentTest {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        /**
         * 正常用户名
         */
        String email = "david@gmail.com";
        /**
         * sql注入后的用户名
         */
        String email_inject = "david@gmail.com' or '1'='1";
        /**
         * 用户密码为空字符串
         */
        String pwd = "";

        /**
         * StatusMent 正常情况下查不到结果
         */
        printUserDataByStatusMent(email,pwd);

        /**
         * StatusMent sql注入的情况下查到结果
         */
        printUserDataByStatusMent(email_inject,pwd);

        /**
         * PreparedStatusMent sql注入的情况下查不到结果
         */
        printUserDataByPreparedStatusMent(email_inject,pwd);
    }


    private static void printUserDataByStatusMent(String email, String pwd) throws ClassNotFoundException, SQLException {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            con = DbConn.getConnection();
            stmt = con.createStatement();
            String query = "select name, country, password from Users where email = '"+email+"' and password='"+pwd+"'";
            System.out.println(query);
            rs = stmt.executeQuery(query);

            System.out.println("查询结果如下:");
            while(rs.next()){
                System.out.println("Name="+rs.getString("name")+",country="+rs.getString("country")+",password="+rs.getString("password"));
            }
        }finally{
            if(rs != null) {rs.close();}
            stmt.close();
            con.close();
        }

    }

    private static void printUserDataByPreparedStatusMent(String email, String pwd) throws ClassNotFoundException, SQLException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = DbConn.getConnection();
            String query = "select name, country, password from Users where email = ? and password= ? ";
            ps = con.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, pwd);

            System.out.println(query);
            rs = ps.executeQuery();

            System.out.println("查询结果如下:");
            while(rs.next()){
                System.out.println("Name="+rs.getString("name")+",country="+rs.getString("country")+",password="+rs.getString("password"));
            }
        }finally{
            if(rs != null) {rs.close();}
            ps.close();
            con.close();
        }

    }
}
