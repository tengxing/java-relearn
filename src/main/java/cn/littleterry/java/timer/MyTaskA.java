package cn.littleterry.java.timer;

import java.util.Date;
import java.util.TimerTask;

/**
 *
 * @author X.Teng
 * @email tengxing7452@163.com
 * @date 18-4-26 下午8:25
 */
public class MyTaskA extends TimerTask {

    @Override
    public void run() {
        System.out.println("B run timer=" + new Date());
        this.cancel();// 调用的是TimerTask类中的cancel()方法
        System.out.println("A任务自己移除自己,B任务不受影响，继续运行");
    }

}
