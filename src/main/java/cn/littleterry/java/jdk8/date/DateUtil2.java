package cn.littleterry.java.jdk8.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: X.Teng
 * @Email: tengxing7452@163.com
 * @Date: 18-1-11 下午7:31
 * @Describe:2.使用同步：同步SimpleDateFormat对象
 */
public class DateUtil2 {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String formatDate(Date date)throws ParseException {
        synchronized(sdf){
            return sdf.format(date);
        }
    }

    public static Date parse(String strDate) throws ParseException{
        synchronized(sdf){
            return sdf.parse(strDate);
        }
    }

    /**
     * 当线程较多时，当一个线程调用该方法时，其他想要调用此方法的线程就要block等待，多线程并发量大的时候会对性能有一定的影响
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {
        System.out.println(formatDate(new Date()) + "\n" + parse("2018-01-11 18:07:51"));
    }
}
