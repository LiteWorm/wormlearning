package com.liteworm;


import com.liteworm.service.SomeService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Unit test for simple App.
 */

public class AppTest {

    @Test
    public void test01() {
        String config = "applicationContext.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
        SomeService ss = (SomeService)ctx.getBean("proxyObject");
        System.out.println("proxy:" + ss.getClass().getName());
        ss.doSome();
    }
}

