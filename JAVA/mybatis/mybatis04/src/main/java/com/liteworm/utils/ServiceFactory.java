package com.liteworm.utils;

/**
 * @ClassName ServiceFactory
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/25 22:45
 * @Version 1.0
 **/
public class ServiceFactory {

    public static  Object getService(Object service){

        return new TransactionInvocationHandler(service).getProxy();
    }
}