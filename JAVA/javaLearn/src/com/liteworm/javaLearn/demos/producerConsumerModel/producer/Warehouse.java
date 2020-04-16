package com.liteworm.javaLearn.demos.producerConsumerModel.producer;

import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName Test
 * @Decription
 * 模拟仓库
 * @AUthor LiteWorm
 * @Date 2020/4/11 20:18
 * @Version 1.0
 **/
public class Warehouse {
    LinkedList<String> procucts = new LinkedList<>();
    public static  final  int  MAX_STORAGE = 100;


    public synchronized int produce(String product){
        while(procucts.size() > MAX_STORAGE){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        procucts.offer(product);
        this.notifyAll();
       return  procucts.size();
    }

    public synchronized String comsumer(){
        while (procucts.isEmpty()){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String s =  procucts.poll();
        return s;
    }
}