package com.liteworm.javaLearn.thread;

/**
 * @ClassName TestThreadStatus
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/11 0:05
 * @Version 1.0
 **/
public class TestThreadStatus {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {


                for (int i = 0; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName() +"---->" + i);


                }
            }
        }, "t1");

        System.out.println("t1++>"  + t1.getState());
        t1.start();
        System.out.println("t1==>"  + t1.getState());

        for (int i = 0; i < 200; i++) {
            System.out.println(Thread.currentThread().getName() +"---->" + i);

        }

        System.out.println("t1***>"  + t1.getState());

    }
}
