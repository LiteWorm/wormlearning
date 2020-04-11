package com.liteworm.javaLearn.demos.producerConsumerModel.producer;

import java.util.Random;

/**
 * @ClassName Producer
 * @Decription
 * 定义生产者类
 * @AUthor LiteWorm
 * @Date 2020/4/11 20:14
 * @Version 1.0
 **/
public class Producer extends Thread{
    Warehouse ts;

    public Producer(Warehouse ts) {
        this.ts = ts;
    }

    @Override
    public void run() {

        while(true) {
            String product = "货物:" + new Random().nextInt(100);
            System.out.println("生产" + product);
            ts.produce(product);

            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}