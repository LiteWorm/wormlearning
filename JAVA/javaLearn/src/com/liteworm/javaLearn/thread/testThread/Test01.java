package com.liteworm.javaLearn.thread.testThread;

/**
 * @ClassName Test01
 * @Decription 测试通过集成Thread 实现线程
 * @AUthor LiteWorm
 * @Date 2020/4/10 22:52
 * @Version 1.0
 **/
public class Test01 {
    public static void main(String[] args) throws InterruptedException {
        SubThread st = new SubThread();
        st.setName("子线程");
        st.start();

        for (int i = 0; i < 100 ; i++) {
            System.out.println("main --->" + i);
            Thread.sleep(1);
        }
    }

}
