package cn.littleterry.java.jdk8;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * @Author: X.Teng
 * @Email: tengxing7452@163.com
 * @Date: 18-1-5 上午9:58
 * @Describe: Optional
 */
public class OptionalTest {
    static class User{
        private String name;
        private String age;

        public User(String name,String age){
            this.name=name;
            this.age=age;
        }
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }

    /**
     * 用了 isPresent() 处理 NullPointerException 不叫优雅, 有了  orElse, orElseGet 等, 特别是 map 方法才叫优雅.
     *
     *其他几个, filter() 把不符合条件的值变为 empty() ,   flatMap() 总是与 map() 方法成对的,   orElseThrow() 在有值时直接返回, 无值时抛出想要的异常.
     *
     *一句话小结: 使用 Optional 时尽量不直接调用 Optional.get() 方法, Optional.isPresent() 更应该被视为一个私有方法, 应依赖于其他像 Optional.orElse() , Optional.orElseGet() , Optional.map() 等这样的方法.
     *
     *最后, 最好的理解 Java 8 Optional 的方法莫过于看它的源代码java.util.Optional , 阅读了源代码才能真真正正的让你解释起来最有底气, Optional 的方法中基本都是内部调用   isPresent() 判断, 真时处理值, 假时什么也不做.
     * @param args
     */
    public static void main(String[] args){


        List<User> userList = new LinkedList();
        userList.add(new User("","1"));
        userList.add(new User("name2",null));
        userList.add(new User(null,"1"));
        userList.add(new User("name4","1"));
        userList.add(new User("name5","1"));
        userList.forEach(tmp -> System.out.println(tmp));

        Optional<List<User>> stringOptional = Optional.ofNullable(userList);
        Optional<String> stringOptional1 = Optional.of("");
        System.out.println(stringOptional.orElse(new ArrayList()));
        System.out.println(stringOptional.orElseGet(() -> getData()));
        stringOptional.ifPresent(user -> System.out.println(user.get(0).getName()));
        getName(userList);
    }

    public static List<User> getData(){
        List<User> userList = new LinkedList();
        userList.add(new User("name1","1"));
        userList.add(new User("name1",null));
        userList.add(new User(null,"1"));
        userList.add(new User("name3","1"));
        userList.add(new User("name5","1"));
        return userList;
    }
    public static void getName(List<User> userList){
        userList.forEach(user -> {
            Optional<User>  stringOptional = Optional.of(user);
            System.out.println(stringOptional.map(n -> n.getName()).map(a -> a.toUpperCase()).map(q ->q.equals("")?"空字符串":q).orElse("默认值"));
        });
    }
}
