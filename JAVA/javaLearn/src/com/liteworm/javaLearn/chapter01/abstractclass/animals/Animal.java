package com.liteworm.javaLearn.chapter01.abstractclass.animals;

/**
 * @ClassName Animal
 * @Decription
 * 使用abstarct 修饰的类就是抽象类
 * @AUthor YongLiu
 * @Date 2020/3/29 1:29
 * @Version 1.0
 **/
public class Animal {
    public static void main(String[] args) {

    }
    String color;   //在抽象类中定义示例变量
    static int  XX = 1234;
    public void eat(){
        System.out.println("动物要吃食物");
    }
    public static void sm(){
        System.out.println("动物需要sm");
    }

    public Animal() {
    }

    public Animal(String color) {
        super();
        this.color = color;
    }
}
