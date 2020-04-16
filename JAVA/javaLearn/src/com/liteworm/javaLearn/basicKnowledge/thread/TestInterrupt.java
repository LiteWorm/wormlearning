package com.liteworm.javaLearn.basicKnowledge.thread;

/**
 * @ClassName TestInterrupt
 * @Decription 测试线程中断
 * @AUthor LiteWorm
 * @Date 2020/4/11 0:32
 * @Version 1.0
 **/
public class TestInterrupt {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    System.out.println("t1===>" + i);
                    if (i == 20) {
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                            continue;
                        }
                    }
                }
            }
        });
        t1.start();
        for (int i = 0; i <= 100 ; i++) {
            System.out.println(Thread.currentThread().getName() + "===>" + i);
        }
        t1.interrupt();

        //当main线程结束，唤醒t1
    }
}
