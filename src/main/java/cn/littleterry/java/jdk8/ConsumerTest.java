package cn.littleterry.java.jdk8;

import java.util.function.Consumer;

/**
 * @Author: X.Teng
 * @Email: tengxing7452@163.com
 * @Date: 18-1-4 下午3:58
 * @Describe: jdk8 Consumer< T>接口接受一个T类型参数，没有返回值。
 */
public class ConsumerTest {
    public static void main(String[] args){
        String name = "X.Teng";
        showName(name);
    }

    /**
     * 打印输入的参数
     * @param name
     */
    public static void showName(String name){
        Consumer<String> userConsumer = input ->System.out.println(input);
        userConsumer.accept(name);
    }
}
