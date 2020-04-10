package com.liteworm.javaLearn.streams.testFileInOutputStreams.testFileInputStream;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @ClassName TestReadMutiBytesFromFIle
 * @Decription  一次从文件中读取多个字节
 * 1、一次读取一个字节数组
 * 2、读取很多自己保存到字节数组中
 * @AUthor LiteWorm
 * @Date 2020/4/6 20:42
 * @Version 1.0
 **/
public class TestReadMutiBytesFromFIle {

    public static void main(String[] args) throws IOException {
        //1）在当前文件与指定的文件之间建立通道
        FileInputStream fis = new FileInputStream("D:/Develop/demo/filetest/test.txt");

        //2）读写文件内容
//        fis.read();
        //2.1）以字节流的方式读取文件
        //read（）方法会从文件中读取一个字节，并把读到的字节返回，读到文件末尾返回-1
       byte[] bytes = new byte[4];
        int len = 0;
        while ((len = fis.read(bytes)) > 0){

            System.out.println("长度：" + len + "\t data:" + new String(bytes, 0 , len));
        }
        System.out.println();
         //3）关闭通道
        fis.close();;
    }
}
