package com.liteworm.ba03;

import org.springframework.beans.factory.annotation.Autowired;
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
     *      位置：
     *          1.属性定义的上面，无需set方法，常用方式
     *          2.在set方法的上面
    **/
    @Autowired
    private  School school;

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