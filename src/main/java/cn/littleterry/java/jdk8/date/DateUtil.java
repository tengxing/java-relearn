package cn.littleterry.java.jdk8.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: X.Teng
 * @Email: tengxing7452@163.com
 * @Date: 18-1-11 下午6:02
 * @Describe: DateUtil
 * 解决DateUtil.java的性能开销问题，但是带来了问题:线程不安全
 * 不管什么时候，将有线程安全问题的对象由共享变为局部私有都能避免多线程问题，
 * 不过也加重了创建对象的负担。在一般情况下，这样其实对性能影响比不是很明显的。
 */
public class DateUtil {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static  String formatDate(Date date)throws ParseException{
        return sdf.format(date);
    }

    public static Date parse(String strDate) throws ParseException {
        return sdf.parse(strDate);
    }

    /**
     *
     * SimpleDateFormat继承了DateFormat,在DateFormat中定义了一个protected属性的 Calendar类的对象：calendar。
     * 只是因为Calendar累的概念复杂，牵扯到时区与本地化等等，Jdk的实现中使用了成员变量来传递参数，这就造成在多线程的时候会出现错误。
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {
        System.out.println(formatDate(new Date()) + "\n" + parse("2018-01-11 18:07:51"));
        for (int i=0;i<3;i++){
            new TestSimpleDateFormatThreadSafe().start();
        }
    }

    public static class TestSimpleDateFormatThreadSafe extends Thread {
        @Override
        public void run() {
            while(true) {
                try {
                    this.join(2000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                try {
                    System.out.println(this.getName()+":"+DateUtil.parse("2018-01-11 18:08:52"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}