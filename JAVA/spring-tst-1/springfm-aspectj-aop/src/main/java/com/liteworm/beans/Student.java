package com.liteworm.beans;

/**
 * @ClassName Student
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/19 11:18
 * @Version 1.0
 **/
public class Student {
    private  String name;
    private  int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}