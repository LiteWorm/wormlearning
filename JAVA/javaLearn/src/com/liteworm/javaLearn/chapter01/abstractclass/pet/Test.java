package com.liteworm.javaLearn.chapter01.abstractclass.pet;

/**
 * @ClassName Test
 * @Decription
 * 宠物会卖萌，小猫/小狗/小猪是宠物
 * 主人喂宠物是，宠物就卖萌
 * @AUthor YongLiu
 * @Date 2020/3/29 1:55
 * @Version 1.0
 **/
public class Test {
    public static void main(String[] args) {
        Pet pet = new Dog();
        pet.sellMeng();;
        Master master = new Master();
        master.feed(pet);


        pet = new Cat();
        pet.sellMeng();
        master.feed(pet);
    }
}
