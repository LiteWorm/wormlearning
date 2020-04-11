package com.liteworm.javaLearn.basicKnowledge.chapter01.abstractclass.animals;

/**
 * @ClassName Test
 * @Decription
 * @AUthor YongLiu
 * @Date 2020/3/29 1:38
 * @Version 1.0
 **/
public class Test {
    public static void main(String[] args) {
        //1)抽象类定义变量类型
        Animal animal;
        //2)抽象类不能实例化对象
//        animal = new Animal() ;
        //3)抽象类的引用需要赋值子类对象
        animal = new Dog();
        animal.eat();
        animal = new Cat();
        animal.eat();
        //4)抽象类可以复制匿名的内部类对象
//        animal = new Animal() {
//        }

    }
}
