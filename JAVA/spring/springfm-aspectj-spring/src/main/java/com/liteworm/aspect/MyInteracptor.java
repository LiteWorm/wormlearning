package com.liteworm.aspect;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @ClassName MyInteracptor
 * @Decription
 * 环绕通知
 * @AUthor LiteWorm
 * @Date 2020/4/19 15:30
 * @Version 1.0
 **/
public class MyInteracptor implements MethodInterceptor {

    //MethodInvocation 等同于 jdk中的Method
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {

        Object retValue = null;
        //可以实现功能的增强，目标方法的调用
        System.out.println("spring的环绕通知：在目标方法之前执行的功能");
        retValue = methodInvocation.proceed();
        System.out.println("spring的环绕通知：在目标方法之后执行的功能");
        return retValue;
    }
}