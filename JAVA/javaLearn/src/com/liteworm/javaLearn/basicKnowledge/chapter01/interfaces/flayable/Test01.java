package com.liteworm.javaLearn.basicKnowledge.chapter01.interfaces.flayable;

/**
 * @ClassName Test01
 * @Decription 测试弹弓类
 * @AUthor YongLiu
 * @Date 2020/3/29 12:09
 * @Version 1.0
 **/
public class Test01 {
    public static void main(String[] args) {
        SignalShot slingshot = new SignalShot();


        Bird bird = new Bird();
        slingshot.shoot(bird);

        Plane plane = new Plane();
        slingshot.shoot(plane);

        slingshot.shoot(new Flyable() {
            @Override
            public void fly() {
                System.out.println("会飞的物体");
            }
        });
    }
}
