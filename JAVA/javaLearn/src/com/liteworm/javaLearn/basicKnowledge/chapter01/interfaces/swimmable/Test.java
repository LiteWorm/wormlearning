package com.liteworm.javaLearn.basicKnowledge.chapter01.interfaces.swimmable;

/**
 * @ClassName Test
 * @Decription
 * @AUthor YongLiu
 * @Date 2020/3/29 12:21
 * @Version 1.0
 **/
public class Test {
    public static void main(String[] args) {
        SwimmingPool swimmingPool = new SwimmingPool();

        Dog dog = new Dog();
        swimmingPool.accept(dog);

        Fish fish = new Fish();
        swimmingPool.accept(fish);

        Cat cat = new Cat();
        swimmingPool.accept(cat);

        swimmingPool.accept(new Swimmable() {
            @Override
            public void swim() {
                System.out.println("人在游泳池游泳");
            }
        });
    }
}
