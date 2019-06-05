package cn.littleterry.java.timer;

import java.util.Date;
import java.util.TimerTask;

/**
 * @author X.Teng
 * @email tengxing7452@163.com
 * @date 18-4-26 下午8:25
 */
public class MyTaskB extends TimerTask {

    @Override
    public void run() {
        System.out.println("B run timer=" + new Date());
    }

}
