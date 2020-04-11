package com.liteworm.javaLearn.basicKnowledge.chapter01.interfaces.flayable;

/**
 * @ClassName Test
 * @Decription
 * @AUthor YongLiu
 * @Date 2020/3/29 2:39
 * @Version 1.0
 **/
public class Test {
    public static void main(String[] args) {
        Flyable flyable;
        //接口不能实例化对象
//        flyable = new Flyable();
        //接口一用需要赋值实例化对象
        flyable = new Bird();
        //通过接口引用，调用接口的抽象方法，实际上是执行实现类对象的方法，这是接口多态
        flyable.fly();

        flyable = new Plane();
        flyable.fly();
    }
}
