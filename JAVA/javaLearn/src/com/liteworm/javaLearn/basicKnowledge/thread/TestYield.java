package com.liteworm.javaLearn.basicKnowledge.thread;

/**
 * @ClassName TestYield
 * @Decription 测试线程让步
 * Thread.yield();
 *  设置线程让步，把线程转化为就绪状态，重新获取CPU资源
 * @AUthor LiteWorm
 * @Date 2020/4/11 0:38
 * @Version 1.0
 **/
public class TestYield {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName() + "====>" + i);
                    if(i%10 == 0){
                        Thread.yield();
                    }
                }
            }
        }, "t1");
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName() + "====>" + i);
                }
            }
        }, "t2");
        t2.start();
    }
}
