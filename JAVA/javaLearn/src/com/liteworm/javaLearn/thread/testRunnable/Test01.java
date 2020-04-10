package com.liteworm.javaLearn.thread.testRunnable;

/**
 * @ClassName Test01
 * @Decription 测试实现Runnable接口创建线程
 * @AUthor LiteWorm
 * @Date 2020/4/10 23:20
 * @Version 1.0
 **/
public class Test01 {
    public static void main(String[] args) {
        TestRunnable tr = new TestRunnable();
        tr.setName("Runnable线程");
        new Thread(tr).start();
        new Thread( new TestRunnable("另一个子线程")).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("一个奇葩的线程：" + i);
                }
            }
        }).start();

    }
}
