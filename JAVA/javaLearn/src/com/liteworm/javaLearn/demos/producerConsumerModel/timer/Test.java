package com.liteworm.javaLearn.demos.producerConsumerModel.timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @ClassName Test
 * @Decription
 * 测试使用定时器控制任务
 * @AUthor LiteWorm
 * @Date 2020/4/11 21:56
 * @Version 1.0
 **/
public class Test {
    public static void main(String[] args) throws InterruptedException {
        //创建定时器
        Timer timer = new Timer(true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(new Date());
            }
        }, 3000, 100);
        Thread.sleep(10000);
    }
}