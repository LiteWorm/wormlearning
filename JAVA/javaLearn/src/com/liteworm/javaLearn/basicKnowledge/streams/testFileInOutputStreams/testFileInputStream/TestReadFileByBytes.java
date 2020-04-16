package com.liteworm.javaLearn.basicKnowledge.streams.testFileInOutputStreams.testFileInputStream;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @ClassName TestReadFileByBytes
 * @Decription 读写文件
 * @AUthor LiteWorm
 * @Date 2020/4/6 18:51
 * @Version 1.0
 **/
public class TestReadFileByBytes {

    public static void main(String[] args) throws IOException {
        //1）在当前文件与指定的文件之间建立通道
        FileInputStream fis = new FileInputStream("D:/Develop/demo/filetest/test.txt");

        //2）读写文件内容
//        fis.read();
        //2.1）以字节流的方式读取文件
        //read（）方法会从文件中读取一个字节，并把读到的字节返回，读到文件末尾返回-1
        int cc = 0;
         while ((cc = fis.read()) > 0){

             System.out.print((char)cc);
         }
        System.out.println();



        //3）关闭通道
        fis.close();;
    }
}
