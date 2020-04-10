package com.liteworm.javaLearn.thread;

/**
 * @ClassName testThreadOperations
 * @Decription
 * Thread.currentThread() 返回当前线程
 * getName() 返回当前线程的名称
 * t2.setDaemon(true)  设置为守护线程
 * @AUthor LiteWorm
 * @Date 2020/4/10 23:57
 * @Version 1.0
 **/
public class testThreadOperations {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() +"---->" + i);

                }
            }
        }, "t1");
        t1.start();
        System.out.println("t1++>"  + t1.isAlive());

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 300; i++) {
                    System.out.println(Thread.currentThread().getName() +"---->" + i);

                }
            }
        });
        t2.setName("t2");
        t2.setDaemon(true);
        t2.start();
        System.out.println("t2++>"  + t1.isAlive());

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() +"---->" + i);
                }
            }
        }).start();

        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() +"---->" + i);

        }
    }
}
