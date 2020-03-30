package com.liteworm.javaLearn.chapter02.demo03;

/**
 * @ClassName AgeOutOfRangeException
 * @Decription
 * 1）定义一个类集成Exception
 * 2）一般定义两个构造方法，无参构造和带字符串的有参构造
 * 3）通过Throw抛出异常对象
 * @AUthor LiteWorm
 * @Date 2020/3/31 0:55
 * @Version 1.0
 **/
public class AgeOutOfRangeException extends Exception {
    public AgeOutOfRangeException() {
    }

    public AgeOutOfRangeException(String message) {
        super(message);
    }
}
