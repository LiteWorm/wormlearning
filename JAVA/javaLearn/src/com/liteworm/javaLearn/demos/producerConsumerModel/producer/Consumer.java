package com.liteworm.javaLearn.demos.producerConsumerModel.producer;

/**
 * @ClassName Consumer
 * @Decription
 * 定义消费者类
 * @AUthor LiteWorm
 * @Date 2020/4/11 20:27
 * @Version 1.0
 **/
public class Consumer extends Thread {
    Warehouse ts;
    private  boolean isEmpty = true;

    @Override
    public void run() {
        while (true) {
          System.out.println("消费" + ts.comsumer());
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    Consumer(Warehouse ma) {
        ts = ma;
    }
}