package cn.littleterry.java.jdbc.preparedstatusmentin;

import cn.littleterry.java.jdbc.DbConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 动态拼接sql占位符一次执行
 *
 * @author XTENG
 * @email tengxing7452@163.com
 * @date 18-4-28 下午4:13
 */
public class JDBCPreparedStatementDynamic {

    public static void printData(int[] ids){

        String query = createQuery(ids.length);

        System.out.println("Query="+query);
        Connection con = DbConn.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(query);

            for(int i = 1; i <=ids.length; i++){
                ps.setInt(i, ids[i-1]);
            }
            rs = ps.executeQuery();

            while(rs.next()){
                System.out.println("User ID="+rs.getInt("id")+", Name="+rs.getString("name"));
            }

            try{
                rs.close();
            } catch(SQLException e){}

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 拼接sql占位符
     * @param length
     * @return
     */
    private static String createQuery(int length) {
        String query = "SELECT id,name,email,country,password FROM Users where id in (";
        StringBuilder queryBuilder = new StringBuilder(query);
        for( int i = 0; i< length; i++){
            queryBuilder.append(" ?");
            if(i != length -1) {
                queryBuilder.append(",");
            }
        }
        queryBuilder.append(")");
        return queryBuilder.toString();
    }
}