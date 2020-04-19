package com.liteworm.ba04;

import com.liteworm.ba04.beans.User;
import com.liteworm.ba04.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Unit test for simple App.
 */
public class AppTest 
{

    @Test
    public void test01() {

        //定义Spring的配置文件
        String config = "ba04/applicationContext.xml";

        //创建Spring的容器对象,根据Spring配置文件的位置，使用接口的不同实现类
        //如果Spring的配置文件是在类路径（classpath），使用ClassPathXmlApplicationContext
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);

        User user = (User) ctx.getBean("user1");
        UserService us = (UserService) ctx.getBean("service");
        us.addUser(user);
    }


    @Test
    public void test02(){
        String conifg = "ba04/applicationContext.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(conifg);
        User user = (User) ctx.getBean("user2");
        UserService us = (UserService)ctx.getBean("service");
        us.addUser(user);
    }

    @Test
    public  void  test03(){
        String config = "ba04/applicationContext.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
        User user = (User) ctx.getBean("user3",User.class);
        UserService us = (UserService) ctx.getBean( UserService.class);
        us.addUser(user);
    }
}
