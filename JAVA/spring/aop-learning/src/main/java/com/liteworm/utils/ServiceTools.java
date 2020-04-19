package com.liteworm.utils;

import java.util.Date;

/**
 * @ClassName ServiceTools
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/18 20:06
 * @Version 1.0
 **/
public class ServiceTools {

    public static   void doLog(){
        System.out.println("日志功能，在方法开始时记录日志" + new Date());
    }


    public  static void doTrans(){
        System.out.println("非业务功能，在方法结束后执行" + new Date());
    }
}