package com.liteworm.ba04.beans;

/**
 * @ClassName User
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/18 12:51
 * @Version 1.0
 **/
public class User {
    private  String id;
    private  String name;
    private  int age;


    public User(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public User() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}