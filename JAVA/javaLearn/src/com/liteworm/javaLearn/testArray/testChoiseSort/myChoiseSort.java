package com.liteworm.javaLearn.testArray.testChoiseSort;

import java.util.Arrays;

/**
 * @ClassName myChoiseSort
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/5 15:11
 * @Version 1.0
 **/
public class myChoiseSort {

    public static void main(String[] args) {
        int[] data = {53, 43, 33, 103 ,103, 23, 13, 3};
        System.out.println("原数组：" + Arrays.toString(data));
        data = choisesort(data, 0);
        System.out.println("原数组排序后 ：" + Arrays.toString(data));


        int [][] ss = new int [5][8];
        System.out.println(Arrays.deepToString(ss));
    }

    public static int[] choisesort(int [] data, int per){
        int[] mydata = data;
        int myper = per;
        for(int i = myper + 1;  i < mydata.length  ; i++){
            if(mydata[i] < mydata[myper]){
                myper = i;
            }
        }
        if(myper != per){
            int minedata = data[myper];
            data[myper] = data[per];
            data[per] = minedata;
        }else{
            System.out.println("数据相同，不需要交换data[" + per + "] = " + data[per] +
                    ",data[" + myper + "]=" + data[myper]);
        }

        System.out.println("\t第" + (per + 1) + "次排序结果：" + Arrays.toString(mydata));
        if(per < data.length - 2){
            mydata = choisesort(data, per + 1);
        }

        return  mydata;
    }
}
