package com.liteworm.javaLearn.testArray.testBubbleSort;

import java.util.Arrays;

/**
 * @ClassName TestRandemByArray
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/5 13:40
 * @Version 1.0
 **/
public class TestRandemByArray {
    public static void main(String[] args) {
        int[] data = getArray();
        System.out.println(Arrays.toString(data));
    }

    public static int [] getArray(){
        int[] data = new int[10];

        for(int i = 0; i < data.length; i ++){
            int xx = (int)(Math.random() * 100 + 1);
            while(exist(data, xx)){
                xx = (int)(Math.random() * 100 + 1);
            }
            data[i] = xx;
        }
        return data;
    }

    public  static  boolean exist(int [] myarrays, int key){
        for (int i = 0; i < myarrays.length; i++){
            if(key == myarrays[i]){
                return true;
            }
        }
        return  false;
    }
}
