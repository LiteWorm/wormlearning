package com.liteworm.javaLearn.testClass;

/**
* @auther LiteWorm
* @ClassName TestClass
* @FunctionName
* @Description
 * 测试使用反射的基本方法
* @Date 18:28 2020/4/11
* @Param
* @return
**/

public class TestClass {

    public static void main(String[] args) throws ClassNotFoundException {


        Class c1 = TestClass.class;

        Class c2 = new TestClass().getClass();


        Class c3 = Class.forName("com.liteworm.javaLearn.testClass.TestClass");

        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);


        Class<?> c4 = int.class;
        Class<?> c5 = Integer.class;
        System.out.println(c4 == c5);

        Class<?> c6 = Integer.TYPE;
        System.out.println(c4 == c6);


    }

}
