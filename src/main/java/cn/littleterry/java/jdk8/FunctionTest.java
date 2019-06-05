package cn.littleterry.java.jdk8;

import java.util.function.Function;

/**
 * @Author: X.Teng
 * @Email: tengxing7452@163.com
 * @Date: 18-1-4 下午7:10
 * @Describe: jdk8 Function< T,R> 接口接受一个T类型参数，返回R类型对象或值
 */
public class FunctionTest {
    public static void main(String[] args) {
        System.out.println(getStringToIngeter("520"));
    }

    /**
     * String类型转Integer类型
     * @param num
     * @return
     */
    public static Integer getStringToIngeter(String num){
        Function<String,Integer> function = input ->Integer.parseInt(input);
        Integer result = function.apply(num);
        return result;
    }
}
