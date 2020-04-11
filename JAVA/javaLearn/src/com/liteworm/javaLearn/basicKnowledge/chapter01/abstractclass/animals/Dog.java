package com.liteworm.javaLearn.basicKnowledge.chapter01.abstractclass.animals;

/**
 * @ClassName Dog
 * @Decription
 * @AUthor YongLiu
 * @Date 2020/3/29 1:36
 * @Version 1.0
 **/
public class Dog extends Animal  {
    public Dog(String color) {
        super(color);
    }

    public Dog() {
        System.out.println("小狗喜欢吃肉");
    }

    @Override
    public void eat() {
        System.out.println("小狗喜欢吃肉");
    }
}
