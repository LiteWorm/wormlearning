package com.liteworm;

import static org.junit.Assert.assertTrue;


import com.liteworm.service.SomeService;
import com.liteworm.beans.Student;
import com.liteworm.service.impl.OrderServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Unit test for simple App.
 */

public class AppTest
{

    @Test
    public void test01(){
        String config = "applicationContext.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
        SomeService poxy = (SomeService) ctx.getBean("someServiceTarget");
        //com.sun.proxy.$Proxy7:目标类有接口，框架默认使用jdk动态代理
        System.out.println("prox代理:" + poxy.getClass().getName());
        poxy.doSome("zhangsan", 20);

    }

    @Test
    public void test02(){
        String config = "applicationContext.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
        SomeService poxy = (SomeService) ctx.getBean("someServiceTarget");

        String str = poxy.doOther();
        System.out.println("str:" + str);

    }

    @Test
    public void test03(){
        String config = "applicationContext.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
        SomeService poxy = (SomeService) ctx.getBean("someServiceTarget");

        Student str = poxy.doOther2();
        System.out.println("str:" + str);

    }


    @Test
    public void test04(){
        String config = "applicationContext.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
        SomeService poxy = (SomeService) ctx.getBean("someServiceTarget");

        String str = poxy.doFirst("zhangsan", 40);
        System.out.println("str:" + str);

    }


    @Test
    public void test05(){
        String config = "applicationContext.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
        SomeService poxy = (SomeService) ctx.getBean("someServiceTarget");

         poxy.doSecond("zhangsan", 50);

    }

    @Test
    public void test06(){
        String config = "applicationContext.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
        SomeService poxy = (SomeService) ctx.getBean("someServiceTarget");

        poxy.doThird();

    }

    @Test
    public void test07(){
        String config = "applicationContext.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
        OrderServiceImpl poxy = (OrderServiceImpl) ctx.getBean("orderServiceTarget");
        //代理类型
        /**
        * poxy:com.liteworm.service.impl.OrderServiceImpl$$EnhancerBySpringCGLIB$$a5d2775c
         * 目标类没有接口，使用的是cglib动态代理
        **/
        System.out.println("poxy:" + poxy.getClass().getName());
        poxy.createOrder();

    }
}
