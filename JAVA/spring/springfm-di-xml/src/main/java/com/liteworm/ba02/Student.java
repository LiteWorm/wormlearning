package com.liteworm.ba02;

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

    //引用类型
    private School school;

    public Student() {
        System.out.println("Student 的无参构造");
    }

    /**
    * 没有set方法报错
     * Bean property 'name' is not writable or has an invalid setter method
    **/
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSchool(School school) {
        System.out.println("setSchool:" + school);
        this.school = school;
    }

    public  void setSex(String sex){
        System.out.println("setSex:" + sex);
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