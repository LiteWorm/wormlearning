package com.liteworm.javaLearn.basicKnowledge.streams.printStream;

import java.io.PrintWriter;

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
