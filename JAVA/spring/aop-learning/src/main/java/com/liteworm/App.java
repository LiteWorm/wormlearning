package com.liteworm;

import com.liteworm.proxy.MyInvocationHandler;
import com.liteworm.service.SomeService;
import com.liteworm.service.impl.SomeServiceImpl;

import java.lang.reflect.Proxy;

/**
 *
 *
 */
public class App 
{
    public static void main( String[] args ) {
//        SomeService someService = new SomeServiceImpl();
//        someService.doSome();
//        System.out.println("==================================");
//        someService.doOther();

        //目标对象
        SomeService target = new SomeServiceImpl();
        //创建InvocationHandler的实现类对象
        MyInvocationHandler handler = new MyInvocationHandler(target);
        //创建代理对象
        SomeService proxy = (SomeService)Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                handler);
        //com.sun.proxy.$Proxy0   jdk的动态代理类型
        System.out.println("proxy:"+ proxy.getClass().getName());
        //通过代理对象执行业务方法
        proxy.doSome();
        System.out.println("====================");
        proxy.doOther();



    }
}
