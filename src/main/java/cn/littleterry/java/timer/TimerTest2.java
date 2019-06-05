package cn.littleterry.java.timer;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

/**
 * Timer类中的cancel()方法则是将任务队列中全部的任务清空
 *
 * @author X.Teng
 * @email tengxing7452@163.com
 * @date 18-4-26 下午8:18
 */
public class TimerTest2 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("当前时间为：" + new Date());
        Calendar c = Calendar.getInstance();
        Date runDate1 = c.getTime();
        System.out.println("计划时间为：" + runDate1);
        MyTaskB task2 = new MyTaskB();
        MyTaskC task1 = new MyTaskC();
        Timer timer = new Timer();
        timer.schedule(task1, runDate1, 4000);
        timer.schedule(task2, runDate1, 4000);
        Thread.sleep(5000);
        timer.cancel();
        System.out.println("A、B任务都移除了");

    }
}