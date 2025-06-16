package com.liteworm.ba04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName Student
 * @Decription
 * @AUthor LiteWorm
 * @Date 2020/4/18 16:21
 * @Version 1.0
 **/


@Component("myStudent")
public class Student {
    /**
    * @Value:简单类型的属性赋值
     *  属性：value 是String类型的，标识简单类型的属性值
     *  位置：
     *      1、在属性定义的上面，无需set方法，常用的方式
     *      2、在set的方法上面
    **/

    @Value(value = "张三")
    private  String name;

    private  int age;

    /**
    * 引用类型
     * @Autowired：应用类型的自动注入，支持byName，byType，默认是byType
     *      属性：required，boolean，默认是true
     *          true：引用类型必须赋值成功，如果失败，程序报错，并终止执行
     *          false：应用类型复制失败，程序正常执行，引用类型的值是null
     *      位置：
     *          1.属性定义的上面，无需set方法，常用方式
     *          2.在set方法的上面
     * byName注入需要两个注解：
     * 1.@Autowired
     * 2.@Qualifier(value="bean的id")
    **/
    //byType
//    @Autowired

     //byName
    @Autowired
    @Qualifier(value = "school")
    private School school;

    public Student() {
        System.out.println("使用注解方式调用默认构造");
    }

    public void setName(String name) {
        this.name = name;
    }

    @Value(value = "37")
    public void setAge(int age) {
        System.out.println("setAge:" + age);
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", school=" + school +
                '}';
    }
}