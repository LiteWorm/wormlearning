package com.liteworm.javaLearn.basicKnowledge.chapter01.interfaces.description;

public interface MyInterface {
    void m1();   //接口中的方法默认使用public abstract 修饰，一般情况下，接口中只有抽象放阿飞

    int XX = 999;  //接口中的字段默认使用public static final 修饰

    public static  void sm(){
        System.out.println("接口中可以定义静态方法，通过接口名调用");
    }

    public default void dm(){
        System.out.println("接口中方法如果使用default 修改，在接口中可以有默认方法体，"
            +"事项类可以重写该方法，也可以不重写");
    }
}
