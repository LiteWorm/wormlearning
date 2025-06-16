package com.liteworm;

import com.liteworm.service.TestService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        //定义Spring的配置文件
        String config = "aplicationContext.xml";

        //创建Spring的容器对象,根据Spring配置文件的位置，使用接口的不同实现类
        //如果Spring的配置文件是在类路径（classpath），使用ClassPathXmlApplicationContext
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);

        //从容器中获取对象，使用getBean("<bean> 的id")
        TestService testService =  (TestService)ctx.getBean("myService");
        testService.testService();
    }
}
