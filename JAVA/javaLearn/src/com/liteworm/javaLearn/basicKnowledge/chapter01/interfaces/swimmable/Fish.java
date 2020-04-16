package com.liteworm.javaLearn.basicKnowledge.chapter01.interfaces.swimmable;

/**
 * @ClassName Fish
 * @Decription
 * @AUthor YongLiu
 * @Date 2020/3/29 12:18
 * @Version 1.0
 **/
public class Fish implements Swimmable {
    @Override
    public void swim() {
        System.out.println("鱼天生会游泳");
    }
}
