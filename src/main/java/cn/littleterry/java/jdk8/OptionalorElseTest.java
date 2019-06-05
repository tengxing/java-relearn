package cn.littleterry.java.jdk8;

import java.util.Optional;

/**
 * 传入Optonal值为空,orElse或orElseGet都会执行,返回传入值;
 * 传入Optional值为非空,orElse会执行,orElseGet不会执行,返回执行方法体的结果.
 * @author terry
 * @since 29/05/2019
 */
public class OptionalorElseTest {
    /**
    -----------non-null----------
    invokeType:orElse
    input:100	output:100
    input:100	output:100
    -----------null--------------
    invokeType:orElse
    input:null	output:9527
    invokeType:orElseGet
    input:null	output:9527
    */
    public static void main(String[] args){
        Integer Yes=100;
        Integer No=null;
        System.out.println("-----------non-null----------");
        System.out.println("input:"+Yes+"\toutput:"+Optional.ofNullable(Yes).orElse(invokeMethod("orElse")));
        System.out.println("input:"+Yes+"\toutput:"+Optional.ofNullable(Yes).orElseGet(() ->invokeMethod("orElseGet")));
        System.out.println("-----------null--------------");
        System.out.println("input:"+No+"\toutput:"+Optional.ofNullable(No).orElse(invokeMethod("orElse")));
        System.out.println("input:"+No+"\toutput:"+Optional.ofNullable(No).orElseGet(() ->invokeMethod("orElseGet")));
    }

    /**
     * invokeMethod
     * @param type
     * @return
     */
    public static Integer invokeMethod(String type){
        System.out.println("invokeType:"+type);
        return 9527;
    }
}
