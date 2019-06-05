package cn.littleterry.java.jdk8;

import java.util.function.UnaryOperator;

/**
 * @Author: X.Teng
 * @Email: tengxing7452@163.com
 * @Date: 18-1-4 下午7:16
 * @Describe: jdk8 UnaryOperator<T>一元运算，接受一个T类型参数，输出一个与入参一模一样的值
 * 它继承了Function<T, T>
 */
public class UnaryOperatorTest {
    public static void main(String[] args){
        UnaryOperator unaryOperator = s ->{return s;};

        System.out.println(unaryOperator.apply("1123"));
    }
}
