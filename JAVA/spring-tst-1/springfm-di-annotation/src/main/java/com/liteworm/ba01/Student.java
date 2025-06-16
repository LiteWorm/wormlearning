package com.liteworm.ba01;

/**
 * @ClassName Student
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/18 10:43
 * @Version 1.0
 **/
public class Student {
    private String name;
    private int age;

    public Student() {
        System.out.println("Student的无参构造方法");
    }

    /**
    * 没有set方法报错
     * Bean property 'name' is not writable or has an invalid setter method
    **/
    public void setName(String name) {
        System.out.println("Student的setName方法");
        this.name = name;
    }

    public void setAge(int age) {
        System.out.println("Student的setAge方法");
        this.age = age;
    }

    public  void setSex(String sex){
        System.out.println("setSex:" + sex);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}