package cn.littleterry.java.jdk8;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: X.Teng
 * @Email: tengxing7452@163.com
 * @Date: 18-1-4 下午4:27
 * @Describe: jdk8 Stream
 */
public class StreamTest {
    public static void main(String[] args) {
        List<String> nameList = new ArrayList(3);
        nameList.add("张三");
        nameList.add("李四");
        nameList.add("王二");

        //errorStream(nameList);
        rightStream(nameList);
        betterStream(nameList);
    }

    /**
     * 会报错:stream has already been operated upon or closed
     * 报错原因：A Stream should be operated on (invoking an intermediate
     * or terminal stream operation) only once. A Stream implementation
     * may throw IllegalStateException if it detects that the Stream is being reused.
     * 就是说被消费了两次
     * 解决:creating a new Stream each time when we need one,eg:rightStream()
     * @param nameList
     */
    public static void errorStream(List<String> nameList){
        //Stream<List<String>> names = Stream.of(nameList);
        Stream<String> names = nameList.stream();
        names.filter(name ->!name.equals("张三")).collect(Collectors.toList());
        List<String> newList = names.collect(Collectors.toList());
        System.out.println(newList);
    }

    /**
     * 消费一次
     * @param nameList
     */
    public static void rightStream(List<String> nameList){
        //Stream<List<String>> names = Stream.of(nameList);
        Stream<String> names = nameList.stream();
        List<String> newList = names.filter(name ->!name.equals("张三")).collect(Collectors.toList());
        System.out.println(newList);
    }

    /**
     * 使用优雅的方式来实现
     * @param nameList
     */
    public static void betterStream(List<String> nameList){
        //Supplier<Stream<List>> streamSupplier =  () -> Stream.of(nameList);
        Supplier<Stream<String>> streamSupplier =  () -> nameList.stream();
        streamSupplier.get().filter(name ->!name.equals("张三"));
        Optional<String> result1 = streamSupplier.get().filter(name ->!name.equals("张三")).findAny();
        System.out.println(result1.get());
    }
}
