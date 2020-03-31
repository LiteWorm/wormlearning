package com.liteworm.javaLearn.chapter02.demo02;

import com.liteworm.javaLearn.chapter01.abstractclass.animals.Animal;

import java.io.IOException;

/**
 * @ClassName Father
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/3/31 0:29
 * @Version 1.0
 **/
public class Father {

    /**
    * @auther LiteWorm
    * @ClassName Father
    * @FunctionName m1
    * @Description
     * 1）方法其阿明：m1(int)
    * @Date 0:34 2020/3/31
    * @Param [x]
    * @return void
    **/
    public void m1(int x ){

    }

    /**
    * @auther LiteWorm
    * @ClassName Father
    * @FunctionName m2
    * @Description
     * 1)方法签名：m2(int, int)
    * @Date 0:35 2020/3/31
    * @Param [x, y]
    * @return com.liteworm.javaLearn.chapter01.abstractclass.animals.Animal
    **/
    protected Animal m2(int x, int y) throws IOException {

        return new Animal();
    }


}
