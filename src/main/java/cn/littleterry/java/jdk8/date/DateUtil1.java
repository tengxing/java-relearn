package cn.littleterry.java.jdk8.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: X.Teng
 * @Email: tengxing7452@163.com
 * @Date: 18-1-11 下午6:02
 * @Describe:方法1：需要的时候创建新实例
 */
public class DateUtil1 {

    public static  String formatDate(Date date)throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static Date parse(String strDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(strDate);
    }

    /**
     * 每次执行创建一个对象，这样大量的对象就这样被new出来，占用大量的内存和 jvm空间,优秀的开发者不应该这样做
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {
        System.out.println(formatDate(new Date()) + "\n" + parse("2018-01-11 18:07:51"));
    }
}