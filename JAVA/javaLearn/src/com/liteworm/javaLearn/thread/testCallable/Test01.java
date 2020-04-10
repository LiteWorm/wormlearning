package com.liteworm.javaLearn.thread.testCallable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName Test01
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/10 23:33
 * @Version 1.0
 **/
public class Test01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestCallable tc  = new TestCallable();

        FutureTask<Integer> task = new FutureTask<>(tc);
        Thread td = new Thread(task);
        td.start();
        System.out.println("子线程的执行结果result=" + task.get());
        System.out.println("*************" + Thread.activeCount());
        Thread sc = Thread.currentThread();
        System.out.println("当前活动线程为" + sc.getName());

    }
}
