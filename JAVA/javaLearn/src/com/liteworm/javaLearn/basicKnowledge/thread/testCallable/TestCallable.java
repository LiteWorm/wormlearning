package com.liteworm.javaLearn.basicKnowledge.thread.testCallable;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * @ClassName TestCallable
 * @Decription 测试使用实现Callable接口创建线程
 * @AUthor LiteWorm
 * @Date 2020/4/10 23:33
 * @Version 1.0
 **/
public class TestCallable implements Callable <Integer>{
    @Override
    public Integer call() throws Exception {
        int result = new Random().nextInt(100);
        Thread sc = Thread.currentThread();
        System.out.println("当前活动线程为" + sc.getName());
        System.out.println("处理完成,结果为：" + result);
        return result;
    }
}
