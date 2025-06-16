package com.liteworm.ba01;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    @Test
    public void test01(){
        String config="applicationContext.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
        Student stu = (Student) ctx.getBean("myStudent");
        System.out.println(stu);

    }

    @Test
    public void test02(){
        String config="applicationContext.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
        Student stu = (Student) ctx.getBean("myStudent2");
        System.out.println(stu);

    }


    @Test
    public void test03(){
        String config="applicationContext.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
        Student stu = (Student) ctx.getBean("myStudent");
        System.out.println(stu);

    }

}
