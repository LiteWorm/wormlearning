package com.liteworm;

import static org.junit.Assert.assertTrue;

import com.liteworm.service.TestService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }


    /**
    * @auther LiteWorm
    * @ClassName AppTest
    * @FunctionName
    * @Description
     *测试方法，使用junit
     * 1、定义方法，public void 方法名自定定义的()
     * 2、在方法的上下加入@Test
     * 3、把光标放在方法的上面，执行run
    * @Date 4:44 2020/4/18
    * @Param
    * @return
    **/
    @Test
    public void test01(){
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


    /**
    * @auther LiteWorm
    * @ClassName AppTest
    * @FunctionName
    * @Description
     * 测试容器的其他使用方法，在Spring的配置文件放在项目的根目录之下
     * 和src,target是同级的位置
     * 使用FileSystemXmlApplicationContext 读取配置文件
    * @Date 9:42 2020/4/18
    * @Param
    * @return
    **/
    @Test
    public void test02(){
        System.out.println( "Hello World!" );
        //定义Spring的配置文件
        String config = "spring.xml";

        //创建Spring的容器对象,根据Spring配置文件的位置，使用接口的不同实现类
        //如果Spring的配置文件是在类路径（classpath），使用ClassPathXmlApplicationContext
        ApplicationContext ctx = new FileSystemXmlApplicationContext(config);

        //从容器中获取对象，使用getBean("<bean> 的id")
        TestService testService =  (TestService)ctx.getBean("myService2");
        testService.testService();
    }

    /**
    * @auther LiteWorm
    * @ClassName AppTest
    * @FunctionName
    * @Description
     * 容器中对象的创建时间
     * 默认是在创建容器对象是，把配置文件中的所有对象都创建出来，放在容器之中
     *
     * 优点：
     *  获取对象快，对象已经创建好了，可以快速调用
     * 缺点：
     *  占用内存多
    * @Date 9:48 2020/4/18
    * @Param
    * @return
    **/
    @Test
    public void test03(){
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

    /**
    * @auther LiteWorm
    * @ClassName AppTest
    * @FunctionName test04
    * @Description
     * 获取容器中对象信息
    * @Date 9:59 2020/4/18
    * @Param []
    * @return void
    **/
    @Test
    public void test04(){
        //定义Spring的配置文件
        String config = "aplicationContext.xml";

        //创建Spring的容器对象,根据Spring配置文件的位置，使用接口的不同实现类
        //如果Spring的配置文件是在类路径（classpath），使用ClassPathXmlApplicationContext
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);

        //获取容器中定义的对象中的个数
        int nums = ctx.getBeanDefinitionCount();
        System.out.println("容器中对象的个数：" + nums);

        //获取容器中每个对象的名称
        String[] names = ctx.getBeanDefinitionNames();
        for (String name: names) {
            System.out.println(name);
        }
    }

    /**
    * @auther LiteWorm
    * @ClassName AppTest
    * @FunctionName test05
    * @Description
     *Spring 默认调用累的无参构造方法创建对象
    * @Date 10:11 2020/4/18
    * @Param []
    * @return void
    **/
    @Test
    public void test05(){
        //定义Spring的配置文件
        String config = "aplicationContext.xml";

        //创建Spring的容器对象,根据Spring配置文件的位置，使用接口的不同实现类
        //如果Spring的配置文件是在类路径（classpath），使用ClassPathXmlApplicationContext
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);

        //从容器中获取对象，使用getBean("<bean> 的id")
        TestService testService =  (TestService)ctx.getBean("myService");
        testService.testService();
    }


    /**
    * @auther LiteWorm
    * @ClassName AppTest
    * @FunctionName test06
    * @Description
     * 测试单例：默认的
     * 单例对象的创建时间：
     *  在创建容器对象时，会创建所有的单例对象。
     *
     * 优点：获取对象的速度快
     *
    * @Date 10:23 2020/4/18
    * @Param []
    * @return void
    **/
    @Test
    public  void test06(){
        //定义Spring的配置文件
        String config = "aplicationContext.xml";

        //创建Spring的容器对象,根据Spring配置文件的位置，使用接口的不同实现类
        //如果Spring的配置文件是在类路径（classpath），使用ClassPathXmlApplicationContext
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);

        //从容器中获取对象，使用getBean("<bean> 的id")
//        TestService testService1 =  (TestService)ctx.getBean("myService2");
//        TestService testService2 =  (TestService)ctx.getBean("myService2");
//
//        if (testService1 == testService2){
//            System.out.println("testService1和testService2是同一个对象");
//        }else{
//            System.out.println("testService1和testService2 不是 同一个对象");
//        }
    }

    /**
    * @auther LiteWorm
    * @ClassName AppTest
    * @FunctionName test07
    * @Description
     * 测试原型
     * 原型对象的创建时间：
     *  把对象创建的时间延迟到使用对象的时候，
     *  在执行getBean()时创建对象
     *  有点：不占内存
     *  取点：获取对象的速度慢
    * @Date 10:30 2020/4/18
    * @Param []
    * @return void
    **/
    @Test
    public  void test07(){
        //定义Spring的配置文件
        String config = "aplicationContext.xml";

        //创建Spring的容器对象,根据Spring配置文件的位置，使用接口的不同实现类
        //如果Spring的配置文件是在类路径（classpath），使用ClassPathXmlApplicationContext
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);

        //从容器中获取对象，使用getBean("<bean> 的id")
        TestService testService1 =  (TestService)ctx.getBean("myService2");
        TestService testService2 =  (TestService)ctx.getBean("myService2");
//
//        System.out.println(testService1.hashCode() + "       " + testService2.hashCode());
//        if (testService1 == testService2){
//            System.out.println("testService1和testService2是同一个对象");
//        }else{
//            System.out.println("testService1和testService2 不是 同一个对象");
//        }
    }
}
