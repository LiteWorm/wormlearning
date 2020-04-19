package com.liteworm.ba04;


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
        String config="applicationContext04.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
        Student stu = (Student) ctx.getBean("myStudent");
        System.out.println(stu);

    }
}
