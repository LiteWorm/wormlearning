package com.liteworm.javaLearn.chapter02.demo03;

/**
 * @ClassName Test
 * @Decription
 * 测试自定义异常
 * @AUthor LiteWorm
 * @Date 2020/3/31 0:46
 * @Version 1.0
 **/
public class Test {
    public static void main(String[] args) {
        //用户如果直接修改对象的字段，可能赋值一些无效的字段
//        Person p1 = new Person();
//        p1.Name = "张三";
//        p1.age = 1999;
//        p1.gender = "男";
//        System.out.println(p1);

        //解决方法，对字段进行封装
        Person p2 = new Person();
        p2.setName("王五");
        try {
            p2.setAge(100);
            p2.setGender("╭︿︿︿╮ {/ o  o /}   ( (oo) )     ︶︶︶");
        } catch (AgeOutOfRangeException | GenderException e ) {
            e.printStackTrace();
        }

    }
}
