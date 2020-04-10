package com.liteworm.javaLearn.streams.printStream;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.TreeMap;

/**
 * @ClassName Test
 * @Decription
 * 测试打印输出流
 * @AUthor LiteWorm
 * @Date 2020/4/9 23:21
 * @Version 1.0
 **/
public class Test {

    public static void main(String[] args) {
//        PrintStream ps = new PrintStream(System.out);
//       // ps.println("测试");
//        ps.close();


        PrintWriter pw = new PrintWriter(System.out);
        pw.println("测试");
        pw.close();
        

    }
}
