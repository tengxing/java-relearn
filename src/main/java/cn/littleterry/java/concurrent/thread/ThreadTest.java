package cn.littleterry.java.concurrent.thread;

/**
 * @author terry
 * @since 19/07/2019
 */
public class ThreadTest {

    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();
    /**
     * 线程A拿到资源A的锁
     * 线程A拿到资源B的锁
     * 线程A堵塞等待，释放锁中的资源A
     * 线程C 开始通知
     * 线程A被通知，执行完毕，同时释放B资源
     * wait :4999
     * 线程B拿到资源B的锁
     * 线程B释放资源B进入等待状态，等待被中断
     * java.lang.InterruptedException
     * 	at java.lang.Object.wait(Native Method)
     *
     * 一：使用Runable实现三个线程，线程A先拿到资源A的锁，然后拿到资源B的锁，之后资源
     * A进入堵塞等待状态，同时在线程A中被释放
     * 二：主线程休眠5秒之后，C线程开始通知资源A，此时A资源只有
     * 线程A调用，所以会唤醒线程A继续执行，之后资源B被释放
     * 三：此时线程B等待4999毫秒之后拿到资源B的锁，然后也进入了等待阻塞状态
     * 四：主线程继续休眠5秒，然后中断线程B，抛出异常:InterruptedException
     *
     *
     *
     *
     * 两个线程共享资源
     * Thread.sleep()对比Object.wait()：前者是进程休眠，方法不会释放对象锁，后者是进入等待状态，会释放锁
     *
     * Object.notifyAll()对比Object.notify()：
     * 前者是唤醒所有等待的线程队列，后者是随机唤醒一个等待的线程
     *
     * 对比Thread.interrupt(),Thread.isInterrupted(),Thread.interrupted()
     * Thread.interrupt()：中断线程，设置中断标志为true(如果被挂起的线程，如sleep，wait，join这类，会抛出异常InterruptedException)
     * Thread.isInterrupted()：检测是否被中断(不清除中断标志)
     * Thread.interrupted()：检测是否被中断(清除中断标志)
     *
     * Thread.join()：等待线程执行终止
     *
     * Thread.yield()：线程让出CPU执行权，放弃时间片(CPU为了执行线程锁分配时间片)，进行下一轮调度(类似暂停task，先干另一个task，再回来继续干)
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (resourceA){
                        System.out.println("线程A拿到资源A的锁");
                        synchronized (resourceB){
                            System.out.println("线程A拿到资源B的锁");

                            System.out.println("线程A堵塞等待，释放锁中的资源A");
                            resourceA.wait();
                            System.out.println("线程A被通知，执行完毕，同时释放B资源");
                        }
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                long start = System.currentTimeMillis();
                synchronized (resourceB){
                    System.out.println("wait :"+String.valueOf(System.currentTimeMillis()-start));
                    System.out.println("线程B拿到资源B的锁");
                    try {
                        System.out.println("线程B释放资源B进入等待状态，等待被中断");
                        resourceB.wait();
                        System.out.println("线程B等待被中断");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized(resourceA){
                    System.out.println("线程C 开始通知");
                    resourceA.notify(); //执行wait()后面的方法。。
                }
            }
        });

        threadA.start();
        threadB.start();
        Thread.sleep(5000);
        threadC.start();
        Thread.sleep(5000);
        threadB.interrupt();

        threadA.join();
        threadB.join();
        threadC.join();
    }
}
