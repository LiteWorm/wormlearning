package com.liteworm.javaLearn.testArray;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @ClassName Test01
 * @Decription
 * 测试对象数组的排序
 * @AUthor LiteWorm
 * @Date 2020/3/31 21:43
 * @Version 1.0
 **/
public class Test01 {

    public static void main(String[] args) {
        //定义数组报出Product产品对象
        Product[] data = new Product[5];

        data[0] = new Product("lenovo", 6800);
        data[1] = new Product("Dell", 7800);
        data[2] = new Product("Huawei", 4800);
        data[3] = new Product("Mac", 16800);
        data[4] = new Product("xiaomi", 3800);

        System.out.println(Arrays.toString(data));

        //对数组排序，可以指定一个Comparator比较器
        //Copmparator 后面<T>是一个方形，指定比较袁术的数据类型
        Arrays.sort(data,new Comparator<Product>(){
            //在匿名内部类中重写Comparator接口的抽象方法，指定比较规则


            @Override
            public int compare(Product o1, Product o2) {
                //根据价格的升序
                return o1.getPrice() - o2.getPrice(); //如果升序，第一个对象大于第二个对象返回正数，第一个对象小于第二个对象返回负数，两个对象相等返回0
            }
        });

        System.out.println(Arrays.toString(data));
    }
}
