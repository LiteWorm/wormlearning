package com.liteworm.javaLearn.basicKnowledge.testArray;

/**
 * @ClassName unLengthParams
 * @Decription
 * 测试可变长参数方法
 * 1、定义一个方法，可以接受任意个数据，可以使用可变长参数
 * 2、定义
 *  方法名(参数类型 参数名， 类型 ... 参数)
 * 3、说明
 *  1）一个方法最多有一个可变参数
 *  2）可变长参数只能放在参数列表的最后
 *  3）在方法体中，把可变长参数当做数据使用即可
 * @AUthor LiteWorm
 * @Date 2020/4/5 16:55
 * @Version 1.0
 **/
public class unLengthParams {
    public static void main(String[] args) {

        sum();
        sum(1,2,3);
        int[] data = {3,4, 6,7,8,};
        sum(data);
    }

    //定义一个方法，可以计算人一个整数的和
    public  static  void sum(int ... data){
        int sum = 0;
        for (int xx : data){
            sum += xx;
        }
        System.out.println("sum == " + sum);
    }
}
