package com.liteworm.javaLearn.basicKnowledge.thread.testRunnable;

/**
 * @ClassName TestRunnable
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/10 23:18
 * @Version 1.0
 **/
public class TestRunnable implements  Runnable {
    private  String name;

    public TestRunnable() {
    }

    public TestRunnable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            testPrint();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void testPrint() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            System.out.println("SubThread:" + this.getName() + " ---->" + i);
            Thread.sleep(2);
        }
    }
}