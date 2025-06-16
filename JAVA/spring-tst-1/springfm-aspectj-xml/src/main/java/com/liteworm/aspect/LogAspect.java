package com.liteworm.aspect;

/**
 * @ClassName LogAspect
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/19 14:50
 * @Version 1.0
 **/
public class LogAspect {
    /**
    * @auther LiteWorm
    * @ClassName LogAspect
    * @FunctionName
    * @Description
     * 定义方法，实现切面的功能
     * 方法的定义方式同注解方式中的各个方法。
     * 前置通知同@Before方法的定义
    * @Date 14:50 2020/4/19
    * @Param
    * @return
    **/

    public  void recordLog(){
        System.out.println("前置通知，在目标方法之前记录日志");
    }
}