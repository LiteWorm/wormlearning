package com.liteworm.service.impl;

import com.liteworm.service.SomeService;

/**
 * @ClassName SomeServiceImpl
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/19 14:43
 * @Version 1.0
 **/
public class SomeServiceImpl implements SomeService {
    @Override
    public void doSome() {
        System.out.println("执行SomeServiceImpl的业务方法doSome");
    }
}