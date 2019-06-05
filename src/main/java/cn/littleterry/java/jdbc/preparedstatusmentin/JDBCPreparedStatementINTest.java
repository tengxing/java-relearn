package cn.littleterry.java.jdbc.preparedstatusmentin;

/**
 * 测试程序
 *
 * @author XTENG
 * @email tengxing7452@163.com
 * @date 18-4-28 下午4:48
 */
public class JDBCPreparedStatementINTest {

    private static int[] ids = {1,2,3,4,5,6,7,8,9,10};

    public static void main(String[] args) {

        JDBCPreparedStatementSingle.printData(ids);

        System.out.println("*********");

        JDBCPreparedStatementDynamic.printData(ids);

        System.out.println("*********");

        JDBCPreparedStatementNULL.printData(ids);
    }

}
