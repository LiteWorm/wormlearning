package com.liteworm.javaLearn.basicKnowledge.chapter01.interfaces.swimmable;

/**
 * @ClassName SwimmingPool
 * @Decription
 * @AUthor YongLiu
 * @Date 2020/3/29 12:20
 * @Version 1.0
 **/
public class SwimmingPool {
    public void  accept(Swimmable swimmable){
        swimmable.swim();
    }
}
