package com.liteworm.ba03;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.util.Date;


/**
 * Unit test for simple App.
 */
public class AppTest 
{

    @Test
    public void test01(){

        //定义Spring的配置文件
        String config = "ba03/aplicationContext.xml";

        //创建Spring的容器对象,根据Spring配置文件的位置，使用接口的不同实现类
        //如果Spring的配置文件是在类路径（classpath），使用ClassPathXmlApplicationContext
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);

        //从容器中获取对象，使用getBean("<bean> 的id")
//        Student student =  (Student)ctx.getBean("myStudent");
//        System.out.println(student);

//        Student student =  (Student)ctx.getBean("myStudent2");
//        System.out.println(student);

        Student student =  (Student)ctx.getBean("myStudent3");
        System.out.println(student);
    }


    @Test
    public void test02(){
        String config = "ba03/aplicationContext.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
        Date date = (Date)ctx.getBean("myDate");
        System.out.println(date);
    }

    @Test
    public void test03(){
        String config = "ba03/aplicationContext.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
        File file = (File)ctx.getBean("myFile");
        System.out.println(file);
    }

}
