package com.liteworm.javaLearn.basicKnowledge.testArray;

import java.util.Arrays;

/**
 * @ClassName Test01
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/4/5 16:41
 * @Version 1.0
 **/
public class Test01 {

    public static void main(String[] args) {
        int [] data = getArray();
        System.out.println("原始数组：" + Arrays.toString(data));
        swap(data, 0, 2);
        System.out.println("调用swap后：" + Arrays.toString(data));
        
        newarray(data);
        System.out.println("调用newarray后：" + Arrays.toString(data));
    }

    private static int[] getArray() {
        return  new int[]{4,6,7,34,0};
    }

    private static void newarray(int[] data) {
        data = new int []{0,0,0,0,0};
    }

    private static void swap(int[] data, int i, int i1) {
        int tmp = data[i];
        data[i]  = data[i1];
        data[i1] = tmp;
    }
}
