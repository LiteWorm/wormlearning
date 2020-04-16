package com.liteworm.javaLearn.basicKnowledge.chapter02.demo01;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @ClassName TestThrows
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/3/31 0:08
 * @Version 1.0
 **/
public class TestThrows {
    /**
    * @auther LiteWorm
    * @ClassName TestThrows
    * @FunctionName main
    * @Description
     * throws跑出异常
     *      在方法体重如果有受检异常需要预处理，可以捕获处理，也可以跑出处理
    * @Date 0:09 2020/3/31
    * @Param [args]
    * @return void
    **/

    //main 方法抛出的异常，JVM负责处理，JVM处理异常的方式，就是中断程序
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("main方法中，调用m1方法");
        m1();
        System.out.println("main方法中，调用m13方法");
        m1();
        System.out.println("main方法执行结束");
    }

    private static void m1() throws FileNotFoundException {
        System.out.println("main方法中，调用m2方法");
        m2();
        System.out.println("m2方法执行结束");

    }
    private  static void m3(){
        System.out.println("m3方法中，调用m2方法");
        try {
            m2();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("m2方法执行结束");

    }
    private static void m2() throws FileNotFoundException {
        System.out.println("m1方法中，调用m2方法");
        FileInputStream fis = new FileInputStream("D:/Develop/demo/filetest/t1/testwrit.txt");
        System.out.println("m2方法执行结束");
    }
}
