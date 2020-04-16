package com.liteworm.javaLearn.basicKnowledge.chapter01.finals.p5;

/**
 * @ClassName Test
 * @Decription
 * @AUthor YongLiu
 * @Date 2020/3/29 1:08
 * @Version 1.0
 **/
public class Test {
    public static void main(String[] args) {//测试psv+table
        System.out.println("测试sout");

        m1(11,22);  //在调用方法时，就会给final形参初始化
    }

    public static void m1(int x , final int y) {
        x = 55;
//        y = 99;           //必能修改final形参的值
    }
}
