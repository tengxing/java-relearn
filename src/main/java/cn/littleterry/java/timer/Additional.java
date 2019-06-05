package cn.littleterry.java.timer;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Timer类中的cancel()方法有时不一定会停止计划任务，而是正常执行
 *
 * 原因:Timer类中的cancel()方法有时并没有抢到queue锁，则让TimerTask类中的任务正常执行
 * 简单说就是:在取消之前任务被执行了
 *
 * @author X.Teng
 * @email tengxing7452@163.com
 * @date 18-4-26 下午8:33
 */

public class Additional {

    public static void main(String[] args) throws InterruptedException {
        int i = 0;

        Calendar c = Calendar.getInstance();
        Date runDate1 = c.getTime();
        System.out.println("仍然有任务没有被移除");
        MyTaskD task1;
        Timer timer;
        while (true) {
            i++;
            timer = new Timer();
            task1 = new MyTaskD(i);
            timer.schedule(task1, runDate1);
            timer.cancel();

        }

    }
}

class MyTaskD extends TimerTask {
    private int i;

    public MyTaskD(int i) {
        super();
        this.i = i;
    }

    @Override
    public void run() {

        System.out.println("第" + i + "次测试任务没有被cancel取消");
    }

}