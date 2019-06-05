package cn.littleterry.java.jdk8.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: X.Teng
 * @Email: tengxing7452@163.com
 * @Date: 18-1-11 下午7:33
 * @Describe:3.使用ThreadLocal
 */
public class DateUtil3 {
    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue(){
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public static  String formatDate(Date date)throws ParseException {
        return threadLocal.get().format(date);
    }

    public static Date parse(String strDate) throws ParseException {
        return threadLocal.get().parse(strDate);
    }

    /**
     * 使用ThreadLocal, 也是将共享变量变为独享，线程独享肯定能比方法独享在并发环境中能减
     * 少不少创建对象的开销。如果对性能要求比较高的情况下，一般推荐使用这种方法
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {
        System.out.println(formatDate(new Date()) + "\n" + parse("2018-01-11 18:07:51"));
    }

}
