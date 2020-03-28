package com.liteworm.javaLearn.chapter01.abstractclass.pet;

/**
 * @ClassName Pet
 * @Decription
 * //宠物有卖萌的行为，不通的宠物有不通的卖萌方式，在宠物类中，卖萌行为无法具体事项
 * 当一个累的操作无法具体实现时，这个操作可以定义为抽象方法
 * 使用abstract修饰的方法，只有方法的声明部分，没有方法体
 * 含有抽象方法的类必须定义为抽象类
 * @AUthor YongLiu
 * @Date 2020/3/29 1:58
 * @Version 1.0
 **/
public abstract class  Pet {
    //卖萌
    public abstract void sellMeng();
}
