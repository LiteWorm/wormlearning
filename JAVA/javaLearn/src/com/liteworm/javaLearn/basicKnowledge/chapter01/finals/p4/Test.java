package com.liteworm.javaLearn.basicKnowledge.chapter01.finals.p4;

/**
* @auther LiteWorm
* @Description
* @Date 0:52 2020/3/29
* @Param 
* @return
**/

public class Test {
    public  static void main(String[] args){
        int x;      //局部变量
        x = 10;
        x = 101;

        final int y;  //final 局部变量，一旦被初始化就不能再被修改
        y = 19;
//      y = 20;

        Person p1 = new Person("lisi", 18);
        final Person p2 = new Person("zhangsan", 19);

        //final 关键字修饰的是p2变量，是不能给p2重新赋值的
        //p2变量不能在指向其他对象
        //但p2的成员变量可以别修改
//        p2 = p1;
//        p2 = new Person("wanger", 92);
        p2.name = "ceshi ";
    }

}
