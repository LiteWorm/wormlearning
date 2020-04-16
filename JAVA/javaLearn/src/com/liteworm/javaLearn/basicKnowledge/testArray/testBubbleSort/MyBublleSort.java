package com.liteworm.javaLearn.basicKnowledge.testArray.testBubbleSort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @ClassName MyBublleSort
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/3/31 22:46
 * @Version 1.0
 **/
public class MyBublleSort {


    public static <T> T[] bubblesort(T[] sortArray,  Comparator<T> cmp){
        sortArray = bubblesort(sortArray, sortArray.length, cmp);
        return sortArray;
    }

    /**
    * @auther LiteWorm
    * @ClassName MyBublleSort
    * @FunctionName bubblesort
    * @Description 泛化数组冒泡排序
    * @Date 23:23 2020/3/31
    * @Param [sortArray, endPoint, cmp]
    * @return T[]
    **/
    private  static <T> T[] bubblesort(T[] sortArray , int endPoint, Comparator<T> cmp){
        //检测数组是否为空
        if (sortArray == null ){
            System.out.println("非法数组！！！");
            return  sortArray;
        }
        System.out.println(Arrays.toString(sortArray));
        if ( endPoint <=1){
            System.out.println("数组为有序序列，无需重新排序！！！");
            return  sortArray;
        }


        for(int i = 0 ; i < endPoint - 1 ; i++){
            if(cmp.compare(sortArray[i], sortArray[i+1]) > 0){
                commonExchangeData(sortArray, i);;
            }
        }
        if (endPoint > 1) {
            bubblesort(sortArray, endPoint - 1, cmp);
        }

        return sortArray;
    }

    /**
    * @auther LiteWorm
    * @ClassName MyBublleSort
    * @FunctionName commonExchangeData
    * @Description 泛化数组相邻元素交换
    * @Date 23:22 2020/3/31
    * @Param [data, i]
    * @return void
    **/
    private static <T> void commonExchangeData(T[] data, int i) {
        T tempData;
        tempData = data[i];
        data[i] = data[i+1];
        data[i+1] = tempData;
    }




    /**
     * @auther LiteWorm
     * @ClassName TestBubbleSort
     * @FunctionName bubblesort02
     * @Description 使用迭代方式实现对数组进行冒泡排序
     * @Date 22:43 2020/3/31
     * @Param [data, endPoint]
     * @return int[]
     **/
    public static int[] bubblesort02(int[] data, int endPoint) {
        //检测数组是否为空
        if (data == null ){
            System.out.println("非法数组！！！");
            return  data;
        }
        System.out.println(Arrays.toString(data));
        if ( endPoint <= 1){
            return  data;
        }
        int tempData ;
        for(int i = 0 ; i < endPoint - 1 ; i++){
            if(data[i] > data[i+1]){
                exchangeData(data, i);
            }
        }
        if (endPoint > 1) {
            bubblesort02(data, endPoint - 1);
        }
        return data;
    }
    /**
     * @auther LiteWorm
     * @ClassName TestBubbleSort
     * @FunctionName bubbleSort01
     * @Description 使用for循环实现对数组进行冒泡排序
     * @Date 22:42 2020/3/31
     * @Param [data]
     * @return int[]
     **/
    private static int[] bubbleSort01(int[] data) {
        for (int i = data.length; i > 0 ; i--) {
            data = bubblesort(data, i);
        }
        return data;
    }



    /**
     * @auther LiteWorm
     * @ClassName TestBubbleSort
     * @FunctionName bubblesort
     * @Description 实现冒泡排序
     * @Date 22:42 2020/3/31
     * @Param [data, endPoint]
     * @return int[]
     **/
    private static int[] bubblesort(int[] data, int endPoint) {
        //检测数组是否为空
        if (data == null || endPoint < 1){
            System.out.println("非法数组！！！");
            return  null;
        }
        int tempData ;
        for(int i = 0 ; i < endPoint - 1 ; i++){
            if(data[i] > data[i+1]){
                exchangeData(data, i);
            }
        }

        return data;
    }

    /**
     * @auther LiteWorm
     * @ClassName TestBubbleSort
     * @FunctionName exchangeData
     * @Description
     * 交换数组中相邻的两个元素
     * @Date 22:42 2020/3/31
     * @Param [data, i]
     * @return void
     **/
    private static void exchangeData(int[] data, int i) {
        int tempData;
        tempData = data[i];
        data[i] = data[i+1];
        data[i+1] = tempData;
    }

}
