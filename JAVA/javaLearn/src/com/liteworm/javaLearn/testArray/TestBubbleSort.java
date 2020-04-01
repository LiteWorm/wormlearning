package com.liteworm.javaLearn.testArray;

import java.util.Arrays;
import java.util.Comparator;

import static com.liteworm.javaLearn.testArray.MyBublleSort.bubblesort02;

/**
 * @ClassName TestBubbleSort
 * @Decription @TOTO
 * @AUthor LiteWorm
 * @Date 2020/3/31 22:09
 * @Version 1.0
 **/
public class TestBubbleSort {
    public static void main(String[] args) {

//        testNomalBubbleSort();
          testCommonBubbleSort();

    }

    private static void testCommonBubbleSort() {

        //定义数组报出Product产品对象
        Product[] data = new Product[5];

        data[0] = new Product("lenovo", 6800);
        data[1] = new Product("Dell", 7800);
        data[2] = new Product("Huawei", 4800);
        data[3] = new Product("Mac", 16800);
        data[4] = new Product("xiaomi", 3800);
        MyBublleSort myBublleSort = new MyBublleSort();
        myBublleSort.bubblesort(data, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getPrice() - o2.getPrice();
            }
        });
        System.out.println(Arrays.toString(data));

    }

    public static void testNomalBubbleSort() {
        int[] data = {100, 98, 12, 34, 45};
//        data = bubbleSort01(data);
        MyBublleSort myBublleSort = new MyBublleSort();
        myBublleSort.bubblesort02(data, data.length);
        System.out.println(Arrays.toString(data));
    }


}
