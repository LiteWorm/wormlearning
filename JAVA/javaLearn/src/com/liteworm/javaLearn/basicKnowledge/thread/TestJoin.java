package com.liteworm.javaLearn.basicKnowledge.thread;

/**
 * @ClassName TestJoin
 * @Decription 测试线程的加入（合并）
 * join()
 * @AUthor LiteWorm
 * @Date 2020/4/11 0:42
 * @Version 1.0
 **/
public class TestJoin {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName() + "====>" + i);
                }
            }
        });
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName() + "----->" + i);
                    //当i == 30 时，加入t1线程，当前线程转换为登但状态，等到t1线程执行完毕后，当前线程再转换为就绪状态
                    if (i == 30) {
                        try {
                            t1.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        t2.start();
    }
}
