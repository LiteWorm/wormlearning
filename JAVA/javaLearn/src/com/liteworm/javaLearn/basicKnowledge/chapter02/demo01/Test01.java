package com.liteworm.javaLearn.basicKnowledge.chapter02.demo01;

/**
 * @ClassName Test
 * @Decription 演示运行时异常
 * @AUthor LiteWorm
 * @Date 2020/3/29 16:21
 * @Version 1.0
 **/
public class Test01 {
    public static void main(String[] args) {
        divide(10,2);
        divide(10, 0);
        System.out.println("程序结束");
    }

    public static void  divide (int x, int y){
        int result = 0;
        try {
            result = x/y;
        } catch (Exception e) {
           // System.out.println(e.printStackTrace().);;
            System.out.println("异常信息：" + e.getMessage().toString().trim());
            System.out.println("异常堆栈信息：" + e.getStackTrace().toString());
            System.out.println("计算出错退出");
            return;
        } finally {
            System.out.println("计算结束");
        }
        System.out.println(x + "/" + y + "=" + result);
    }
}
