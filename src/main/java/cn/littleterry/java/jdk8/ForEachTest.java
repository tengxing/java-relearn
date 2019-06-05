package cn.littleterry.java.jdk8;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @Author: X.Teng
 * @Email: tengxing7452@163.com
 * @Date: 18-1-4 下午3:23
 * @Describe: jdk8 Iterable ForEach 测试
 */
public class ForEachTest {
    public static void main(String[] args){
        List<String> names = new ArrayList<>();
        names.add("张三");
        names.add("李四");
        names.add("王二");
        /*通过一下的几种方式可以是实现打印,个人主推方式三,四*/
        test(names);

    }
    public static void test(List<String> names){
        /*方式一*/
        for(String name : names){
            System.out.println(name);
        }
        /*方式二*/
        for(int i=0;i<names.size();i++){
            System.out.println(names.get(i));
        }
        /*方法三*/
        names.forEach((String name) ->{
            System.out.println(name);
        });
        /*方法四*/
        names.forEach(name -> System.out.println(name));
        /*方法五*/
        names.forEach(System.out::println);
        /*方法六*/
        class NameConsumer implements Consumer<String>{
            @Override
            public void accept(String name) {
                System.out.println(name);
            }
        }
        names.forEach(new NameConsumer());
    }
}
