package com.liteworm.ba05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

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
     * @Resource:来袭jdk，可以给引用类型赋值，spring框架支持这个注解的使用
     *           模式是byName自动注入、
     *        位置：
     *          1.在属性定义的上面，无需set方法，推荐使用
     *          2.在set方法的上面
    **/
     //默认是byName，先使用byName赋值，如果byName失败，则使用byType
    @Resource
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