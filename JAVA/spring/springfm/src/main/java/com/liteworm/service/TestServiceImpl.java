package com.liteworm.service;

/**
 * @ClassName TestServiceImpl
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/18 1:46
 * @Version 1.0
 **/
public class TestServiceImpl implements TestService {

    /**
    * @auther LiteWorm
    * @ClassName TestServiceImpl
    * @FunctionName TestServiceImpl
    * @Description
     * Srping 默认调用午餐构造方法创建对象
     * 如果没有午餐狗咋偶方法，报错：No default constructor found
    * @Date 10:15 2020/4/18
    * @Param [name]
    * @return
    **/
    public TestServiceImpl() {
        System.out.println("TestServiceImpl的无参构造" );
    }

    @Override
    public void testService() {
        System.out.println("业务方法");
    }
}