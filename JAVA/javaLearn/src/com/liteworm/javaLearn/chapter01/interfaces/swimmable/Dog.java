package com.liteworm.javaLearn.chapter01.interfaces.swimmable;

/**
 * @ClassName Dog
 * @Decription
 * @AUthor YongLiu
 * @Date 2020/3/29 12:17
 * @Version 1.0
 **/
public class Dog implements Swimmable {

    @Override
    public void swim() {
        System.out.println("狗天生会游泳");
    }
}
