package com.liteworm;


import com.liteworm.service.BuyGoods;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{


    @Test
    public void testAddStudent(){
        String config = "applicationContext.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
        BuyGoods service = (BuyGoods) ctx.getBean("buyGoodsService");

        //使用事务时，是通过代理对象执行业务方法，才有事务的功能
        //使用的jdk的动态代理serbice:com.sun.proxy.$Proxy18，因为目标对象有接口
        System.out.println("serbice:" + service.getClass().getName());
        service.buyGoods(1001, 20);
        System.out.println("购买商品结束");
    }

    @Test
    public void testQueryStudent(){
        String config = "applicationContext.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);

    }
}
