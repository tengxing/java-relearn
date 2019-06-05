package cn.littleterry.java.jdk8;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @Author: X.Teng
 * @Email: tengxing7452@163.com
 * @Date: 18-1-4 下午3:14
 * @Describe: OptialTest
 */
public class OptialTest {
    public static void main(String[] args){

        Stream<String> names = Stream.of("Lamurudu", "Okanbi", "Oduduwa");
        Optional<String> longest = names
                .filter(name -> name.startsWith("L"))
                .findFirst();

        longest.ifPresent(name -> {
            String s = name.toUpperCase();
            System.out.println("The longest name is "+ s);
        });
    }

}
