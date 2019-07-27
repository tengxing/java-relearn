package cn.littleterry.java.concurrent.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

/**
 * @author terry
 * @since 20/07/2019
 */
public class ThreadCreateTest {
    /**
     * 创建线程一共三种方式：继承Thread，实现Runnable，RunnableFuture
     * 第一种:方便传参,不能继承其他类(java单继承)
     * 第二种:只能使用主线程中关键字final修饰的变量
     * 第三种:能够拿到任务的返回结果，前两种不能
     * @param args
     */
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start();

        RunableTask runableTask = new RunableTask();
        new Thread(runableTask).start();

        RunnableFuture<String> futureTask = new FutureTask<>(new CallerTask());
        new Thread(futureTask).start();
        try {
            String result = futureTask.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * RunnableFuture
     * call方法实际调用了run()方法，其实就是实现了接口Runnable，
     * 只不过是将返回值用临时类型:outcome保存起来了，
     * 再用get()获取outcome,可以看源码。
     */
    public static class CallerTask implements Callable<String> {
        @Override
        public String call() throws Exception {
            String result = "通过RunnableFuture的方式创建线程";
            return result;
        }
    }

    /**
     * 实现Runnable接口
     */
    public static class RunableTask implements Runnable{
        @Override
        public void run() {
            System.out.println("通过实现Runnable接口创建线程");
        }
    }

    /**
     * 继承Thread类
     */
    public static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("通过继承Thread类创建线程");
        }

    }
}
