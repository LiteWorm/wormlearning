package com.liteworm.javaLearn.chapter01.interfaces.flayable;

/**
 * @ClassName Plane
 * @Decription
 * @AUthor YongLiu
 * @Date 2020/3/29 2:38
 * @Version 1.0
 **/
public class Plane implements Flyable {
    @Override
    public void fly() {
        System.out.println("飞机也有飞行功能");
    }
}
