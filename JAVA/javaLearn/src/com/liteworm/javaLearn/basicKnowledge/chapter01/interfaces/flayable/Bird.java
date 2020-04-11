package com.liteworm.javaLearn.basicKnowledge.chapter01.interfaces.flayable;

/**
 * @ClassName Bird
 * @Decription
 * @AUthor YongLiu
 * @Date 2020/3/29 2:37
 * @Version 1.0
 **/
public class Bird implements Flyable {
    @Override
    public void fly() {
        System.out.println("小鸟会飞");
    }
}
