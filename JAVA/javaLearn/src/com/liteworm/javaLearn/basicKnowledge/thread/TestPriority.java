package com.liteworm.javaLearn.basicKnowledge.thread;

/**
 * @ClassName TestPriority
 * @Decription 测试线程优先级
 * @AUthor LiteWorm
 * @Date 2020/4/11 0:25
 * @Version 1.0
 **/
public class TestPriority {
    public static void main(String[] args) {


        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {


                for (int i = 0; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName() +"---->" + i);


                }
            }
        }, "t1");
        t1.setPriority(2);
        t1.start();


        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {


                for (int i = 0; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName() +"====>" + i);


                }
            }
        }, "t2");
        t2.setPriority(10);
        t2.start();
        System.out.println(t1.getName() +" Priority====>" + t1.getPriority());
        System.out.println(t2.getName() +" Priority====>" + t2.getPriority());
        System.out.println(Thread.currentThread().getName() +" Priority====>" + Thread.currentThread().getPriority());
    }

}
