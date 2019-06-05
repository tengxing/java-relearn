package cn.littleterry.java.timer;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

/**
 * TimerTask类中的cancel()方法侧重的是将自身从任务队列中清除，
 * 其他任务不受影响
 *
 * @author X.Teng
 * @email tengxing7452@163.com
 * @date 18-4-26 下午8:16
 */
public class TimerTest1 {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("当前时间为：" + new Date());
        Calendar c = Calendar.getInstance();
        Date runDate1 = c.getTime();
        System.out.println("计划时间为：" + runDate1);
        MyTaskA task1 = new MyTaskA();
        MyTaskB task2 = new MyTaskB();
        Timer timer = new Timer();
        timer.schedule(task1, runDate1, 4000);
        timer.schedule(task2, runDate1, 4000);
    }
}



