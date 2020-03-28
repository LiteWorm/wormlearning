package com.liteworm.javaLearn.chapter01.finals.p2;

public class Father2 {
    int xx = 10; // 实例变量
    static int  yy = 20; // 静态变量
    final int  zz = 666; // 经常在定义字段时就初始化
    final int tt ;
    final static int qq = 543;
    final static int pp ;

    public void m1(){
        xx  = 101;  // 在实例方法中修改实例变量的值
//        zz  = 1234; // final 修饰的字段不能在重新赋值
    }

    public static  void  sm(){
        yy = 202;   //在静态方法中袖肥静态变量的值
    }

    public Father2(){
        tt = 342; // 也可以在构造方法中给final修饰的实例变量赋值

    }
    static{
        pp = 9;
    }
    public static  final double PI = 3.1415926;

}
