package com.liteworm.service.impl;

import com.liteworm.service.SomeService;
import com.liteworm.utils.ServiceTools;



/**
 * @ClassName SomeServiceImpl
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/18 19:59
 * @Version 1.0
 **/
public class SomeServiceImpl implements SomeService {
    @Override
    public void doSome() {
//        ServiceTools.doLog();
        System.out.println("执行了业务方法doSome");
//        ServiceTools.doTrans();
    }

    @Override
    public void doOther() {
//        ServiceTools.doLog();
        System.out.println("执行了业务方法doOther");
//        ServiceTools.doTrans();

    }


}