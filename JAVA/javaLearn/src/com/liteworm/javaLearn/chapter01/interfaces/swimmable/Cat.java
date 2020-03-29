package com.liteworm.javaLearn.chapter01.interfaces.swimmable;

/**
 * @ClassName Cat
 * @Decription
 * @AUthor YongLiu
 * @Date 2020/3/29 12:19
 * @Version 1.0
 **/
public class Cat implements Swimmable {
    @Override
    public void swim() {
        System.out.println("猫天生怕水");
    }
}
