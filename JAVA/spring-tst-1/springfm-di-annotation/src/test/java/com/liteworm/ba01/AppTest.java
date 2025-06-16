package com.liteworm.ba01;

import static org.junit.Assert.assertTrue;

import com.liteworm.ba01.Student;
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

        //定义Spring的配置文件
        String config = "ba01/aplicationContext.xml";

        //创建Spring的容器对象,根据Spring配置文件的位置，使用接口的不同实现类
        //如果Spring的配置文件是在类路径（classpath），使用ClassPathXmlApplicationContext
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);

        //从容器中获取对象，使用getBean("<bean> 的id")
        Student student =  (Student)ctx.getBean("myStudent");
        System.out.println(student);

    }



}
