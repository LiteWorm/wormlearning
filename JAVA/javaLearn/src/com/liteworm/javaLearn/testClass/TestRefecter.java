package com.liteworm.javaLearn.testClass;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;



public class TestRefecter {

    /**
    * @auther LiteWorm
    * @ClassName TestRefecter
    * @FunctionName main
    * @Description
     * 测试使用反射实现调用String的equals方法
    * @Date 18:29 2020/4/11
    * @Param [args]
    * @return void
    **/
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        //创建Class
        Class<?> class1 = String.class;

        //通过反射创建String实例
        Object obj1 = class1.newInstance();
        Object obj2 = class1.newInstance();
        //分别给obj1和obj2设值
        Field valueF = class1.getDeclaredField("value");
        valueF.setAccessible(true);
        valueF.set(obj1, "测试".toCharArray());
        valueF.set(obj2, "测试".toCharArray());

        System.out.println("obj1=" + obj1);
        System.out.println("obj1=" + obj2);

        //获取String 的equals方法
        Method equals = class1.getDeclaredMethod("equals", Object.class);
        System.out.println (equals.invoke(obj1,obj2));
        System.out.println(equals.invoke(obj1, "测试"));

    }
}
