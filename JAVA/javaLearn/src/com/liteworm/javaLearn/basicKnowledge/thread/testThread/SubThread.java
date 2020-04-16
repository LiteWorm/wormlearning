package com.liteworm.javaLearn.basicKnowledge.thread.testThread;

/**
 * @ClassName SubThread
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/10 22:53
 * @Version 1.0
 **/
public class SubThread extends  Thread {
    @Override
    public void run() {
        try {
            testPrint();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void testPrint() throws InterruptedException {
        for (int i = 0; i <  100; i++) {
            System.out.println("SubThread:"+ this.getName() +" ---->" + i);
            Thread.sleep(2);
        }
    }
}
