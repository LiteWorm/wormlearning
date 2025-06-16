package com.liteworm.proxy;

import com.liteworm.utils.ServiceTools;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ClassName MyInvocationHandler
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/18 20:11
 * @Version 1.0
 **/
public class MyInvocationHandler  implements InvocationHandler {
    private  Object target;

    public MyInvocationHandler() {
    }

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    /**
    * @auther LiteWorm
    * @ClassName MyInvocationHandler
    * @FunctionName invoke
    * @Description
     * 实现业务放啊发的功能增强。加入日志，事务功能
    * @Date 20:11 2020/4/18
    * @Param [
     * proxy, //生成的代理对象
     * method,//业务方法
     * args //业务方法参数
     * ]
    * @return java.lang.Object //业务方法的返回值
    **/
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object result = null;

        System.out.println("Method 对象的：" + method.getName());
        if ("doSome".equals(method.getName())) {
            //在目标方法调用之前加入日志
            ServiceTools.doLog();

            //执行目标方法
            result = method.invoke(target, args); //doSome,doOther

            //在目标方法调用之后加入事务
            ServiceTools.doTrans();
        }else{
            //执行目标方法
            result = method.invoke(target, args); //doSome,doOther
        }
        return result;
    }
}