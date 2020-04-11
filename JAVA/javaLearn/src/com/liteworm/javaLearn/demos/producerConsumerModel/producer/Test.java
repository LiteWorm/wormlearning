package com.liteworm.javaLearn.demos.producerConsumerModel.producer;

/**
 * @ClassName Test
 * @Decription
 * 模拟生产者消费者模式
 * @AUthor LiteWorm
 * @Date 2020/4/11 20:40
 * @Version 1.0
 **/
public class Test {
    public static void main(String[] args) {
        Warehouse wh = new Warehouse();
        //创建生产线程
        Producer producer = new Producer(wh);
        producer.setName("生产者");
        //创建消费线程
        Consumer consumer = new Consumer(wh);
        consumer.setName("消费者");

       //启动线程
        producer.start();
        consumer.start();

       //创建守护线程


    }
}