package cn.littleterry.java.jdk8;

import java.util.function.Predicate;

/**
 * @Author: X.Teng
 * @Email: tengxing7452@163.com
 * @Date: 18-1-4 下午4:12
 * @Describe: jdk8 Predicate < T>接口接受一个T类型参数，返回一个boolean值
 */
public class PredicateTest {
    public static void main(String[] args){
        String name = "X.Teng";
        System.out.println(equalAuthor(name));
    }

    /**
     * 判断是否是博主
     * @param name
     * @return
     */
    public static boolean equalAuthor(String name){
        Predicate<String> predicate = input ->input.equals("X.Teng");
        return predicate.test(name);
    }
}
