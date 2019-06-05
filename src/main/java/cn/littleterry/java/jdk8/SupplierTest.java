package cn.littleterry.java.jdk8;

import java.util.function.Supplier;

/**
 * @Author: X.Teng
 * @Email: tengxing7452@163.com
 * @Date: 18-1-4 下午7:03
 * @Describe: jdk8 Supplier<T>接口没有入参，每次调用返回一个T类型的对象，类似工厂方法。
 */
public class SupplierTest {
    public static void main(String[] args) {
        class User{
            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
        Supplier<User> supplier = ()->new User();
        User user = supplier.get();
        Supplier<String> supplier1 = ()->new String("X.Teng");
        System.out.println(user.getName() + "\t" + supplier1.get());
    }
}
