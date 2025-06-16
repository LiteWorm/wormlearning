package com.liteworm.aspect;

import com.liteworm.beans.Student;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * @ClassName MyAspect
 * @Decription
 * 切面类：是用来给业务方法增强功能的
 * @Aspect：是aspectj框架中的，标识当前类是切面类
 *  位置：在累的定义上面使用
 * @AUthor LiteWorm
 * @Date 2020/4/18 21:30
 * @Version 1.0
 **/
@Aspect
public class MyAspect {
    //有功能增强的方法。

    /**
    * 定义方法实现功能增强，方法的定义格式
     * 1.public方法
     * 2.一般都是void返回值(环绕通知除外)
     * 3.方法名称自定义
     * 4.方法可以有参数，参数的类型有限制
    **/

    /**
    * @auther LiteWorm
    * @ClassName MyAspect
    * @FunctionName myBefore
    * @Description
     * @Before:前置通知
     *  属性：value 标识切入点表达式，标识切面执行的位置
     *  位置：方法定义的上面
     *
     *  特点：
     *      1.在目标方法之前先执行
     *      2.不会影响目标方法的执行
     *      3.不会影响目标方法的执行结果
    * @Date 21:35 2020/4/18
    * @Param []
    * @return void
    **/
//    @Before(value = "execution(* com.liteworm.service.impl.SomeServiceImpl.doSome(String, int, com.liteworm.service.Other))")
//    public  void myBefore(){
//        //在方法中实现功能的增强，例如日志的代码
//        System.out.println("前置通知：在目标方法之前指向日志的功能");
//    }


    /**
    * @auther LiteWorm
    * @ClassName MyAspect
    * @FunctionName myBefore
    * @Description
     * 获取doSome方法在执行时候的信息
     *  参数：
     *      JoinPoint 表示连接点（业务方法）
     *      连接点是切入点中的一个方法
    * @Date 22:10 2020/4/18
    * @Param []
    * @return void
    **/
    @Before(value = "execution(* *..SomeServiceImpl.doSome(..))")
    public  void myBefore(JoinPoint joinPoint){
        //在方法中实现功能的增强，例如日志的代码
        System.out.println("前置通知：在目标方法之前指向日志的功能2");
        //获取方法的定义
        System.out.println("连接点的方法定义：" + joinPoint.getSignature());
        System.out.println("连接点的方法名称：" + joinPoint.getSignature().getName());
        //获取方法执行时的参数
        Object objs[] = joinPoint.getArgs();
        for (Object obj :
                objs) {
            System.out.println(obj);
        }

    }


    /**
     * @AfterReturinig:后置通知
     *  属性：
     *      value：切入点表达式
     *      returning：自定义变量，标识目标方法的返回值的，
     *                  变量的名称必须和通知方法的菜蔬名一样
     *  位置：方法的上面
     *  特点：
     *      1.在目标方法之后执行的
     *      2.能获取到目标方法的执行结果
     *      3.修改目标方法不会影响最后的执行结果
     *
     */
    @AfterReturning(value = "execution(* *..SomeServiceImpl.doOther(..))", returning = "result")
    public  void myAfterReturing(Object result) {
        //修改目标方法的返回值
        if(result != null){
            String st = (String) result;
            result = st.toUpperCase();
        }
        System.out.println("后置通知，在目标方法之后执行的，能够获取到方法的执行结果result：" + result);
    }

    @AfterReturning(value = "execution(* *..SomeServiceImpl.doOther2(..))", returning = "result")
    public  void myAfterReturing2(Object result) {
        //修改目标方法的返回值
        if(result != null){
            Student st = (Student) result;
            st.setAge(20);
            st.setName("lisi");
            result = st;
        }
        System.out.println("后置通知，在目标方法之后执行的，能够获取到方法的执行结果result：" + result);
    }


    /**
    * @Around：环绕通知
     *    属性：
     *      value：切入点表达式
     *    位置：
     *      方法定义的上面
     *    特点：
     *      1.在目标方法的钱和后都增强功能
     *      2。控制目标方法是否执行
     *      3.膝盖目标方法的执行结果
     * 环绕通知方法的定义：
     *  1.有参数ProceedingJoinPoint
     *  2.有返回值，推荐使用Object
     *
     *
     * myAround等同于：InvocationHandler的invoke()
     * interface ProceedingJoinPoint extends JoinPoint
     **/


    @Around(value = "execution(* *..SomeServiceImpl.doFirst(..))")
    public Object myAround(ProceedingJoinPoint pjp){

        //ProceedingJoinPoint 能够湖区连接点的方法的定义，参数等信息
        Object args[] = pjp.getArgs();
        String name = "";
        if (args.length > 1){
            name = (String) args[0];
        }

        Object result = null;


        System.out.println("环绕通知：在目标方法之前加入日志");
        //控制目标方法是否执行
        if("zhangsan".equals(name))
        {
            //执行目标方法
            try {
                result =  pjp.proceed();  //doFirst
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }

        System.out.println("环绕通知：在目标方法之后加入事务处理");

        //返回目标方法的执行结果（可以是修改后的结果）
        //修改目标方法的执行结果
        if(result != null){
            result = "Helllo AspectJ";
        }
        return result;
    }

    /**
    * @AfterThrowing:异常通知
     *  属性：
     *     value：切入点表达式
     *     throwing：自定义变量，标识目标方法抛出的异常对象，必须和同支付方法的参数名一样
     *  位置：
     *      方法定义的上面
     *  特点：
     *      1.在目标方法抛出异常是执行的，可以看做是对布标方法的监控
     *      2.不是异常处理程序，异常还是会抛出的
     *
     *  try{
     *
     *      SomeServiceImpl.doSecond(..)
     *  }catch(Exception ex){
     *      AfterThrowing(e);
     *  }
    **/

    @AfterThrowing(value = "execution(* *..SomeServiceImpl.doSecond(..))" , throwing = "ex")
    public  void myAfterThrowing(Throwable ex){

            //能获取到异常发生的信息
            //项目中怎么处理异常：
                //1.把异常记录下来（数据库，日志文件）
                //2.发送通知（邮件，短信）
            System.out.println("异常通知：在目标方法抛出异常是执行的，异常原因：" + ex.getMessage());
        }

    
        
    /**
    * @auther LiteWorm
    * @ClassName MyAspect
    * @FunctionName myAfter
    * @Description
     * @After:最终通知
     *  属性：value 切入点表达式
     *  位置：字方法的定义的上面
     *
     *  特点：
     *      1.子啊目标方法执行之后执行的
     *      2.总是会被执行的
     *
    * @Date 14:07 2020/4/19
    * @Param [ex]
    * @return void
    **/    
//    @After(value = "execution(* *..SomeServiceImpl.doThird(..))" )
    @After(value = "mypt()")
    public  void myAfter(){


        System.out.println("最终通知：总是会被执行的，可以做程序最后要做的工作，例如资源回收，内存释放");
    }


    /**
    * @pointcut:定义和管理切入点
     *  属性：
     *      value 切入点表达式
     *  位置：
     *      自定义的方法上面
     *  作用：
     *     @Pointcut定义在方法上面，之歌方法的名称就是切入点的别名
     *     其他的通知注解的value属性可以使用方法名称，表示切入点
     *
    **/

    @Pointcut(value = "execution(* *..SomeServiceImpl.doThird(..))")
    private  void mypt(){
        //无需代码
        //一般声明为private
    }



    @Before(value = "execution(* *..OrderServiceImpl.*(..))")
    public  void orderLog(){
        System.out.println("前置通知，加入日志功能");
    }
}