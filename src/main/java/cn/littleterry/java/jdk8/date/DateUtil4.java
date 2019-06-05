package cn.littleterry.java.jdk8.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: X.Teng
 * @Email: tengxing7452@163.com
 * @Date: 18-1-11 下午7:42
 * @Describe:DateUtil4.java等价
 */
public class DateUtil4 {

    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>();

    public static DateFormat getDateFormat(){
        DateFormat dateFormat = threadLocal.get();
        if (dateFormat==null){
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            threadLocal.set(dateFormat);
        }
        return dateFormat;
    }

    public static  String formatDate(Date date)throws ParseException {
        return getDateFormat().format(date);
    }

    public static Date parse(String strDate) throws ParseException {
        return getDateFormat().parse(strDate);
    }
    /**
     * 使用ThreadLocal, 也是将共享变量变为独享，线程独享肯定能比方法独享在并发环境中能减
     * 少不少创建对象的开销。如果对性能要求比较高的情况下，一般推荐使用这种方法
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {
        /**
         * 1.自己写公用类的时候，要对多线程调用情况下的后果在注释里进行明确说明
         * 2.对线程环境下，对每一个共享的可变变量都要注意其线程安全性
         * 3.我们的类和方法在做设计的时候，要尽量设计成无状态的
         */
        System.out.println(formatDate(new Date()) + "\n" + parse("2018-01-11 18:07:51"));
    }
}
