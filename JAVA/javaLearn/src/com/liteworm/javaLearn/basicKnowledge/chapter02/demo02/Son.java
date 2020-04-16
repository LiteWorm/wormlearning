package com.liteworm.javaLearn.basicKnowledge.chapter02.demo02;

import java.io.FileNotFoundException;

/**
 * @ClassName Son
 * @Decription
 * 子类重写父类的方法
 * @AUthor LiteWorm
 * @Date 2020/3/31 0:36
 * @Version 1.0
 **/
public class Son extends  Father{

    @Override   //注解，验证方法重写是否正确
    /**
    * @auther LiteWorm
    * @ClassName Son
    * @FunctionName m1
    * @Description
     * 方法重写，方法签名必须相同，必须是m1(int)
     * 返回值类型是否void，也必须相同
     * 父类方法使用public修饰，子类也必须用public修饰
     * 父类方法没有抛出异常，子类也不能抛出异常
    * @Date 0:44 2020/3/31
    * @Param [x]
    * @return void
    **/
    public void m1(int x) {
        super.m1(x);
    }


    @Override
    /**
    * @auther LiteWorm
    * @ClassName Son
    * @FunctionName m2
    * @Description
     *方法重写，方法签名必须相同，必须是m2(int, int)
     *返回值类型是Animal，子类方法返回值类型可以是Animal，也可以是Animal的子类
     *父类方法使用private修饰，子类重写后，可以是protected，衣蛾可以使public修饰
     *父类方法声明抛出了IOException异常，子类方法也可以抛出相同的异常，也可以抛出父类异常的自异常
     *父类方法没有抛出异常，子类也不能抛出异常,也可以不抛出异常
    * @Date 0:44 2020/3/31
    * @Param [x, y]
    * @return com.liteworm.javaLearn.chapter02.demo02.Dog
    **/
    public Dog m2(int x, int y) throws FileNotFoundException {

        return new Dog();
    }
}
