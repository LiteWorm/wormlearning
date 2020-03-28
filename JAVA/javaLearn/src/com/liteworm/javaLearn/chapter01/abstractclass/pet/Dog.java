package com.liteworm.javaLearn.chapter01.abstractclass.pet;

/**
 * @ClassName Dog
 * @Decription
 * 子类继承了抽象列，必须重写抽象类的抽象放啊发
 * @AUthor YongLiu
 * @Date 2020/3/29 2:02
 * @Version 1.0
 **/
public class Dog extends Pet {
    @Override
    public void sellMeng() {
        System.out.println("小狗卖萌，汪汪汪。。。");
    }
}
