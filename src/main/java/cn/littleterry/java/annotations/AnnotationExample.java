package cn.littleterry.java.annotations;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * 注解示例
 *
 * @author X.Teng
 * @email tengxing7452@163.com
 * @date 18-4-26 下午8:06
 */
public class AnnotationExample {
    @Override
    @MethodInfo(author = "Pankaj", comments = "Main method", date = "Nov 17 2018", revision = 1)
    public String toString() {
        return "Overriden toString method";
    }

    @Deprecated
    @MethodInfo(comments = "deprecated method", date = "Nov 17 2018")
    public static void oldMethod() {
        System.out.println("old method, don't use it.");
    }

    @SuppressWarnings({ "unchecked", "deprecation" })
    @MethodInfo(author = "Pankaj", comments = "Main method", date = "Nov 17 2018", revision = 10)
    public static void genericsTest() throws FileNotFoundException {
        List l = new ArrayList();
        l.add("abc");
        oldMethod();
    }

}
