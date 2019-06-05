package cn.littleterry.java.annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 注解解析测试
 *
 * @author X.Teng
 * @email tengxing7452@163.com
 * @date 18-4-26 下午8:08
 */
public class AnnotationParsingTest {

    public static void main(String[] args) {
        try {
            for (Method method : AnnotationParsingTest.class
                    .getClassLoader()
                    .loadClass(("AnnotationExample"))
                    .getMethods()) {
                // checks if MethodInfo annotations is present for the method
                if (method
                        .isAnnotationPresent(MethodInfo.class)) {
                    try {
                        // iterates all the annotations available in the method
                        for (Annotation anno : method.getDeclaredAnnotations()) {
                            System.out.println("Annotation in Method '"
                                    + method + "' : " + anno);
                        }
                        MethodInfo methodAnno = method
                                .getAnnotation(MethodInfo.class);
                        if (methodAnno.revision() == 1) {
                            System.out.println("Method with revision no 1 = "
                                    + method);
                        }

                    } catch (Throwable ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } catch (SecurityException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}