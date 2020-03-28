package com.liteworm.javaLearn.chapter01.finals.p4;

/**
 * @ClassName Person
 * @Decription
 * @AUthor YongLiu
 * @Date 2020/3/29 0:57
 * @Version 1.0
 **/
public class Person {
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

    String name;
    int age;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
