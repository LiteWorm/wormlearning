package com.liteworm.service.impl;

import com.liteworm.beans.Other;
import com.liteworm.service.SomeService;
import com.liteworm.beans.Student;

/**
 * @ClassName SomeServiceImpl
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/18 21:29
 * @Version 1.0
 **/
public class SomeServiceImpl implements SomeService {
    @Override
    public void doSome(String name, int age, Other other) {
        System.out.println("SomeServiceImpl.doSome, name:" + name + ", age:" + age + ", Other:" + other);
    }

    @Override
    public void doSome(String name, int age) {
        System.out.println("SomeServiceImpl.doSome, name:" + name + ", age:" + age );
    }

    @Override
    public String doOther() {
        System.out.println("SomeServiceImpl.doOther");
        return  "aaa";
    }

    @Override
    public Student doOther2() {
        Student student = new Student();
        student.setName("zhangsan");
        student.setAge(20);
        System.out.println("doOther2:"+ student);
        return student;
    }

    @Override
    public String doFirst(String name, int age) {

        System.out.println("SomeServiceImpl的业务方法doFirst");
        return "doFirst";
    }

    @Override
    public void doSecond(String name, int age) {
        System.out.println("SomeServiceImpl的业务方法doSecond");
        int i = 10/0;
    }

    @Override
    public void doThird() {
        System.out.println("SomeServiceImpl的业务方法doThird");
        int i = 10/0;
    }
}