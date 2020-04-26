package com.liteworm.utils;

import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName TransactionInvocationHandler
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/25 22:20
 * @Version 1.0
 **/
public class TransactionInvocationHandler implements InvocationHandler {

    private  Object target;

    public TransactionInvocationHandler(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        SqlSession session = null;
        Object obj = null;
        try{
            session = SqlSessionUtil.getSession();

            obj = method.invoke(target,args);

            session.commit();

        }catch (Exception ex){
            session.rollback();
            ex.printStackTrace();
        }finally {
            SqlSessionUtil.closeSession(session);
        }


        return obj;
    }

    public Object getProxy(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }
}